Data Structures and Algorithms 2 (DSA711S) - Star Topology Network Simulation
This project simulates a local area network (LAN) with a star topology, where a central server node communicates with multiple client nodes. The simulation is implemented in Java and focuses on the following aspects:

Key Components:
ServerNode: Represents the central node of the network, responsible for relaying messages between client nodes.
ClientNode: Represents individual nodes connected to the server, each with a unique ID and the ability to send and receive messages.
Star: Models the overall network structure, providing methods to add (insertNode) and remove (deleteNode) nodes.
Functionality:

Message Brokering: The ServerNode acts as a message broker, facilitating communication between client nodes.
Send and Receive: ClientNode objects can send messages to other clients through the server, and the receiving client displays the message and sender's ID.
Network Management: The Star class allows for dynamic addition and removal of nodes in the network.
Data Compression:

Huffman Coding: The project utilizes Huffman coding to compress messages transmitted between clients. Huffman coding is a lossless compression technique that assigns shorter codes to more frequent characters, reducing the overall size of the message.
Implementation: The implementation includes building a Huffman tree based on character frequencies in the message, generating Huffman codes, and encoding/decoding messages using these codes.
Worst-Case Time Complexity: The worst-case time complexity of Huffman coding is O(n log n), where n is the number of unique characters in the message. This is due to the need to build the Huffman tree, which involves sorting the characters based on frequency.
How to Run:

Compile the Java classes: javac ServerNode.java ClientNode.java Star.java Main.java 
Run the simulation: java Main

Additional Notes:
This project was completed as part of the DSA711S course.
The code is well-documented with comments explaining the implementation details.
Feel free to explore and extend the functionality of the simulation.
