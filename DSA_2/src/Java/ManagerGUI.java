package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ManagerGUI {
    private static Map<String, Star> servers = new HashMap<>();
    private static DefaultListModel<String> connectedClientsListModel = new DefaultListModel<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Manager GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Manager GUI components
        JButton createServerButton = new JButton("Create Server");
        JButton removeServerButton = new JButton("Remove Server");
        JButton launchServerButton = new JButton("Launch Server");
        JList<String> connectedClientsList = new JList<>(connectedClientsListModel);
        JButton createClientButton = new JButton("Create Client");
        JButton deleteClientButton = new JButton("Delete Client");
        JButton launchClientButton = new JButton("Launch Client");

        // Add action listeners to buttons
        createServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverName = JOptionPane.showInputDialog(frame, "Enter the server name:");
                if (serverName != null && !serverName.isEmpty()) {
                    Star server = new Star(serverName);
                    servers.put(serverName, server);
                    JOptionPane.showMessageDialog(frame, "Server '" + serverName + "' created successfully.");
                }
            }
        });

        createClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverName = JOptionPane.showInputDialog(frame, "Enter the server name:");
                if (servers.containsKey(serverName)) {
                    String clientName = JOptionPane.showInputDialog(frame, "Enter the client name:");
                    if (clientName != null && !clientName.isEmpty()) {
                        servers.get(serverName).insertNode(clientName);
                        connectedClientsListModel.addElement(clientName);
                        JOptionPane.showMessageDialog(frame, "Client '" + clientName + "' created successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Server '" + serverName + "' not found.");
                }
            }
        });

        removeServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverName = JOptionPane.showInputDialog(frame, "Enter the server name:");
                if (servers.containsKey(serverName)) {
                    servers.remove(serverName);
                    JOptionPane.showMessageDialog(frame, "Server '" + serverName + "' removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Server '" + serverName + "' not found.");
                }
            }
        });

        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverName = JOptionPane.showInputDialog(frame, "Enter the server name:");
                if (servers.containsKey(serverName)) {
                    String clientName = JOptionPane.showInputDialog(frame, "Enter the client name:");
                    if (servers.get(serverName).getConnectedClients().containsKey(clientName)) {
                        servers.get(serverName).deleteNode(clientName);
                        connectedClientsListModel.removeElement(clientName);
                        JOptionPane.showMessageDialog(frame, "Client '" + clientName + "' deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Client '" + clientName + "' not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Server '" + serverName + "' not found.");
                }
            }
        });

        // Layout for Manager GUI
        JPanel panel = new JPanel(new BorderLayout());
        JPanel serverPanel = new JPanel(new GridLayout(3, 1));
        JPanel clientPanel = new JPanel(new GridLayout(3, 1));

        serverPanel.add(createServerButton);
        serverPanel.add(removeServerButton);
        serverPanel.add(launchServerButton);

        clientPanel.add(createClientButton);
        clientPanel.add(deleteClientButton);
        clientPanel.add(launchClientButton);

        panel.add(serverPanel, BorderLayout.WEST);
        panel.add(clientPanel, BorderLayout.EAST);
        panel.add(new JScrollPane(connectedClientsList), BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}

class ServerGUI {
    private static JTextArea encodedMessagesTextArea = new JTextArea();
    private static JList<String> connectedClientsList = new JList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Server GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Server GUI components
        JLabel connectedClientsLabel = new JLabel("Connected Clients:");
        JLabel encodedMessagesLabel = new JLabel("Encoded Messages:");
        JScrollPane connectedClientsScrollPane = new JScrollPane(connectedClientsList);
        JScrollPane encodedMessagesScrollPane = new JScrollPane(encodedMessagesTextArea);

        // Layout for Server GUI
        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());

        leftPanel.add(connectedClientsLabel, BorderLayout.NORTH);
        leftPanel.add(connectedClientsScrollPane, BorderLayout.CENTER);

        rightPanel.add(encodedMessagesLabel, BorderLayout.NORTH);
        rightPanel.add(encodedMessagesScrollPane, BorderLayout.CENTER);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    // Update connected clients list
    public static void updateConnectedClientsList(Map<String, ClientNode> connectedClients) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String clientID : connectedClients.keySet()) {
            model.addElement(clientID);
        }
        connectedClientsList.setModel(model);
    }

    // Update encoded messages
    public static void updateEncodedMessages(String message) {
        encodedMessagesTextArea.append(message + "\n");
    }
}

/*public class ClientGUI {
    private static JTextArea decodedMessagesTextArea = new JTextArea();
    private static JTextField composeMessageTextField = new JTextField();
    private static List<String> sentMessages = new ArrayList<>();  // Corrected declaration

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Client GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Client GUI components
        JButton sendMessageButton = new JButton("Send Message");

        // Layout for Client GUI
        JPanel panel = new JPanel(new BorderLayout());
        JPanel composePanel = new JPanel(new BorderLayout());

        panel.add(new JScrollPane(decodedMessagesTextArea), BorderLayout.CENTER);
        composePanel.add(composeMessageTextField, BorderLayout.CENTER);
        composePanel.add(sendMessageButton, BorderLayout.EAST);
        panel.add(composePanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        // Add action listener to send message button
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = composeMessageTextField.getText();
                if (!message.isEmpty()) {
                    decodedMessagesTextArea.append("You: " + message + "\n");
                    sentMessages.add(message);
                    composeMessageTextField.setText("");
                    // Send message to all connected clients (method to be implemented)
                    sendMessageToAllClients(sentMessages); // Pass the correct list type
                }
            }
        });
    }

    // Method to display received messages
    public static void displayReceivedMessage(String sender, String message) {
        decodedMessagesTextArea.append(sender + ": " + message + "\n");
    }

    // Method to send message to all connected clients (to be implemented)
    private static void sendMessageToAllClients(List<String> messages) { // Corrected parameter type
        // Implement sending message to all connected clients
    }
}*/
