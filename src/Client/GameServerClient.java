package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameServerClient {
    private Socket socket = null; // used to store client socket
    private PrintWriter networkOut = null; // used to write to socket
    private BufferedReader networkIn = null; // used to read from socket

    //we can read this from the user too
    public static String SERVER_ADDRESS = "localhost"; // server address
    public static int    SERVER_PORT = 16789; // server port

    public GameServerClient(){
        try{
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT); // sets up socket
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: "+SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("IOException while connecting to server: "+SERVER_ADDRESS);
        }
        try {
            networkOut = new PrintWriter(socket.getOutputStream(), true); // sends up writer
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // sets up reader
        } catch (IOException e) {
            System.err.println("IOException while opening a read/write connection");
        }
    }

    public String getUSR(String username){
        return username;
    }

    public void setUSR(String username){

    }

    public void checkUserCount() {
        networkOut.println("GETCLIENTS");
        try {
            System.out.println("hello");
            String line = networkIn.readLine();
            int id = (new Integer(line)).intValue();
            if (id >= 2) {
                System.err.println("Too Many Players");
            }
        } catch (IOException e) {
            System.out.println("IOException while opening a read/write connection");
        }
    }
}
