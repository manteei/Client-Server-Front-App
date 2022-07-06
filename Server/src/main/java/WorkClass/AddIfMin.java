package WorkClass;

import User.User;
import Collection.LabWork;
import CommandControl.CommandWithLabworks;
import DataBase.DBManager;
import User.CommandLine;

/**
 * Класс добавляет объект в коллекцию, если его значение поля "Х" меньше минимального в коллекции
 */
public class AddIfMin implements CommandWithLabworks {
    private User user;
    private LabWork newLab;
    private CommandLine commandLine;
    private LabWork labWork;
    public AddIfMin(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    /**
     * Метод добавления с консоли.
     * @return
     */
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        newLab = labWork;
        if (commandLine.findMinimalX()> newLab.getCoordinates().getX()) {
            if (DBManager.addLabwork(labWork, user)) {
                synchronized (commandLine) {
                    commandLine.insert(newLab);
                    commandLine.sortCollection();
                }
                stringBuilder.append("addresult" + "namein1" + newLab.getName());
            } else {
                stringBuilder.append("addresult2" + "bdex");
            }
        } else {
            stringBuilder.append("addresult2" + "addex5");
        }
        return stringBuilder.toString();
    }

    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }

    @Override
    public void setLabworks(LabWork lab) {
        this.labWork = lab;
    }
}

