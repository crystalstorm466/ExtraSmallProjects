import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) throws IOException {
        Scanner choice = new Scanner(System.in);
        System.out.println("[First Project]");
        System.out.println("_______________________");
        System.out.println("[ATM Machine]");
        System.out.println("[Main Menu]");
        boolean choicestllGo = true;
        String usernameInput = null;

        while (choicestllGo) {
            System.out.print("What would you like to do?\n" +
                    "1. Login\n" +
                    "2. Create an account: ");
            int mainChoice = choice.nextInt();
            if (mainChoice == 1) {
                usernameInput = login();
                choicestllGo = false;
            } else if (mainChoice == 2) {
                String newUser = createAccount();
                System.out.println( "New Account: " + newUser + " has been created.");
                System.out.print("Would you like to return to the main menu? (Y/N) ");
                char returnMenu = choice.next().charAt(0);
                if (returnMenu == 'Y') {
                    choicestllGo = true;
                } else if (returnMenu == 'N') {
                    choicestllGo = false;
                    System.exit(0);
                }
            } else {
                System.out.println("Invalid response. Please make sure you use a number.");
            }
        }


        choicestllGo = true;
        while (choicestllGo) {
            System.out.print("Welcome " + usernameInput + "! " +
                    "What would you like to do?\n" +
                    "1. List Transactions\n" +
                    "2. Create new transactions\n" +
                    "3. Exit ");
           int mainChoice = choice.nextInt();
           if (mainChoice == 1) {
               System.out.println("[Transactions]");
               Transactions.listTransactions(usernameInput);
           } else if (mainChoice == 2) {
               Transactions.createTransactions(usernameInput);
           } else if (mainChoice == 3) System.exit(67575656);
        }


        System.out.println(" ");
        System.out.println("_______________________");
        System.out.println("Please come back again!");

    }

    public static String login() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String usernameInput = input.next();
        System.out.print("Enter your password: ");
        String passwordInput = input.next();

        File accountsInfo = new File("src/accounts.txt");

        Scanner readingFile = new Scanner(accountsInfo);

        while (readingFile.hasNextLine()) {
            String realUserName = readingFile.nextLine();
            String realPassword = readingFile.nextLine();
            if (usernameInput.equalsIgnoreCase(realUserName) && passwordInput.equalsIgnoreCase(realPassword)) {
                System.out.println("Login Successful!");
                System.out.println("Welcome " + usernameInput);
                return (usernameInput);


            } else {
                System.out.println("Login Failed");;
            }
        }


        return null;
    }
    public static String createAccount() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("[Create an Account]");
        System.out.print("Enter a username: ");
        String newUser = input.nextLine();
        System.out.print("Enter a password: ");
        String newPass = input.nextLine();

        try(FileWriter fw = new FileWriter("src/accounts.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(newUser);
            out.println(newPass);
        } catch (IOException e) {
            System.out.println("An Error Occurred: " + e);
        }
        return newUser;
    }
}


