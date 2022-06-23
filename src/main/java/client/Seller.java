package client;

import common.Ingredient;
import common.IngredientGenerator;
import common.RandomBank;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Seller {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 5056);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            while (true)
            {
                String response = dis.readUTF();
                System.out.println(response);
                if(response.equals("ADD")){
                    int randomBank = RandomBank.randomBank();
                    Ingredient ingredient = IngredientGenerator.generateIngredient();
                    dos.writeUTF(ingredient.getId());
                    dos.writeUTF(String.valueOf(randomBank));
                }
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
