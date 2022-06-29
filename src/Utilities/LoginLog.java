package Utilities;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LoginLog {

    public LoginLog() {
    }

    public static void inputLog(String username) throws IOException {

        try {
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            // pw.println(ZonedDateTime.now() + " " + username + (successful ? " Successfully logged in." : " was denied access"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}