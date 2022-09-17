import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) throws IOException {

        System.out.println("[First Project]");
        System.out.println("_______________________");
        System.out.println("[ATM Machine]");
        System.out.println("_______________________");
        String usernameInput = login();
        System.out.println("[Transactions]");
        listTransactions(usernameInput);
        System.out.println(" ");
        System.out.println("_______________________");
        System.out.println("Please come back again!");

    }

    public static void listTransactions(String usernameInput) throws IOException {
        File TransactionsFile = new File("src/Transactions.txt");
        Scanner readingFile = new Scanner(TransactionsFile);
        BufferedReader br = new BufferedReader(new FileReader("src/Transactions.txt"));
        String usernameInput1 = usernameInput;
        int lineCount = 0;
        if (usernameInput1.equalsIgnoreCase("user1")) {
            lineCount = 4;
        } else if (usernameInput1.equalsIgnoreCase("user2")) {
            lineCount = 8;
        } else if (usernameInput.equalsIgnoreCase("admin")) {
            lineCount = 0;
        } //TODO I want to be able make this a better system of identifying user

        while (readingFile.hasNextLine()) {
            String formatTransact = Files.readAllLines(Paths.get("src/Transactions.txt")).get(lineCount);
            lineCount = lineCount + 1;
            if ((formatTransact.equalsIgnoreCase("END"))) {
                break;
            }
            System.out.println(formatTransact);

        }


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
}
