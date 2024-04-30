package Java;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static ArrayList<String> names = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the server name
        System.out.println("\n\n");
        System.out.print("Enter the name of the central server: ");
        String serverName = scanner.nextLine();

        // Create a star network with the provided server name
        Star starNetwork = new Star(serverName);
        //System.out.println("Central server named '" + serverName + "' has been successfully added.");

        // Prompt user to enter the number of clients to add
        System.out.print("Enter the number of clients to add: ");
        int numClients = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Add clients to the network
        for (int i = 1; i <= numClients; i++) {
            System.out.println("\n\n");
            System.out.print("Enter the name of client " + i + ": ");
            String clientName = scanner.nextLine();
            names.add(clientName);
            starNetwork.insertNode(clientName);
            //System.out.println("Client '" + clientName + "' has been successfully added.");
        }

        System.out.println("\n\n");
        System.out.println("Clients Names: ");

// Get the keys (client IDs) from the connectedClients map
        Set<String> clientIDs = starNetwork.getConnectedClients().keySet();

// Print the client IDs
        for (String clientID : clientIDs) {
            System.out.println(clientID);
        }

        for (int i = 0; i <= numClients; i++) {

            System.out.println("\n\n");
            System.out.println("Enter sender name: ");
            String senderName = scanner.nextLine();
            System.out.println("Enter receiver name: ");
            String receiverName = scanner.nextLine();

            ClientNode sender = starNetwork.getConnectedClients().get(senderName);
            ClientNode receiver = starNetwork.getConnectedClients().get(receiverName);

            if (sender != null && receiver != null) {
                // Prompt user to enter the message
                System.out.println("\n\n");
                System.out.print("Enter the message to send from " + senderName + " to " + receiverName + ": ");
                String message = scanner.nextLine();

                // compressing the message
                String compressedMessage = HuffmanCompression.compress(message);

                sender.send(receiverName, compressedMessage); // Send the user-entered message
                System.out.println("\n\n\n");
                
                // Decode the encoded message
                //String receivedMessage = receiver.receive();
                String decodedMessage = HuffmanCompression.decompress(compressedMessage, HuffmanCompression.decOrigin(message));
                
                System.out.println("Received Message: \nFrom: "+senderName+" \nTo: " + receiverName + "\nMessage: " +decodedMessage);
            } else {
                System.out.println("Error: Sender or receiver not found.");
            }
        }


        scanner.close();
    }
}
