package Testing;

import Java.ClientNode;
import Java.ServerNode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ClientNodeTest {

    private ServerNode serverNode;

    @Before
    public void setUp() {
        serverNode = new ServerNode("MainServer");
    }

    @Test
    public void testSend() {
        ClientNode sender = new ClientNode("Sender", serverNode);
        ClientNode receiver = new ClientNode("Receiver", serverNode);

        serverNode.connectClient(sender);
        serverNode.connectClient(receiver);

        sender.send("Receiver", "Hello!");

        assertEquals("Sender: Hello!", receiver.receive());
    }

    // Add more test cases for send and receive methods as needed
}
