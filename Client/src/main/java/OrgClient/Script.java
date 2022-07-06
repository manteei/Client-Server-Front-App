 package OrgClient;

import Collection.LabWork;
import Grafic.GUI;
import Reader.FillerScriptFields;
import User.User;
import User.Request;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Script {
    private String path;
    private innerScript innerScript;
    private ComandReader comandReader;
    private ClientUtil clientUtil;
    private User user;
    private String answer = "scriptRes";
    private GUI gui;


    public Script(ComandReader comandReader, ClientUtil clientUtil, User user) {
        this.comandReader = comandReader;
        innerScript = new innerScript();
        this.clientUtil = clientUtil;
        this.user = user;
    }

    public String executeScript(String command, Socket socket, ByteBuffer buffer, String username) {
        String[] com = command.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String[] args = Arrays.copyOfRange(com, 1, com.length);
        //path = "./OrgClient.Client/src/main/java/"+args[0];
        path = args[0];
        try {
            if (innerScript.scriptPath.contains(path)){
                return ("Попытка рекурсивного вызова скрипта внутри него же!");
            } else innerScript.putScript(path);
            File ioFile = new File(path);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scanner = new Scanner(inputStreamReader);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                String[] scrCom = str.trim().toLowerCase(Locale.ROOT).split("\\s+");
                String[] scrArgs = Arrays.copyOfRange(scrCom, 1, scrCom.length);
                if (scrCom[0].equals("execute_script") && scrCom.length != 0 && !innerScript.scriptPath.contains(scrArgs[0])) {
                    executeScript(str, socket , buffer, username);
                } else if (scrCom[0].equals("add") | scrCom[0].equals( "update") | scrCom[0].equals("add_if_max")| scrCom[0].equals("add_if_min") | scrCom[0].equals("remove_lower" )){
                    FillerScriptFields fillerScriptFields = new FillerScriptFields(scanner);
                    try{
                        LabWork lab = fillerScriptFields.creationsLabwork(username);
                        Request request = comandReader.getRequestFromScript(str, lab, this.user);
                        request.setUser(user);
                        clientUtil.sendRequestToServer(request, socket);
                        answer += ("@" + (clientUtil.receive(socket).getBody()));
                        buffer.clear();
                    } catch (NullPointerException e){
                        answer += ("@"  + "Объект из скрипта не прочитан!");
                    }

                } else {
                    Request request = comandReader.getRequestFromCommand(str, this.user);
                    request.setUser(user);
                    clientUtil.sendRequestToServer(request, socket);
                    String ans = clientUtil.receive(socket).getBody();
                    answer += "@" + ans + "\n";
                    buffer.clear();
                }
            }
        } catch (IOException e) {
            answer += ("@" + "Нет доступа к файлу!" + e.getMessage());
        } finally {
            innerScript.removeScript(path);
        }
        return answer;
    }

    static class innerScript {
        private ArrayList<String> scriptPath = new ArrayList<>();

        public void putScript(String path) {
            scriptPath.add(path);
        }

        public void removeScript(String path) {
            scriptPath.remove(path);
        }

    }

    public User getUser() {
        return user;
    }
    public void setUser(){
        this.user = user;
    }
}
