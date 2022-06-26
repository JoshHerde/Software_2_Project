package Utilities;

import Controller.LoginController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LoginLog {

    static User currentUser;

    static ZoneId zoneId = ZoneId.of("UTC");

    static DateTimeFormatter globalFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm:ssa z");

    public static void inputLog(int logEntry) throws IOException {

        LocalDateTime currentUTC = LocalDateTime.now(zoneId);
        ZonedDateTime zonedDateTime = currentUTC.atZone(zoneId);
        String dateTime = globalFormat.format(zonedDateTime);

        try {
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
        }
    }
}
