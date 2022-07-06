package OrgClient;

import Collection.LabWork;
import Grafic.GUI;
import Reader.Checker;
import User.User;
import DataBase.Response;
import User.Request;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import User.CommandLine;

public class Client {
    private ClientUtil clientUtil;
    private ComandReader comandReader;
    private CommandLine commandLine;
    Script script;
    private User user;
    private String answer;
    private Socket socket;
    private InetAddress InetAddress;
    private Checker checker;
    private ByteBuffer buffer;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);


    public Client() {
        this.clientUtil = new ClientUtil();
        this.comandReader = new ComandReader();
        this.commandLine = new CommandLine();
        this.checker = new Checker(commandLine);
        this.user = new User();
        this.script = new Script(this.comandReader, this.clientUtil, this.user);
        try {
            this.InetAddress = Inet4Address.getByName("localhost");
            this.socket = new Socket(InetAddress, 8899);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.buffer = ByteBuffer.allocate(65000);
    }

    public User getUser() {
        return user;
    }

    public String getAnswer() {
        return answer;
    }

    public String authreg() {
            Request authReq = new Request(user);
        try {
            clientUtil.sendRequestToServer(authReq, socket);
            Response response = clientUtil.receive(socket);
            answer = response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public String goCommand(String comand){
        String answer1 = "1";
        Request request1 = comandReader.getRequestFromCommand(comand, user);
        request1.setUser(user);
        try {
            clientUtil.sendRequestToServer(request1, socket);
            Response response = clientUtil.receive(socket);
            answer1 = response.getBody();
            buffer.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer1;
    }
    public String goCommandFrame(String comand, LabWork labWork){
        String answer1 = "1";
            Request request1 = comandReader.getRequestFromFrameCommand(comand, labWork);
            request1.setUser(user);
            try {
                clientUtil.sendRequestToServer(request1, socket);
                Response response = clientUtil.receive(socket);
                answer1 = response.getBody();
                buffer.clear();

            } catch (IOException e) {
                e.printStackTrace();
            }
        return answer1;
    }

    public String goScriptCommand(String req1, String user){
        String answ = script.executeScript(req1, socket, buffer, user);
        return answ;
    }
}



        /**  while (true) {
                    authorization.userAuthReg();
                    Request authReq = new Request(user);
                    clientUtil.sendRequestToServer(authReq, socket);
                    Response response = clientUtil.receive(socket);
                    if (!authorization.userExist(response)) {
                        continue;
                    } else {
                        break;
                    }
                }
                user.setRegistration(false);
                user.setAuthorization(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
                /*System.out.println("Приложение запущено!");
                System.out.println("Имя пользователя: " + user.getUsername());
                System.out.println("Введите exit для заверешния работы приложения");
                while (true) {
                    System.out.println("Введите команду :");
                    String req = scanner.nextLine();
                    while (true) {
                        if (checkIfScriptCommand(req)) {
                            script.executeScript(req, socket, buffer);
                        } else {
                            Request request1 = comandReader.getRequestFromCommand(req, user);
                            request1.setUser(user);
                            if (request1.getCommand().equals("no_command")) {
                                req = scanner.nextLine();
                                continue;
                            }
                            clientUtil.sendRequestToServer(request1, socket);
                            System.out.println(clientUtil.receive(socket).getBody());
                            buffer.clear();
                            if (request1.getCommand().equals("exit")) {
                                System.out.println("Приложение завершает работу!");
                                System.exit(0);
                            }
                        }
                        req = scanner.nextLine();

                    }
                }
            } catch (SocketException e) {
                System.err.println("Нет подключения по данному порту!");
            } catch (IllegalArgumentException e2) {
                System.err.println("Нет соединения с сервером!");
            } catch (StreamCorruptedException e3) {
                System.err.println("Нет соединения с сервером!");
            } catch (NullPointerException e4) {
                System.out.println("Некорректная команда!");
            } catch (IOException e) {
                e.printStackTrace();
            }

    }*/



