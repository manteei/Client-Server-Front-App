package WorkClass;

import DataBase.DBManager;
import User.User;
import Collection.LabWork;
import User.CommandLine;
import CommandControl.CommandWithArgLab;

/**
 * Класс обновляет значение элемента.
 */
public class Update implements CommandWithArgLab {
    private String arguments;
    private CommandLine commandLine;
    private LabWork lab;
    User user;

    public Update(CommandLine commandLine){
        this.commandLine = commandLine;
    }
    /**
     * Метод обновляет элемент с консоли.
     * @return
     */
    @Override
    public String execute() {
        int oldId;
        try {
            oldId = Integer.parseInt(arguments);
                if (commandLine.containsCollection(oldId, user)) {
                    if (DBManager.updateById(lab, oldId)){
                        synchronized (commandLine) {
                            commandLine.removeById(oldId);
                            commandLine.update(lab, oldId);
                            commandLine.sortCollection();
                        }
                        return "updateres1";
                } else {
                    return "updateres5";
                }
            }else {
                    return "updateres2";
                }
        } catch (ArrayIndexOutOfBoundsException e){
            return "updateres3";
        } catch (NumberFormatException e1){
            return "updateres4";
        }
    }

    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }

    @Override
    public void setLabworks(LabWork lab) {
        this.lab = lab;
    }

    @Override
    public void getArgument(String arguments) {
        this.arguments = arguments;
    }
}
