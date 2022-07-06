package File;


import Collection.LabWork;

/**
 * Класс парсинга даты и времени
 */
public class DateTimeParser {

    public int getYear(String datetime) {
        String[] date = datetime.split("T");
        String year = date[0].split("-")[0];
        return Integer.parseInt(year);
    }

    public int getMonth(String datetime) {
        String[] date = datetime.split("T");
        String month = date[0].split("-")[1];
        return Integer.parseInt(month);
    }

    public int getDay(String datetime) {
        String[] date = datetime.split("T");
        String day = date[0].split("-")[2];
        return Integer.parseInt(day);
    }
}
