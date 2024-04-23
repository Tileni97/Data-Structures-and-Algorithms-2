package Testing;

import Java.Star;
import org.junit.Test;
import static org.junit.Assert.*;

public class StarTest {

    @Test
    public void testInsertNode() {
        Star star = new Star("MainServer");

        // Add a new client
        star.insertNode("Client1");

        // Check if the client was added successfully
        assertTrue(star.getConnectedClients().containsKey("Client1"));
    }

    @Test
    public void testDeleteNode() {
        Star star = new Star("MainServer");

        // Add a new client
        star.insertNode("Client1");

        // Delete the client
        star.deleteNode("Client1");

        // Check if the client was removed successfully
        assertFalse(star.getConnectedClients().containsKey("Client1"));
    }
}
