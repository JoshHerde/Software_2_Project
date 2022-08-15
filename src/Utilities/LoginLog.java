package Utilities;


import DAO_DBAccess.UsersDAO;
import Model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class LoginLog {

    static Users currentUser;


    public LoginLog() {
    }

    public static void inputLog(String username, boolean successful) {


        try {
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            // timezone convert to UTC
            String methods = ZonedDateTime.now().toString() + " " + username;
            if (successful) {
                methods += " Successfully logged in.";
            }
            else {
                methods += " was denied access";
            }
            pw.println(methods);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

// todo convert local time to UTC then print that time.
//
