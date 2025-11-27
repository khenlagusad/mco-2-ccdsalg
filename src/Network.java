import java.util.*;

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

    public void addConnection(int userIndex, int friendIndex){
        LinkedList<Node> connections = friendGraph.get(userIndex);
        connections.add(friendGraph.get(friendIndex).get(0));
    }


    public void breadthFirstSearch(int userIndex, int userIndex2){
        Queue<Integer> connections = new LinkedList<>();
        boolean[] visited = new boolean[friendGraph.size()];
        int parent[] = new int[friendGraph.size()];
        Arrays.fill(parent, -1);

        connections.add(userIndex);
        visited[userIndex] = true;

        boolean found = false;

        while (connections.size() != 0){
            int current = connections.poll();

            if (current == userIndex2){
                found = true;
                break;
            }

            for (int i = 1; i < friendGraph.get(current).size(); i++){
                int neighbor = friendGraph.get(current).get(i).getAccNum();

                if (!visited[neighbor]){
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    connections.add(neighbor);
                }
            }
        }

        if (!found){
            System.out.println("Cannot find connection between user " + userIndex + " and " + userIndex2);
            return;
        }

        LinkedList<Node> pathBackwards = new LinkedList<>();
        int current = userIndex2;

        while (current != -1){
            pathBackwards.addFirst(new Node(current));

            if (current == userIndex){
                break;
            }

            current = parent[current];
        }

        for (int i = 0; i < pathBackwards.size() - 1; i++){
            System.out.println(pathBackwards.get(i).getAccNum() + " is friends with " + pathBackwards.get(i + 1).getAccNum());
        }

    }





}
