package Java;

import java.util.PriorityQueue;
import java.util.HashMap;

public class HuffmanCompression  {

    // Node class for constructing Huffman tree
    static class Node implements Comparable<Node> {
        char character;
        int frequency;
        Node left, right;

        Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        // Compare nodes based on frequency
        public int compareTo(Node other) {
            return this.frequency - other.frequency;
        }
    }

    // Method to build Huffman tree
    public static Node buildHuffmanTree(HashMap<Character, Integer> frequencyMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        // Create a leaf node for each character and add to priority queue
        for (char c : frequencyMap.keySet()) {
            priorityQueue.offer(new Node(c, frequencyMap.get(c)));
        }

        // Merge nodes to form Huffman tree
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            Node parent = new Node('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            priorityQueue.offer(parent);
        }

        // Return root of Huffman tree
        return priorityQueue.poll();
    }

    // Method to generate Huffman codes
    // recursively traverse the Huffman tree and store the codes in a HashMap
    public static void generateHuffmanCodes(Node root, String code, HashMap<Character, String> huffmanCodes) {
        if (root == null) return;

        // Leaf node: store the code for character
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        // Traverse left child with code '0'
        generateHuffmanCodes(root.left, code + '0', huffmanCodes);

        // Traverse right child with code '1'
        generateHuffmanCodes(root.right, code + '1', huffmanCodes);
    }

    // Method to compress a message using Huffman codes
    public static String compress(String message) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        // Count frequency of each character in message
        for (char c : message.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman tree
        Node root = buildHuffmanTree(frequencyMap);

        // Generate Huffman codes
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);

        // Compress message using Huffman codes
        StringBuilder compressedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            compressedMessage.append(huffmanCodes.get(c));
        }

        return compressedMessage.toString();
    }

    // Method to decompress a Huffman-encoded message
    public static String decompress(String compressedMessage, Node root) {
        StringBuilder decompressedMessage = new StringBuilder();
        Node currentNode = root;

        for (char bit : compressedMessage.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.left;
            } else if (bit == '1') {
                currentNode = currentNode.right;
            }

            // If it's a leaf node, append the character to the decompressed message and reset currentNode to root
            if (currentNode.left == null && currentNode.right == null) {
                decompressedMessage.append(currentNode.character);
                currentNode = root;
            }
        }

        return decompressedMessage.toString();
    }

    public static Node decOrigin(String word){
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : word.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman tree
        Node root = buildHuffmanTree(frequencyMap);
        return root;
    }

}
