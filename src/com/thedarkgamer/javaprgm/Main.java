package com.thedarkgamer.javaprgm;

import java.io.Console;
import java.io.File;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
	    //Assign args
        String enableLog = FileClass.readline(new File("C:\\Program Files\\TDGApps\\Java\\Java Prgm\\settings.txt"), 4);
        String debugTrue = FileClass.readline(new File("C:\\Program Files\\TDGApps\\Java\\Java Prgm\\settings.txt"), 7);
        String COF = FileClass.readline(new File("C:\\Program Files\\TDGApps\\Java\\Java Prgm\\settings.txt"), 10);

        boolean enableLogging = enableLog.equalsIgnoreCase("true") ? true : false;
        boolean isDebugTrue = debugTrue.equalsIgnoreCase("true") ? true : false;
        boolean crashOnFailure  = COF.equalsIgnoreCase("true") ? true : false;

        args = new String[] {
            "v0.01", //Version
            System.getProperty("user.name"), //User
            String.valueOf("null"), //Log File
            String.valueOf(enableLogging),
            String.valueOf(isDebugTrue), //Is Debug True
            String.valueOf(crashOnFailure) //Crash on Failure
        };
        if (args[3] == String.valueOf(true)) {
            System.out.println("Logging enabled.");

            File log = Logger.init(false);
            args[2] = String.valueOf(log);
            System.out.println(args[2]);

            Wait.Sleep(1000); //Wait 1 second
            System.out.println("Log being created at: " + args[2]); //Print log file location
        } else {
            System.out.println("Logging disabled.");
        }
        Logger.Log("Main - Startup", new File(args[2]), "MAIN"); //Log type "MAIN"
        if (args[4] == "true") {
            Logger.Log("Debug Enabled", new File(args[2]), "DEBUG"); //Log type "DEBUG" if debugging enabled
        } else {
            Logger.Log("Debug - Disabled or Incorrect", new File(args[2]), "DEBUG"); //Log type "DEBUG" if debugging false or configured incorrectly
            Logger.Log("To turn DEBUG on, go to settings.txt and change the \"isDebugTrue\" line to \"true\" ", new File(args[2]), "DEBUG"); //Log "DEBUG" if debugging false or configured incorrectly
        }
        runtime(args); //Go to runtime
    }

    public static void runtime(String[] runArgs) {
        Logger.Log("Runtime - Loaded", new File(runArgs[2]), "INFO"); //Log type "INFO"
        if (runArgs[5] == "true") {
            Logger.Log("Crash on Failure IS enabled", new File(runArgs[2]), "WARN"); //Log type "WARN" if COF is enabled
        } else {
            Logger.Log("Crash on Failure is disabled", new File(runArgs[2]), "WARN"); // Log type "WARN" if COF is disabled
        }
    }

    public static void Crash(int ErrorNum) {
        System.exit(ErrorNum);
    }
}
