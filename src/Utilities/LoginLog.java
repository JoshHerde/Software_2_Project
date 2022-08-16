package Utilities;


import Controller.LoginController;
import DAO_DBAccess.UsersDAO;
import Model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LoginLog {

    static Users currentUser;
    static ZoneId utcID = ZoneId.of("UTC");
    //static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("On 'MM-dd-yyyy' at 'HH:mm' , ");


    public LoginLog() {
    }

    public static void inputLog(String username, boolean successful) {

        LocalDateTime nowUTC = LocalDateTime.now(utcID);
        ZonedDateTime zoneUTC = nowUTC.atZone(utcID);
        String dateTime = zoneUTC.toString() + " " + username;


        try {
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            currentUser = LoginController.getCurrentUser();
            if (successful) {
                dateTime += " successfully logged in.";
            }
            else {
                dateTime += " was denied access";
            }
            pw.println(dateTime);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


