package Ascensor;

public class Main {
    public static void main(String[] args) {
        Ascensor ascensor = new Ascensor();

        Persona persona1 = new Persona("Persona 1", 0, 5, ascensor);
        Persona persona2 = new Persona("Persona 2", 3, 1, ascensor);
        Persona persona3 = new Persona("Persona 3", 2, 0, ascensor);

        Thread thread1 = new Thread(persona1);
        Thread thread2 = new Thread(persona2);
        Thread thread3 = new Thread(persona3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            ascensor.iniciar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
