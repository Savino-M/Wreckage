package com.goodluckhavefun.wreckage.general;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Salvataggio {

    public static void salva(Mappa mappa, Giocatore giocatore) { //salva la partita su file

	FileOutputStream outFile = null;
	ObjectOutputStream outStream = null;
	FileOutputStream outFile1 = null;
	ObjectOutputStream outStream1 = null;

	try {
	    outFile = new FileOutputStream("res/saves/Mappa.dat");
	    outFile1 = new FileOutputStream("res/saves/Player.dat");

	    outStream = new ObjectOutputStream(outFile);
	    outStream1 = new ObjectOutputStream(outFile1);

	    outStream.writeObject(mappa);
	    outStream1.writeObject(giocatore);

	} catch (Exception e) {
	    System.out.println(e);
	}

	try {
	    outFile.close();
	    outStream.close();
	    outFile1.close();
	    outStream1.close();
	} catch (IOException e) {
	    System.out.println(e);
	}

    }

    public static Mappa caricaMappa() { //carica stato della mapppa da file

	FileInputStream inFile = null;
	ObjectInputStream inStream = null;
	Mappa mappa = null;

	try {  //apertura file
	    inFile = new FileInputStream("res/saves/Mappa.dat");
	    inStream = new ObjectInputStream(inFile);
	    mappa = (Mappa) inStream.readObject();
	} catch (Exception e) {
	    System.out.println(e);
	}

	try {  //chiusura file
	    inStream.close();
	    inFile.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
	return mappa;
    }

    public static Giocatore caricaGiocatore() { //carica stato del giocatore da file

	FileInputStream inFile = null;
	ObjectInputStream inStream = null;
	Giocatore giocatore = null;

	try {
	    inFile = new FileInputStream("res/saves/Player.dat");
	    inStream = new ObjectInputStream(inFile);
	    giocatore = (Giocatore) inStream.readObject();

	} catch (Exception e) {
	    System.out.println(e);
	}

	try {
	    inStream.close();
	    inFile.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
	return giocatore;
    }

    public static boolean esistePartita() { //verifica se su file è già presente una partita

	boolean esito = false;

	FileInputStream inFile = null;
	ObjectInputStream inStream = null;

	try {
	    inFile = new FileInputStream("res/saves/Player.dat");
	    inStream = new ObjectInputStream(inFile);

	    if (inStream.readObject() != null) {
		esito = true;
	    }

	} catch (Exception e) {
	}

	try {
	    inStream.close();
	    inFile.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
	return esito;
    }

    public static void reset() { //reset totale dei file di mappa e giocatore

	FileOutputStream outFile = null;
	ObjectOutputStream outStream = null;
	FileOutputStream outFile1 = null;
	ObjectOutputStream outStream1 = null;

	try {
	    outFile = new FileOutputStream("res/saves/Mappa.dat");
	    outFile1 = new FileOutputStream("res/saves/Player.dat");

	    outStream = new ObjectOutputStream(outFile);
	    outStream1 = new ObjectOutputStream(outFile1);

	    outStream.writeObject(null);
	    outStream1.writeObject(null);

	} catch (Exception e) {
	    System.out.println(e);
	}

    }

}
