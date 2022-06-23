package client;

import common.Ingredient;


import java.util.concurrent.TimeUnit;

public class Cigarrette {
    private Ingredient first;
    private Ingredient second;
    private Ingredient third;

    public Cigarrette(Ingredient first, Ingredient second, Ingredient third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void Smoke(){
        try {
            System.out.println("Usando: " + first.getId());
            System.out.println("Usando: " + second.getId());
            System.out.println("Terminando de armar el cigarro con: " + third.getId());
            System.out.println("Fumando.......");
            TimeUnit.SECONDS.sleep(3);
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
