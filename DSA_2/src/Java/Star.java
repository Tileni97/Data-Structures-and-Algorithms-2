package Java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Star {
    // Instance variables
    private final ServerNode centralServer;
    private final Map<String, ClientNode> connectedClients;
    private final Set<String> uniqueClientIDs;

    // Constructor for Star class
    public Star(String serverName) {
        centralServer = new ServerNode(serverName); // Create a new server node
        connectedClients = new HashMap<>(); // Initialize the map
        uniqueClientIDs = new HashSet<>(); // Initialize the set
        System.out.println("Central server named '" + serverName + "' has been successfully added.");
    }

    // Method to add a client node to the network
    public void insertNode(String clientID) {
        // Check if the client ID is already present
        if (uniqueClientIDs.contains(clientID)) {
            System.out.println("Error: Client ID '" + clientID + "' is already connected to the network. Please enter a different client ID.");
            return; // Exit the method if client ID is already present
        }

        // Add the client to the network
        ClientNode newClient = new ClientNode(clientID, centralServer);
        connectedClients.put(clientID, newClient);
        centralServer.connectClient(newClient);
        uniqueClientIDs.add(clientID); // Add client ID to the set

        System.out.println("Client ID '" + clientID + "' has been successfully added to the network.");
    }

    // Method to delete a client node from the network
    public void deleteNode(String clientID) {
        // Check if the client is connected to the network
        if (connectedClients.containsKey(clientID)) {
            // Remove the client from the network
            ClientNode clientToRemove = connectedClients.get(clientID);
            // Remove the client from the connected clients map
            connectedClients.remove(clientID);
            centralServer.disconnectClient(clientToRemove);
            uniqueClientIDs.remove(clientID); // Remove client ID from the set
            System.out.println("Client ID '" + clientID + "' has been successfully removed from the network.");
        } else {
            System.out.println("Error: Client ID '" + clientID + "' is not connected to the network.");
        }
    }

    // Getter for connected clients (for monitoring purposes)
    public Map<String, ClientNode> getConnectedClients() {
        return connectedClients;
    }
}
