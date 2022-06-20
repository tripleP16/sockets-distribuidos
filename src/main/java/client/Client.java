package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args){
        try {
            Socket sc = new Socket("localhost", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            TimeUnit.SECONDS.sleep(10);
            out.writeUTF("NEEDED");
            String message = in.readUTF();
            if(message.equals("GRANTED")) {
                System.out.println("Access granted");
            } else {
                System.out.println("Access denied");
            }
            out.writeUTF("FINISHED");
            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
