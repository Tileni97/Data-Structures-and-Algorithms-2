package Java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manager GUI");

        // Manager GUI components
        Button createServerButton = new Button("Create Server");
        Button removeServerButton = new Button("Remove Server");
        Button launchServerButton = new Button("Launch Server");
        ListView<String> connectedClientsList = new ListView<>();
        Button createClientButton = new Button("Create Client");
        Button deleteClientButton = new Button("Delete Client");
        Button launchClientButton = new Button("Launch Client");

        // Layout for Manager GUI
        BorderPane layout = new BorderPane();

        VBox serverButtons = new VBox(10);
        serverButtons.getChildren().addAll(createServerButton, removeServerButton, launchServerButton);

        VBox clientButtons = new VBox(10);
        clientButtons.getChildren().addAll(createClientButton, deleteClientButton, launchClientButton);

        VBox clientInfo = new VBox(10);
        clientInfo.getChildren().addAll(new Label("Connected Clients:"), connectedClientsList);

        layout.setLeft(serverButtons);
        layout.setRight(clientButtons);
        layout.setCenter(clientInfo);
        layout.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }
}

class ServerGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Server GUI");

        // Server GUI components
        ListView<String> connectedClientsList = new ListView<>();
        ListView<String> encodedMessagesList = new ListView<>();
        TextArea messageDetails = new TextArea();

        // Layout for Server GUI
        BorderPane layout = new BorderPane();

        VBox serverInfo = new VBox(10);
        serverInfo.getChildren().addAll(new Label("Connected Clients:"), connectedClientsList, new Label("Encoded Messages:"), encodedMessagesList);

        layout.setLeft(serverInfo);
        layout.setCenter(messageDetails);
        layout.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }
}

class ClientGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Client GUI");

        // Client GUI components
        TextArea decodedMessagesTextArea = new TextArea();
        TextField composeMessageTextField = new TextField();
        Button sendMessageButton = new Button("Send Message");

        // Layout for Client GUI
        VBox layout = new VBox(10);
        layout.getChildren().addAll(decodedMessagesTextArea, composeMessageTextField, sendMessageButton);
        layout.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }
}
