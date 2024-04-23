package Testing;

import Java.ClientNode;
import Java.ServerNode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ServerNodeTest {

    private ServerNode serverNode;

    @Before
    public void setUp() {
        serverNode = new ServerNode("MainServer");
    }

    @Test
    public void testConnectClient() {
        ClientNode clientNode = new ClientNode("Client1", serverNode);
        serverNode.connectClient(clientNode);
        assertTrue(serverNode.getConnectedClients().contains(clientNode));
    }

    @Test
    public void testDisconnectClient() {
        ClientNode clientNode = new ClientNode("Client2", serverNode);
        serverNode.connectClient(clientNode);
        serverNode.disconnectClient(clientNode);
        assertTrue(serverNode.getConnectedClients().isEmpty());
    }
}