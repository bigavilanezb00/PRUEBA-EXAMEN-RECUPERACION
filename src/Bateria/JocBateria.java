package Bateria;

import java.util.Scanner;

public class JocBateria {
    public static void main(String[] args) {
        Bateria bateria = new Bateria(100);
        Scanner scanner = new Scanner(System.in);

        int opcio = -1;
        while (opcio != 0) {
            System.out.println("Menu:");
            System.out.println("1 - Carregar bateria");
            System.out.println("2 - Jugar");
            System.out.println("3 - Info bateria");
            System.out.println("0 - Sortir");
            System.out.print("Selecciona una opció: ");
            opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    Thread carregarThread = new Thread(() -> {
                        while (!bateria.carregada()) {
                            bateria.carregar();
                            System.out.println("Carregant bateria... Nivell actual: " + bateria.getNivell() + "%");
                            try {
                                Thread.sleep(1000); // Espera de 1 segon abans de cada càrrega
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Bateria.Bateria carregada al 100%.");
                    });
                    carregarThread.start();
                    break;
                case 2:
                    if (bateria.carregada()) {
                        Thread descarregarThread = new Thread(() -> {
                            while (bateria.getNivell() > 0) {
                                bateria.descarregar();
                                System.out.println("Joc en marxa. Bateria.Bateria al " + bateria.getNivell() + "%.");
                                try {
                                    Thread.sleep(1000); // Espera de 1 segon abans de cada descàrrega
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("La bateria s'ha descarregat completament. Joc finalitzat.");
                        });
                        descarregarThread.start();
                    } else {
                        System.out.println("La bateria no està carregada al 100%. Carrega-la abans d'iniciar el joc.");
                    }
                    break;
                case 3:
                    System.out.println("Nivell de bateria: " + bateria.getNivell() + "%.");
                    break;
                case 0:
                    System.out.println("Sortint del programa...");
                    break;
                default:
                    System.out.println("Opció invàlida. Si us plau, selecciona una opció vàlida.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
}

