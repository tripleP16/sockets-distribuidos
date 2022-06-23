package server;

import common.Ingredient;
import common.IngredientGenerator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientHandler extends Thread {
        DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
        final DataInputStream dis;
        final DataOutputStream dos;
        final Socket s;


        // Constructor
        public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
        {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
        }

        @Override
        public void run()
        {
            String received;
            String toreturn;
            ArrayList<Bank> banks = new ArrayList<Bank>();
            banks.add(new Bank(new ArrayList<Ingredient>()));
            banks.add(new Bank(new ArrayList<Ingredient>()));
            while (true)
            {
                try {

                    // Ask user what he wants
//                    dos.writeUTF("What do you want?[Date | Time]..\n"+
//                            "Type Exit to terminate connection.");

                    // receive the answer from client
                    received = dis.readUTF();

                    if(received.equals("Exit"))
                    {
                        System.out.println("Client " + this.s + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        break;
                    }

                    // creating Date object
                    Date date = new Date();

                    // write on output stream based on the
                    // answer from the client
                    switch (received) {

                        case "ADD" :
                            System.out.println("AQUI");

                            Ingredient ingredient = new Ingredient( dis.readUTF());
                            int bankId = Integer.parseInt(dis.readUTF());
                            banks.get(bankId).pushIngredient(ingredient);
                            System.out.println(banks.size());
                            break;
                        case "PLEASE":
                            System.out.println("AQUI");
                            dos.writeUTF("ADD");
                            break;
//                        default:
//                            dos.writeUTF("NO MORE");
////                            Ingredient ingredient2 = IngredientGenerator.generateIngredient();
////                            dos.writeUTF(ingredient2.getId());
//                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try
            {
                // closing resources
                this.dis.close();
                this.dos.close();

            }catch(IOException e){
                e.printStackTrace();
            }
        }

}
