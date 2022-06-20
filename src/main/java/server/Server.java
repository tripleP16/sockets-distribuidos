package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args)  {
        ServerSocket server = null;
        Socket sc = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        ArrayList<Socket> clients = new ArrayList<Socket>();
        final int port = 5000;
        try {
            server = new ServerSocket(port);
            System.out.println("Server is running on port " + port);
            while (true) {
                sc = server.accept();
                in = new DataInputStream(sc.getInputStream());
                if(in.readUTF().equals("NEEDED")) {
                    if (clients.size() == 0) {
                        out = new DataOutputStream(sc.getOutputStream());
                        out.writeUTF("GRANTED");
                    } else {
                        out = new DataOutputStream(clients.get(0).getOutputStream());
                        out.writeUTF("DENIED");
                    }
                    clients.add(sc);
                }
                if(in.readUTF().equals("FINISHED")) {
                    System.out.println("Client disconnected");
                    sc.close();
                    clients.remove(sc);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
