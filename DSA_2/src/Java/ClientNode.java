package Java;

public class ClientNode {
    // Instance variables
    private final String clientID;
    private final ServerNode server;

    // Constructor for ClientNode class
    public ClientNode(String clientID, ServerNode server) {
        this.clientID = clientID;
        this.server = server;
    }

    // Method to send a message from one client to another through the server
    public void send(String receiverID, String message) {
        // Send message to the server
        try {
            server.sendMessage(clientID, receiverID, message);
        } catch (IllegalArgumentException e) {
            // Log error if receiver is not connected to the network
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to receive a message from another client
    public synchronized String receive() {
        // Receive message from the server (waits for notification)
        while (true) {
            String message = server.getMessage(clientID);
            if (message != null) {
                return message;
            }
            // Wait for server notification
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error receiving message: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Getter for client ID
    public String getClientID() {
        return clientID;
    }
}
