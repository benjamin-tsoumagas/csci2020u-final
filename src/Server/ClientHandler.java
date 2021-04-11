package Server;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientHandler extends  Thread{
    protected Socket socket = null; // client socket
    protected PrintWriter out = null; // used to write to the socket
    protected BufferedReader in = null; // used to read from the socket
    protected Vector moves = null; // used to store the files in the shared directory
    protected int currentClients = 0;
    protected String strUSR = null;
    protected Map<String, Integer> scores = null;
    protected Boolean isActive = null;



    public ClientHandler(Socket socket, Vector moves, int currentClients) {
        super();
        this.socket = socket;
        this.moves = moves;
        this.currentClients = currentClients;
        scores = new TreeMap<>();
        isActive = true;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOException while opening a read/write connection");
        }
    }

    public void run(){
        Boolean endOfConnection = false;
        while(!endOfConnection){
            endOfConnection = processCommand();
        }
        try{
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean processCommand(){
        String message = null;
        try{
            message = in.readLine();
        } catch (IOException e) {
            System.err.println("Error reading command from socket");
            return true;
        }
        if (null == message){
            return true;
        }
        StringTokenizer st = new StringTokenizer(message);
        String command = st.nextToken();
        String args = null;
        if (st.hasMoreTokens()) {
            args = message.substring(command.length()+1, message.length());
        }
        return processCommand(command, args);
    }

    public boolean processCommand(String command, String args){
        if (command.equalsIgnoreCase("SETUSR")){
            synchronized(this){
                scores.put(args, 0);
                strUSR = args;
                currentClients++;
            }
            out.println("200 Username Set");
            return false;
        } else if (command.equalsIgnoreCase("GETUSR")){
            out.println(strUSR);
            return false;
        } else if (command.equalsIgnoreCase("SETOP")) {
            synchronized(this){
                scores.put(args, 0);
                currentClients++;
            }
            out.println("200 Opponent Set");
            return false;
        } else if (command.equalsIgnoreCase("SETMOVE")){
            synchronized(this){
                moves.addElement("["+strUSR+"]: "+args);
            }
            out.println("200 Move was set");
            return false;
        } else if (command.equalsIgnoreCase("GETMOVE")){
            int id = (new Integer(args)).intValue();
            String msg = null;
            msg = (String)moves.elementAt(id);
            out.println(msg);
            return false;
        } else if (command.equalsIgnoreCase("SETWIN")) {
            synchronized (this) {
                int wins = scores.get(args);
                System.out.print(wins++);
                scores.put(args, wins++);
            }
            out.println("200 Wins were set");
            return false;
        } else if (command.equalsIgnoreCase("GETWIN")){
            out.println(scores.get(args));
            return false;
        } else if (command.equalsIgnoreCase("GETCLIENTS")){
            System.out.println(currentClients);
            out.println(currentClients);
            return false;
        } else if (command.equalsIgnoreCase("GETSTATE")){
            out.println(isActive);
            return false;
        } else if (command.equalsIgnoreCase("GETSIZE")){
            out.println(moves.size());
            return false;
        } else if (command.equalsIgnoreCase("RESET")){
            Vector moves = null; // used to store the files in the shared directory
            int currentClients = 0;
            String strUSR = null;
            Map<String, Integer> scores = null;
            Boolean isActive = false;
            Boolean added = null;
            out.println("200 Client Has Reset System");
            return true;
        } else if (command.equalsIgnoreCase("LOGOUT")){
            out.println("200 Client Has Logged Out");
            return true;
        } else {
            out.println("400 Unrecognized Command: "+command);
            return false;
        }
    }
}
