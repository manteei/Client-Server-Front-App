package WorkClass;

import User.User;
import User.CommandLine;
import CommandControl.CommandWithArgument;

/**
 * Класс выводит элементы, чье поле минимального балла равно указанному.
 * filter_by_minimal_point
 */
public class FilterByMinimalPoint implements CommandWithArgument {
    private String arguments;
    private CommandLine commandLine;
    private User user;

    public FilterByMinimalPoint(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    @Override
    public String execute() {
        Double minPoint;
        String result;
        try {
            minPoint = Double.parseDouble(arguments);
            result = commandLine.filterMinPoint(minPoint);
            if (result.equals("")) {
                return "filterex1";
            } else return result;
        }catch (ArrayIndexOutOfBoundsException e){
            return ("updateres3");
        }
    }
    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }


    @Override
    public void getArgument(String arguments) {
        this.arguments = arguments;
    }
}
