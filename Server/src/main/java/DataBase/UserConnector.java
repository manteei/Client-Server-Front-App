package DataBase;

import User.User;

public class UserConnector {

    public String connectUser(User user, Response response){
        if (user.isRegistration()){
            if (DBManager.addUserToDataBase(user.getUsername(),user.getPassword())){
                response.setRegistered(true);
                return "registrationResult";
            } else {
                response.setRegistered(false);
                return "noregister";
            }
        }
        if (user.isAuthorization()){
            String resp = DBManager.authorize(user.getUsername(),user.getPassword());
            if (resp.equals("authorizationResult")) {
                response.setAuthorized(true);
                return "authorizationResult";
            }
            return resp;
        }
        return "Authorize";
    }
}
