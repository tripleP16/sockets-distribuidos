package client;
import common.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Smoker extends Thread {
    public void run(){
        try {
            Socket s = new Socket("127.0.0.1", 5056);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Ingredient infiniteIngredient = IngredientGenerator.generateIngredient();
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
                    }else {
                        dos.writeUTF("PLEASE");
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
                    dos.writeUTF("PLEASE");
                    tries = 0;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Smoker smoker = new Smoker();
        smoker.run();
    }
}
