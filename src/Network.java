import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class Network {
    private ArrayList<LinkedList<Node>> friendGraph;

    public Network(int numOfAccounts){
        friendGraph = new ArrayList<>();

        for (int i = 0; i < numOfAccounts; i++){
            LinkedList<Node> connections = new LinkedList<>();
            connections.add(new Node(i));
            friendGraph.add(connections);
        }
    }

    public void addConnection(int userIndex, int friendIndex){
        LinkedList<Node> connections = friendGraph.get(userIndex);
        connections.add(friendGraph.get(friendIndex).get(0));
    }

    public void displayFriendlist(int userIndex){
        try{
            LinkedList<Node> connections = friendGraph.get(userIndex);

            int numFriends = connections.size()-1;

            System.out.printf("Person %d has %d friends\n", userIndex, numFriends);

            if (numFriends <= 0){
                return;
            }

            System.out.print("List of friends: ");
            for (int i = 1; i < connections.size(); i++){
                System.out.print(connections.get(i).getAccNum() + " ");
            }
            System.out.println();
            System.out.println();
        } catch (IndexOutOfBoundsException e){
            System.out.println("User does not exist!");
        } catch (InputMismatchException e){
            System.out.println("Invalid input");
        }
    }
}
