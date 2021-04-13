package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Sever which controls the connection of clients to the server
 */
public class ServerTester {
    protected Socket clientSocket           = null; // used to store client socket
    protected ServerSocket serverSocket     = null; // used to store server socket
    protected int numClients                = 0; // used to keep track of number of clients
    protected ClientHandler[] threads = null; // used for each new connection
    protected Vector moves = new Vector(); // used to store all the moves

    public static int PORT = 16789; // server port
    public static int MAX_CLIENTS = 50; // Max Clients Allowed

    public ServerTester() {
        try{
            serverSocket = new ServerSocket(PORT);
            System.out.println("---------------------------");
            System.out.println("Rock/Paper/Scissors Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: "+PORT);
            threads = new ClientHandler[MAX_CLIENTS];
            while(true){
                clientSocket = serverSocket.accept(); // sets up client socket
                System.out.println("Rock/Paper/Scissors Client connected.");
                threads[numClients] = new ClientHandler(clientSocket, moves, numClients); // creates new handler connection
                threads[numClients].start(); // starts new handler connection
                numClients++; // increments number of clients
            }
        } catch (IOException e) {
            System.err.println("IOException while creating server connection");
        }
    }

    /**
     * Instantiates a new Server
     * @param args - Command line arguments
     */
    public static void main(String[] args) {ServerTester server = new ServerTester(); }
}
