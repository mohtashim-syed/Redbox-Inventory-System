import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        BinTree<DVD> DVD_inventory = new BinTree<>();
        Scanner scanner = new Scanner(System.in);

        // Reading inventory file
        System.out.print("Inventory File name? : ");
        String inventoryFileName = scanner.nextLine();

        try (Scanner inventoryFileScanner = new Scanner(new File(inventoryFileName))) {
            while (inventoryFileScanner.hasNextLine()) {
                String line = inventoryFileScanner.nextLine();
                String[] lineArray = line.split(",");

                String title = lineArray[0].trim().replaceAll("\"", "");
                int available = Integer.parseInt(lineArray[1].trim());
                int rented = Integer.parseInt(lineArray[2].trim());

                DVD item = new DVD(title, available, rented);
                DVD_inventory.insert(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file invalid");
            return;
        }

        // Reading transactions file
        System.out.print("Transactions File name? : ");
        String transactionsFileName = scanner.nextLine();

        try (Scanner transactionsFileScanner = new Scanner(new File(transactionsFileName))) {
            while (transactionsFileScanner.hasNextLine()) {
                String line = transactionsFileScanner.nextLine();
                String[] fields = line.split(" ", 2);

                String operator = fields[0];
                String operand = fields[1];

                switch (operator) {
                    case "add":
                        String[] strArray = operand.split(",");
                        String addTitle = strArray[0].trim().replaceAll("\"", "");
                        int addAvailable = Integer.parseInt(strArray[1].trim());
                        DVD addDVD = new DVD(addTitle, addAvailable, 0);

                        if (DVD_inventory.search(addDVD) == null) {
                            DVD_inventory.insert(addDVD);
                        } else {
                            DVD existingDVD = DVD_inventory.search(addDVD);
                            existingDVD.incrementAvailable(addAvailable);
                        }
                        break;

                    case "remove":
                        String[] removeArray = operand.split(",");
                        String removeTitle = removeArray[0].trim().replaceAll("\"", "");
                        int removeAmount = Integer.parseInt(removeArray[1].trim());

                        DVD removeDVD = DVD_inventory.search(new DVD(removeTitle, 0, 0));

                        if (removeDVD != null) {
                            removeDVD.decrementAvailable(removeAmount);

                            if (removeDVD.getAvailable() == 0 && removeDVD.getRented() == 0) {
                                DVD_inventory.delete(removeDVD);
                            }
                        }
                        break;

                    case "rent":
                        String rentTitle = operand.trim().replaceAll("\"", "");
                        DVD rentDVD = DVD_inventory.search(new DVD(rentTitle, 0, 0));

                        if (rentDVD != null) {
                            rentDVD.incrementRented();
                            rentDVD.decrementAvailable(1);
                        }
                        break;

                    case "return":
                        String returnTitle = operand.trim().replaceAll("\"", "");
                        DVD returnDVD = DVD_inventory.search(new DVD(returnTitle, 0, 0));

                        if (returnDVD != null) {
                            returnDVD.decrementRented();
                            returnDVD.incrementAvailable(1);
                        }
                        break;

                    default:
                        System.out.println("Invalid operator in transactions file: " + operator);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Transactions file");
            return;
        }

        // Display in-order report
        System.out.println("In-Order Report:");
        DVD_inventory.printTree();

        // Print preorder report to a file
      //  System.out.print("Preorder Report File name? : ");
      //  String preorderReportFileName = scanner.nextLine();

        try (PrintStream preorderReportStream = new PrintStream(new File("inventory.out"))) {
            DVD_inventory.printTreePreOrder(preorderReportStream);
        } catch (FileNotFoundException e) {
            System.out.println("Preorder report file invalid");
            return;
        }

        System.out.println("Done!");
    }
}
