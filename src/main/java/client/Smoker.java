package client;

import common.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Smoker {
    public static void main(String[] args){
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket("192.168.1.8", 5056);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Ingredient infiniteIngredient = IngredientGenerator.infiniteIngredient();
            SmokerStock stock = new SmokerStock(infiniteIngredient);
            int tries = 0;
            while (true)
            {
                if (tries < 2){
                    Cigarrette cigarrette = null;
                    dos.writeUTF("NEEDED");
                    String response = dis.readUTF();
                    if(!response.equals("NO MORE")){
                        Ingredient ingredient = new Ingredient(response);
                        stock.addIngredient(ingredient);
                    }
                    CigarreteBuilder builder = new CigarreteBuilder(stock, infiniteIngredient);
                    cigarrette = builder.build();
                    stock.setStock(builder.getStock().getStock());
                    tries ++;
                    if(cigarrette != null){
                        cigarrette.Smoke();
                        tries = 0;
                    }


                }else {
                    dos.writeUTF("ADD");
                    tries = 0;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
