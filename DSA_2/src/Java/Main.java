package Java;

public class Main {

    public static void main(String[] args) {
        // Create a star network with a central server named "CentralServer"
        Star starNetwork = new Star("CentralServer");

        // Insert client nodes into the network
        starNetwork.insertNode("Client1");
        starNetwork.insertNode("Client2");
        starNetwork.insertNode("Client3");

        // Get a reference to the connected clients map for monitoring
        System.out.println("Connected Clients: " + starNetwork.getConnectedClients());

        // Send messages between client nodes
        ClientNode client1 = starNetwork.getConnectedClients().get("Client1");
        ClientNode client2 = starNetwork.getConnectedClients().get("Client2");
        ClientNode client3 = starNetwork.getConnectedClients().get("Client3");

        // Send messages from client1 to client2 and client3
        client1.send("Client2", "Hello from Client1 to Client2");
        client1.send("Client3", "Hello from Client1 to Client3");

        // Receive messages for client2 and client3
        System.out.println("Messages received by Client2: " + client2.receive());
        System.out.println("Messages received by Client3: " + client3.receive());

        // Delete a client node from the network
        starNetwork.deleteNode("Client3");

        // Get a reference to the updated connected clients map for monitoring
        System.out.println("Connected Clients after deletion: " + starNetwork.getConnectedClients());
    }
}
