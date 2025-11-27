import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Input file name: ");
        String filename = scan.nextLine().trim();
        String filepath = "data/" + filename + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String firstLine = br.readLine();
            String[] firstParts = firstLine.split(" ");
            int numAccounts = Integer.parseInt(firstParts[0]);

            Network network = new Network(numAccounts);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int friend = Integer.parseInt(parts[0]);
                int user = Integer.parseInt(parts[1]);

                network.addConnection(user, friend);
            }

            System.out.println("Network loaded successfully!");
            System.out.println("===============================");

            int choice = -1;
            do {
                try {
                    System.out.println("MAIN MENU");
                    System.out.println(" [1] Get friend list");
                    System.out.println(" [2] Get connection");
                    System.out.println(" [3] Exit");
                    System.out.print("\nEnter your choice: ");
                    choice = scan.nextInt();
                    System.out.println();

                    if (choice == 1){
                        System.out.print("Enter ID of Person: ");

                        try{
                            int id = scan.nextInt();
                            network.displayFriendlist(id);
                        } catch (InputMismatchException e){
                            System.out.println("Invalid input!\n");
                        }
                    } else if (choice == 2){
                        System.out.println("Enter ID of Person: ");
                        int user1 = scan.nextInt();
                        System.out.println("Enter ID of Friend: ");
                        int user2 = scan.nextInt();
                        network.breadthFirstSearch(user1, user2);

                    } else if (choice == 3){
                        System.out.println("Exiting...");
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (InputMismatchException e){
                    System.out.println("Invalid input!");
                }
            } while (choice != 3);

        } catch (FileNotFoundException e){
            System.out.println("File Not Found!");
        } catch (IOException e){
            System.out.println("Error Reading File!");
            e.printStackTrace();
        } finally {
            System.out.println("===============================\n\n\n");
        }
    }
}