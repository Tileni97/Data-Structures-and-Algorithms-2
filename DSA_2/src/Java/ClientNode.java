package Java;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientNode {
    // Instance variables
    private static final Logger LOGGER = Logger.getLogger(ClientNode.class.getName());

    private final String clientID; // Stores the unique ID or name for the client node. Marked as final since it should not change after initialisation.
    private final ServerNode server; // Stores the server node that the client is connected to. Marked as final since it should not change after initialisation.


    // Constructor for Java.ClientNode class
    //Initialises the Java.ClientNode with the provided client name and server
    public ClientNode(String clientID, ServerNode server) {
        this.clientID = clientID;
        this.server = server;
    }

    // Method to send a message from one client to another
    // Sends message to another client through the server
    public void send(String receiverID, String message) {
        // Send message to the server
        try {
            server.sendMessage(clientID, receiverID, message);
            LOGGER.log(Level.INFO, "Message sent from {0} to {1}: {2}", new Object[]{clientID, receiverID, message});
        } catch (IllegalArgumentException e) {
            // Log error if receiver is not connected to the network
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    // Method to receive a message from another client
    // Receives message from the server (waits for notification)
    public synchronized String receive() {
        // Check for messages from the server
        while (true) {
            String message = server.getMessage(clientID);
            if (message != null) {
                LOGGER.log(Level.INFO, "Message received by {0}: {1}", new Object[]{clientID, message});
                return message;
            }
            // Wait for server notification
            try {
                wait(); // Wait for server notification
            } catch (InterruptedException e) {
                // Log error if interrupted
                LOGGER.log(Level.SEVERE, "Error receiving message: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Getter for client ID
    // Returns the client ID
    public String getClientID() {
        return clientID;
    }
}
