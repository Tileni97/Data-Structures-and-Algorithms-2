package Java;

import java.util.HashMap;
import java.util.Map;

public class Star {
    // Instance variables
    private final ServerNode centralServer;
    private final Map<String, ClientNode> connectedClients;

    // Constructor for Star class
    public Star(String serverName) {
        centralServer = new ServerNode(serverName);
        connectedClients = new HashMap<>();
        System.out.println("Central server named '" + serverName + "' has been successfully added.");
    }

    // Method to add a client node to the network
    public void insertNode(String clientID) {
        // Check if the client is already connected to the network
        if (!connectedClients.containsKey(clientID)) {
            // Add the client to the network
            ClientNode newClient = new ClientNode(clientID, centralServer);
            // Add the client to the connected clients map
            connectedClients.put(clientID, newClient);
            centralServer.connectClient(newClient);

            // Troubleshooting logs
            System.out.println("Client '" + clientID + "' has been successfully added to the network.");
        } else {
            // Troubleshooting logs if client already exists
            System.out.println("Error: Client '" + clientID + "' is already connected to the network.");
        }
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
        }
    }

    // Getter for connected clients (for monitoring purposes)
    public Map<String, ClientNode> getConnectedClients() {
        return connectedClients;
    }
}
