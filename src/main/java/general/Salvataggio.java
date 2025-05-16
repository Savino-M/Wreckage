package general;

import java.io.*;

public class Salvataggio {

    public static void salva(Mappa mappa, Giocatore giocatore) { // salva la partita su file

        FileOutputStream outFile = null;
        ObjectOutputStream outStream = null;
        FileOutputStream outFile1 = null;
        ObjectOutputStream outStream1 = null;

        try {

            outFile = new FileOutputStream("Java/Wreckage/res/saves/mappa.dat");
            outFile1 = new FileOutputStream("Java/Wreckage/res/saves/player.dat");

            outStream = new ObjectOutputStream(outFile);
            outStream1 = new ObjectOutputStream(outFile1);

            outStream.writeObject(mappa);
            outStream1.writeObject(giocatore);

            outFile.close();
            outStream.close();
            outFile1.close();
            outStream1.close();

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    public static Mappa caricaMappa() { // carica stato della mapppa da file

        FileInputStream inFile = null;
        ObjectInputStream inStream = null;
        Mappa mappa = null;

        try { // apertura file

            inFile = new FileInputStream("Java/Wreckage/res/saves/mappa.dat");
            inStream = new ObjectInputStream(inFile);
            mappa = (Mappa) inStream.readObject();

            inStream.close();
            inFile.close();

        } catch (Exception e) {

            System.out.println(e);

        }

        return mappa;

    }

    public static Giocatore caricaGiocatore() { // carica stato del giocatore da file

        FileInputStream inFile = null;
        ObjectInputStream inStream = null;
        Giocatore giocatore = null;

        try {

            inFile = new FileInputStream("Java/Wreckage/res/saves/player.dat");
            inStream = new ObjectInputStream(inFile);
            giocatore = (Giocatore) inStream.readObject();

            inStream.close();
            inFile.close();

        } catch (Exception e) {

            System.out.println(e);

        }

        return giocatore;

    }

    public static boolean esistePartita() { // verifica se su file è già presente una partita

        boolean esito = false;

        FileInputStream inFile = null;
        ObjectInputStream inStream = null;

        try {

            inFile = new FileInputStream("Java/Wreckage/res/saves/mappa.dat");
            inStream = new ObjectInputStream(inFile);

            if (inStream.readObject() != null) {

                esito = true;

            }

            inStream.close();
            inFile.close();

        } catch (Exception e) {

            System.out.println(e);

        }

        return esito;

    }

    public static void reset() { // reset totale dei file di mappa e giocatore

        ObjectOutputStream outStream = null;
        ObjectOutputStream outStream1 = null;

        try (FileOutputStream outFile = new FileOutputStream("Java/Wreckage/res/saves/mappa.dat");
                FileOutputStream outFile1 = new FileOutputStream("Java/Wreckage/res/saves/player.dat")) {

            outStream = new ObjectOutputStream(outFile);
            outStream1 = new ObjectOutputStream(outFile1);

            outStream.writeObject(null);
            outStream1.writeObject(null);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

}
