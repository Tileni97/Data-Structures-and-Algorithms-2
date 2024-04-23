package Java;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerNode {

    // Instance variables
    private static final Logger LOGGER = Logger.getLogger(ServerNode.class.getName());

    private final String centralServer; // Server ID (name)
    private final List<ClientNode> connectedClients;
    private final Map<String, String> messageBuffer;

    // Constructor for ServerNode class
    // Initializes the ServerNode with the provided server name and initializes the lists and maps
    public ServerNode(String centralServer) {
        this.centralServer = centralServer;
        connectedClients = new ArrayList<>();
        messageBuffer = new HashMap<>();
    }

    // Method to send a message from one client to another
    public synchronized void sendMessage(String senderID, String receiverID, String message) {
        // Check if the receiver is connected to the network
        if (connectedClients.stream().anyMatch(client -> client.getClientID().equals(receiverID))) {
            // Add the message to the receiver's message buffer
            messageBuffer.put(receiverID, senderID + ": " + message);
            // Notify the receiver that a message has been received
            notifyAll(); // Notify waiting clients (in ClientNode.receive())
            // Log the message sent
            LOGGER.log(Level.INFO, "Message sent from {0} to {1}: {2}", new Object[]{senderID, receiverID, message});
        } else {
            // Log an error if the receiver is not connected to the network
            String errorMessage = "Error: Receiver " + receiverID + " not connected to the network.";
            LOGGER.log(Level.WARNING, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // Method to get the central server ID
    public String getCentralServer() {
        return centralServer;
    }

    // Method to get a message from the message buffer
    public synchronized String getMessage(String clientID) {
        // Check if the message buffer contains a message for the client
        if (messageBuffer.containsKey(clientID)) {
            // Get the message from the message buffer
            String message = messageBuffer.get(clientID);
            // Remove the message from the message buffer
            messageBuffer.remove(clientID);
            // Log the message received
            LOGGER.log(Level.INFO, "Message received by {0}: {1}", new Object[]{clientID, message});
            return message;
        } else {
            return null;
        }
    }

    // Method to connect a client to the server
    public void connectClient(ClientNode client) {
        // Add the client to the list of connected clients
        connectedClients.add(client);
        // Log the client connection
        LOGGER.log(Level.INFO, "Client {0} connected to server {1}", new Object[]{client.getClientID(), centralServer});
    }

    // Method to disconnect a client from the server
    public void disconnectClient(ClientNode client) {
        // Remove the client from the list of connected clients
        connectedClients.remove(client);
        LOGGER.log(Level.INFO, "Client {0} disconnected from server {1}", new Object[]{client.getClientID(), centralServer});
    }

    // Method to get a read-only view of the connected clients list
    public List<ClientNode> getConnectedClients() {
        return Collections.unmodifiableList(connectedClients);
    }
}
