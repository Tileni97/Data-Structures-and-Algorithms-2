package Java;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Star {
    // Instance variables
    //This line declares a Logger instance named LOGGER for logging messages within the class.
    private static final Logger LOGGER = Logger.getLogger(Star.class.getName());

    //This line declares a Java.ServerNode instance named centralServer to represent the central server in the network.
    private final ServerNode centralServer;

    //This line declares a Map instance named connectedClients to store the connected clients in the network.
    private final Map<String, ClientNode> connectedClients;

    // Constructor for Java.Star class
    //Initialises the Java.Star network with the central server node, given the server name and initialises the connectedClients map.
    public Star(String serverName) {
        centralServer = new ServerNode(serverName);
        connectedClients = new HashMap<>();
    }

    // Method to send a message from one client to another
    // Adds a client node to the network
    public void insertNode(String clientID) {
        // Check if the client is already connected to the network
        if (!connectedClients.containsKey(clientID)) {
            // Add the client to the network
            ClientNode newClient = new ClientNode(clientID, centralServer);
            // Add the client to the connected clients map
            connectedClients.put(clientID, newClient);
            centralServer.connectClient(newClient);
            // Log the client added to the network
            LOGGER.log(Level.INFO, "Client {0} added to the network", clientID);
        } else {
            // Log a warning if the client is already connected to the network
            LOGGER.log(Level.WARNING, "Client {0} already exists in the network", clientID);
        }
    }

    // Deletes a client node from the network
    public void deleteNode(String clientID) {
        // Check if the client is connected to the network
        if (connectedClients.containsKey(clientID)) {
            // Remove the client from the network
            ClientNode clientToRemove = connectedClients.get(clientID);
            // Remove the client from the connected clients map
            connectedClients.remove(clientID);
            centralServer.disconnectClient(clientToRemove);
            // Log the client removed from the network
            LOGGER.log(Level.INFO, "Client {0} removed from the network", clientID);
        } else {
            // Log a warning if the client is not connected to the network
            LOGGER.log(Level.WARNING, "Client {0} not found in the network", clientID);
        }
    }

    // Getter for connected clients (for monitoring purposes)
    public Map<String, ClientNode> getConnectedClients() {
        return connectedClients;
    }
}
