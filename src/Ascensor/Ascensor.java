package Ascensor;

import java.util.ArrayList;
import java.util.List;

public class Ascensor {
    public static final int NUM_PISOS = 11;
    public static final int CAPACITAT_MAXIMA = 10;

    public int pisActual;
    public int personesDins;
    public boolean ple;
    public boolean pujant;

    public List<Persona> personesEsperant;

    public Ascensor() {
        pisActual = 0;
        personesDins = 0;
        ple = false;
        pujant = true;
        personesEsperant = new ArrayList<>();
    }

    public synchronized void entrar(Persona persona) throws InterruptedException {
        while (ple || pisActual != persona.getPisOrigen()) {
            wait();
        }

        personesDins++;
        if (personesDins == CAPACITAT_MAXIMA) {
            ple = true;
        }

        System.out.println(persona.getNom() + " ha entrat a l'ascensor al pis " + pisActual);
        notifyAll();
    }

    public synchronized void sortir(Persona persona) throws InterruptedException {
        while (pisActual != persona.getPisDesti()) {
            wait();
        }

        personesDins--;
        if (personesDins == 0) {
            ple = false;
        }

        System.out.println(persona.getNom() + " ha sortit de l'ascensor al pis " + pisActual);
        notifyAll();
    }

    public synchronized void moure() throws InterruptedException {
        if (pujant) {
            if (pisActual < NUM_PISOS - 1) {
                pisActual++;
            } else {
                pujant = false;
                pisActual--;
            }
        } else {
            if (pisActual > 0) {
                pisActual--;
            } else {
                pujant = true;
                pisActual++;
            }
        }

        System.out.println("L'ascensor està al pis " + pisActual);
        notifyAll();
    }

    public synchronized void afegirPersonaEsperant(Persona persona) {
        personesEsperant.add(persona);
    }

    public synchronized void iniciar() throws InterruptedException {
        while (true) {
            moure();

            List<Persona> personesPendents = new ArrayList<>();
            for (Persona persona : personesEsperant) {
                if (persona.getPisOrigen() == pisActual) {
                    personesPendents.add(persona);
                }
            }

            for (Persona persona : personesPendents) {
                if (!ple) {
                    personesEsperant.remove(persona);
                    entrar(persona);
                }
            }

            for (Persona persona : personesEsperant) {
                if (persona.getPisDesti() == pisActual) {
                    personesEsperant.remove(persona);
                    sortir(persona);
                }
            }

            Thread.sleep(1000); // Simulem el temps que triga a moure's l'ascensor
        }
    }
}
