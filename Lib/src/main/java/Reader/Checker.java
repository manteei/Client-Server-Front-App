package Reader;

import Collection.*;
import File.DateTimeParser;
import User.CommandLine;

/**
 * Класс для проверки корректности данных в файле
 */
public class Checker {
    private CommandLine commandLine;

    public Checker(CommandLine commandLine){
        this.commandLine = commandLine;
    }


    public String chekName(String name){
        if (name.equals("null")){
            return "nameEx1";
        } if(name.equals("")){
            return "nameEx2";
        } return "";

    }
    public String chekX(String coordx){

        try {
            if (coordx.equals("null") | coordx.equals("")){
                return "coordex1";

            }

        } catch (NullPointerException e ){
            return "coordex4";

        } catch (NumberFormatException e1){
            return "coordex5";
        }
        return "";
    }
    public String chekY(String coordy){
        try {
            if (coordy.equals("null") | coordy.equals("")) {
                return "coordex2";

            } else if (Long.valueOf(coordy) <= -732) {
                return "coordex3";

            }
        } catch (NullPointerException e ){
            return "coordex4";

        } catch (NumberFormatException e1){
            return "coordex5";
        }
        return "";
    }
    public String checkMinimalPoint(String point){
        try {
            if (point.equals("null") | point.equals("")) {
                return "minex1";

            } else if (Double.valueOf(point) <= 0) {
                return "minex2";

            }
        } catch (NumberFormatException e){
            return "minex3";
        }
        return "";
    }
    public String checkDifficulty(String diff){
        try {
            if (diff.equals("") | diff.equals("null")) {
                return "difex1";
            }
        } catch (NullPointerException e){
            return "difex1";
        }
        return "";
    }
    public String checkDate(String date){
        DateTimeParser dateTimeParser = new DateTimeParser();
        try {
            if (dateTimeParser.getYear(date) > 2022) {
                return  "dateex1";

            }
            if (dateTimeParser.getMonth(date) > 12 | dateTimeParser.getMonth(date) < 1) {
                return "dateex2";

            }
            if (dateTimeParser.getDay(date) > 31 | dateTimeParser.getDay(date) < 1) {
                return "dateex3";

            }

            if (date.equals("null") || date.equals("")) {
                return "dateex4";

            }
        }catch (NullPointerException e) {
            return "dateex5";

        }
        return "";
    }
    public String checkAuName(String auname){
        if (auname.equals("null")){
            return "aunameex1";
        } if(auname.equals("")){
            return "aunameex2";
        } return "";

    }
    public String checkWeight(String weight){
        try {
            if (weight.equals("null")) {
                return "weightex1";
            } else if (Double.valueOf(weight) <= 0) {
                return "weightex2";
            }
        }catch (NumberFormatException e1){
            return "weightex3";
        }
        return "";
    }
    public String checkPassportId(String passport){
        if (passport.equals("null")) {
            return "passportex1";
        }
        if (passport.equals("")) {
            return "passportex2";
        }
        return "";
    }
    public String checkColor(String color){
        try {
            if (color.equals("null") | color.equals("")) {
                return "colorex1";
            }
        }catch (NullPointerException e){
             return "colorex1";
        }
        return "";
    }
    public String checkNationality(String country){
        try {
            if (country.equals("null") | country.equals("")) {
                return "countryex1";
            }
        }catch (NullPointerException e){
            return "countryex1";
        }
        return "";
    }

    public String IdStock(String id){
        if (id.equals("null")) {
            return "idex1";
        }
        if (id.equals("")) {
            return "idex2";
        }
        try {
            for (LabWork lab : commandLine.getLabworks()) {
                if (lab.getId() == Integer.valueOf(id)) {
                    return "idex4";
                }
            }
        }catch (NumberFormatException e){
            return "idex3";
        }
        return "";
    }

}
