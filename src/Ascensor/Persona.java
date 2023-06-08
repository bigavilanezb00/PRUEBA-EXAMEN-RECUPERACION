package Ascensor;

public class Persona implements Runnable {
    public String nom;
    public int pisOrigen;
    public int pisDesti;
    public Ascensor ascensor;

    public Persona(String nom, int pisOrigen, int pisDesti, Ascensor ascensor) {
        this.nom = nom;
        this.pisOrigen = pisOrigen;
        this.pisDesti = pisDesti;
        this.ascensor = ascensor;
    }

    public String getNom() {
        return nom;
    }

    public int getPisOrigen() {
        return pisOrigen;
    }

    public int getPisDesti() {
        return pisDesti;
    }

    @Override
    public void run() {
        try {
            ascensor.afegirPersonaEsperant(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
