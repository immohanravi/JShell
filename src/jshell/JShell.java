/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jshell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

/**
 *
 * @author mohan
 */
public class JShell {

    /**
     */
    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_BLACK = "\u001B[30m";
    public final String ANSI_RED = "\u001B[1;31m";
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_CYAN = "\u001B[36m";
    public final String ANSI_WHITE = "\u001B[37m";
    public final String ANSI_CLS = "\u001b[2J";
    public final String ANSI_HOME = "\u001b[H";

    final String shell = ANSI_RED + "JShell-$ " + ANSI_RESET;
    Scanner in = new Scanner(System.in);
    String input = "";

    public JShell() {
        System.out.println(ANSI_GREEN + "Welcome to Shell");
        System.out.println(ANSI_YELLOW + "Type help to get informations...\n");
        
        do {
            System.out.print(shell);
            input = in.nextLine();
            if (operations(input)) {
                executeCommand(input);
            } else {
                System.out.println(input + ": command not found");
            }
        } while (true);

    }

    public void clearScreen() {
        System.out.print(ANSI_CLS
                + ANSI_HOME);
    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    /*
    public static String commandRouting(String command) {
        String output = "";
        switch (command) {
            case "ls": {
                output = executeCommand(command);
                break;
            }
        }
        return output;
    }*/
    public boolean operations(String input) {
        switch (input) {
            case "help": {
                System.out.println("Type Options to get list of Avalible Options");
                System.out.println("Type back to get back to previous session");
                System.out.println("Type exit to quit JSHELL");
                return true;
            }
            case "exit": {
                return exit();
            }

           
        }
        return true;
    }

    public boolean exit() {
        System.out.print("Are u sure do u want to exit? Y/N: ");
        do {
            String ans = in.nextLine();
            if (ans.toLowerCase().equals("y")) {
                System.exit(0);
            } else if (ans.toLowerCase().equals("n")) {
                return false;
            } else {
                System.out.print("invalid input try again! Y/N: ");
            }
        } while (true);

    }

    
}
