package Utilities;


import DAO_DBAccess.UsersDAO;
import Model.Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LoginLog {

    static Users currentUser;


    public LoginLog() {
    }

    public static void inputLog(String username, boolean successful) {

        try {
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            currentUser = UsersDAO.getCurrentUser();
            pw.println(ZonedDateTime.now() + " " + username + (successful ? " Successfully logged in." : " was denied access"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}