package Java;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the server name
        System.out.println("\n\n");
        System.out.print("Enter the name of the central server: ");
        String serverName = scanner.nextLine();

        // Create a star network with the provided server name
        Star starNetwork = new Star(serverName);

        // Prompt user to enter the number of clients to add
        int numClients = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter the number of clients to add: ");
                numClients = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer for the number of clients.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        // Add clients to the network
        for (int i = 1; i <= numClients; i++) {
            System.out.println("\n\n");
            boolean added = false;
            while (!added) {
                System.out.print("Enter the name of client " + i + ": ");
                String clientName = scanner.nextLine();
                if (!starNetwork.getConnectedClients().containsKey(clientName)) {
                    starNetwork.insertNode(clientName);
                    added = true;
                } else {
                    System.out.println("Error: Client ID '" + clientName + "' is already connected to the network. Please enter a different client ID.");
                }
            }
        }

        // Print the names of connected clients
        System.out.println("\n\nClients Names: ");
        Set<String> clientIDs = starNetwork.getConnectedClients().keySet();
        for (String clientID : clientIDs) {
            System.out.println(clientID);
        }

        boolean continueMessaging = true;
        while (continueMessaging) {
            System.out.println("\n\n");
            System.out.println("1. Send message");
            System.out.println("2. Add client");
            System.out.println("3. Delete client");
            System.out.println("4. List connected clients");
            System.out.println("5. Disconnect");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Sending a message
                    sendMessage(starNetwork, scanner);
                    break;
                case 2:
                    // Adding a client
                    addClient(starNetwork, scanner);
                    break;
                case 3:
                    // Delete client
                    deleteClient(starNetwork, scanner);
                    break;
                case 4:
                    // List connected clients
                    listConnectedClients(starNetwork);
                    break;
                case 5:
                    // Disconnect
                    continueMessaging = false;
                    System.out.println("Disconnecting from the network...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }

        scanner.close();
    }

    // Method to send a message between clients
    // Method to send a message between clients
    private static void sendMessage(Star starNetwork, Scanner scanner) {
        // Prompt user to enter sender name
        System.out.print("Enter sender name: ");
        String senderName = scanner.nextLine();

        // Prompt user to enter receiver name
        System.out.print("Enter receiver name: ");
        String receiverName = scanner.nextLine();

        // Get sender and receiver nodes from the star network
        ClientNode sender = starNetwork.getConnectedClients().get(senderName);
        ClientNode receiver = starNetwork.getConnectedClients().get(receiverName);

        // Check if sender and receiver are valid
        if (sender != null && receiver != null) {
            // Prompt user to enter the message
            System.out.println("\nEnter the message to send from " + senderName + " to " + receiverName + ": ");
            String message = scanner.nextLine();

            // Compress the message
            String compressedMessage = HuffmanCompression.compress(message);
            sender.send(receiverName, compressedMessage); // Send the user-entered message
            System.out.println("\n\n\n");

            // Display the received encoded message
            System.out.println("Received Encoded Message: \nFrom: " + senderName + " \nTo: " + receiverName + "\nMessage: " + compressedMessage);

            // Decode the encoded message
            String decodedMessage = HuffmanCompression.decompress(compressedMessage, HuffmanCompression.decOrigin(message));

            System.out.println("Received Decoded Message: \nFrom: " + senderName + " \nTo: " + receiverName + "\nMessage: " + decodedMessage);
        } else {
            System.out.println("Error: Sender or receiver not found.");
        }
    }


    // Method to add a new client to the network
    private static void addClient(Star starNetwork, Scanner scanner) {
        System.out.print("Enter the name of the new client: ");
        String newClientName = scanner.nextLine();
        starNetwork.insertNode(newClientName);
        System.out.println("Client ID '" + newClientName + "' has been successfully added to the network.");
    }

    // Method to delete a client from the network
    private static void deleteClient(Star starNetwork, Scanner scanner) {
        System.out.print("Enter the name of the client to delete: ");
        String clientToDelete = scanner.nextLine();
        starNetwork.deleteNode(clientToDelete);
    }

    // Method to list connected clients
    private static void listConnectedClients(Star starNetwork) {
        System.out.println("\nConnected Clients: ");
        Set<String> clientIDs = starNetwork.getConnectedClients().keySet();
        for (String clientID : clientIDs) {
            System.out.println(clientID);
        }
    }
}
