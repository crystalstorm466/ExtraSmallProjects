import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Transactions {


    public static void listTransactions(String usernameInput) throws IOException {
        File TransactionsFile = new File("src/Transactions.txt");
        Scanner readingFile = new Scanner(TransactionsFile);
        BufferedReader br = new BufferedReader(new FileReader("src/Transactions.txt"));
        int lineCount = 0;
        if (usernameInput.equalsIgnoreCase("user1")) {
            lineCount = 4;
        } else if (usernameInput.equalsIgnoreCase("user2")) {
            lineCount = 8;
        } else if (usernameInput.equalsIgnoreCase("admin")) {
            lineCount = 0;
        } else {
            System.out.println("Account Not Found: " + usernameInput);
        }
        //TODO I want to be able make this a better system of identifying user

        while (readingFile.hasNextLine()) {
            String formatTransact = Files.readAllLines(Paths.get("src/Transactions.txt")).get(lineCount);
            lineCount = lineCount + 1;
            if ((formatTransact.equalsIgnoreCase("END"))) {
                break;
            }
            System.out.println(formatTransact);

        }


    }

    enum InsertPosition {
        BEFORE, AFTER
    }
    public static void createTransactions(String usernameInput) throws IOException {
        System.out.println("[Create Transactions]");
        System.out.print("What is the name and the price of the transaction you want to add: ");
        Scanner scan = new Scanner(System.in);
        String newTransactUser = scan.next();

        Path path = Paths.get("src/Transactions.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String extraLine = newTransactUser;
        int position = findPosition(lines, usernameInput, InsertPosition.AFTER);
        lines.add(position, extraLine);
        Files.write(path, lines, StandardCharsets.UTF_8);
        System.out.println("Successfully written! Please come again!");
        System.exit(0);
        }



    public static int findPosition(List<String> lines, String lineToLookFor, InsertPosition position) {

        for (int i = 0; i < lines.size(); i++) {
            if (lineToLookFor.equals(lines.get(i))) {
                if (position == InsertPosition.BEFORE) {
                    return i;
                } else if (position == InsertPosition.AFTER) {
                    return i + 1;
                }
            }
        }

        return -1;
    }
}

