package Server;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientHandler extends Thread{
    protected Socket socket = null; // client socket
    protected PrintWriter out = null; // used to write to the socket
    protected BufferedReader in = null; // used to read from the socket
    protected Vector moves = null; // used to store the files in the shared directory
    protected int currentClients = 0; // used to keep track of current clients
    protected String strUSR = null; // used to store clients name
    protected Map<String, Integer> scores = null; // used to keep scores

    /**
     * Constructor Class for the Handler
     * @param socket - client socket
     * @param moves - records the moves
     * @param currentClients - Active clients
     */
    public ClientHandler(Socket socket, Vector moves, int currentClients) {
        super();
        this.socket = socket;
        this.moves = moves;
        this.currentClients = currentClients;
        scores = new TreeMap<>();
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOException while opening a read/write connection");
        }
    }

    /**
     * Called when a command is requested from the socket
     */
    public void run(){
        Boolean endOfConnection = false;
        while(!endOfConnection){
            endOfConnection = processCommand(); // While loop runs for the socket wanting to remain open
        }
        try{
            socket.close(); // closes socket
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Function processes the command written to the socket
     * @return - Boolean statement if the socket will remain open or not
     */
    public boolean processCommand(){
        String message = null;
        try{
            message = in.readLine(); // Reads input to socket
        } catch (IOException e) {
            System.err.println("Error reading command from socket");
            return true;
        }
        if (null == message){
            return true;
        }
        StringTokenizer st = new StringTokenizer(message);
        String command = st.nextToken(); // gets command from the input string
        String args = null;
        if (st.hasMoreTokens()) { // gets the argument within the input string
            args = message.substring(command.length()+1, message.length());
        }
        return processCommand(command, args); // calls function to have the proper functionality
    }

    /**
     * This function will carry out the proper functionality of the command and respective arguments
     * @param command - Identifies the command the client wants to perform
     * @param args - Identifies the arguments to carry out the command
     * @return - Boolean statement if the program is requested to be closed
     */
    public boolean processCommand(String command, String args){
        if (command.equalsIgnoreCase("SETUSR")){ // Sets the Client user name
            synchronized(this){
                scores.put(args, 0);
                strUSR = args;
                currentClients++;
            }
            out.println("200 Username Set");
            return false;
        } else if (command.equalsIgnoreCase("GETUSR")){ // Gets the client user name
            out.println(strUSR);
            return false;
        } else if (command.equalsIgnoreCase("SETOP")) { // Sets the clients opponent
            synchronized(this){
                scores.put(args, 0);
            }
            out.println("200 Opponent Set");
            return false;
        } else if (command.equalsIgnoreCase("SETMOVE")){ // Sets the clients move for a round
            synchronized(this){
                moves.addElement("["+strUSR+"]: "+args);
            }
            out.println("200 Move was set");
            return false;
        } else if (command.equalsIgnoreCase("GETMOVE")){ // Get a client move
            int id = (new Integer(args)).intValue();
            String msg = null;
            msg = (String)moves.elementAt(id);
            out.println(msg);
            return false;
        } else if (command.equalsIgnoreCase("SETWIN")) { // Sets the win count for a player
            synchronized (this) {
                int wins = scores.get(args);
                System.out.print(wins++);
                scores.put(args, wins++);
            }
            out.println("200 Wins were set");
            return false;
        } else if (command.equalsIgnoreCase("GETWIN")){ // gets the win count for a player
            out.println(scores.get(args));
            return false;
        } else if (command.equalsIgnoreCase("GETCLIENTS")){ // gets the number of clients active
            out.println(currentClients);
            return false;
        } else if (command.equalsIgnoreCase("GETSIZE")){ // gets the size of the moves variable
            out.println(moves.size());
            return false;
        } else if (command.equalsIgnoreCase("LOGOUT")){ // logs out client
            out.println("200 Client Has Logged Out");
            return true;
        } else { // if the command is not recognized
            out.println("400 Unrecognized Command: "+command);
            return false;
        }
    }
}
