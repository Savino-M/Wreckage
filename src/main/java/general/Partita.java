package general;

import java.util.Scanner;

import items.Oggetto;

public class Partita {

    private Scanner leggi = new Scanner(System.in);
    private Scanner scelte = new Scanner(System.in);
    private Giocatore giocatore;
    private Mappa mappa;
    private Interprete interprete;
    private String scelta;
    private AudioThread musica;
    private static final String ANSI_RED = "\033[0;31m";
    private static final String ANSI_BLUE = "\033[0;34m";
    private static final String ANSI_CYAN = "\033[0;36m";
    private static final String ANSI_YELLOW = "\033[0;33m";
    private static final String ANSI_GREEN = "\033[0;32m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Partita() {

	this.giocatore = new Giocatore();
	this.interprete = new Interprete();
	this.mappa = new Mappa();
	giocatore.setLuogoAttuale(mappa.getSeminterratoCasa()); // posiziona il giocatore nella posizione di partenza
    }

    public Partita(Mappa mappa, Giocatore giocatore) {

	this.giocatore = giocatore;
	this.interprete = new Interprete();
	this.mappa = mappa;
    }

    public void play() {

	Azione azione;
	String comando = null;
	boolean esito = false;
	String[] input;
	String oggetto, luogo, nome;
	long tempoFinale;
	long tempoIniziale = System.currentTimeMillis();

	setupMusica();
	System.out.println(ANSI_CYAN + giocatore.getLuogoAttuale().getDescrizione() + ANSI_RESET);

	while (true) {

	    System.out.print(">Che cosa vuoi fare?\n>");
	    comando = leggi.nextLine();
	    comando = comando.toLowerCase();
	    // il comando viene interpretato (se si tratta di uno spostamento ecc..)
	    azione = interprete.interpretaStringa(comando.trim());

	    if (azione != null) {

		switch (azione.getTipo()) {

		case MOVIMENTO:
		    input = comando.split(" "); // suddivide il comando
		    luogo = estraiComplemento(input); // vengono rimosse le preposizioni e gli articoli dal comando

		    if (luogo.trim().equals("interno bunker")) { // se voglio spostarmi all'interno del bunker

			if (mappa.getKm167().getNemico() == null) { // si verifica di aver ucciso la donna al km167
			    esito = false;
			} else {
			    esito = giocatore.move(luogo.trim()); // sposta il giocatore
			}
		    } else {
			esito = giocatore.move(luogo.trim()); // sposta il giocatore
		    } 
		    
		    if (luogo.trim().equals("interno stazione") && esito == false) {
			System.out.println(ANSI_RED + giocatore.getLuogoAttuale().getMessaggio() + ANSI_RESET);
		    }

		    if (esito == false && luogo.trim().equals("interno bunker") && giocatore.getLuogoAttuale().getNomeLuogo().equals("esterno bunker")) { 
			// se la donna è stata uccisa, si perde

			System.out.println(ANSI_BLUE + giocatore.getLuogoAttuale().getMessaggio() + ANSI_RESET);
			System.out.println(ANSI_RED + "\n**************************************************************\n"
			+ "                           GAME OVER!                         " 
			+ "\n**************************************************************\n" + ANSI_RESET);
			Salvataggio.reset();
			System.exit(0);

		    } else if (esito == true && luogo.trim().equals("interno bunker") && giocatore.getLuogoAttuale().getNomeLuogo().equals("interno bunker")) { 
			// altrimenti si vince

			System.out.println(ANSI_GREEN + "\n**************************************************************\n"
				+ "                            YOU WIN!                          " 
				+ "\n**************************************************************\n" + ANSI_RESET);
			tempoFinale = System.currentTimeMillis();
			System.out.print(ANSI_YELLOW + "\n>Inserisci nome giocatore:\n>"+ANSI_RESET);
			nome = leggi.next();
			Classifica.scriviClassifica(nome, convertiInMinuti(tempoFinale - tempoIniziale), convertiInSecondi(tempoFinale - tempoIniziale));
			Classifica.stampaClassifica();
			Salvataggio.reset();
			System.exit(0);
		    }
		    if (esito) {
			Salvataggio.salva(mappa, giocatore);
		    }
		    break;
		    
		case PASSA: // caso di comando non valido
		    System.out.println(ANSI_RED + "\n>Comando non valido." + ANSI_RESET);
		    break;
		    
		case ATTACCA:
		    if (giocatore.getLuogoAttuale().getNemico() == null) {
			System.out.println(ANSI_RED + "\n>Non c'e' nessun nemico che puoi attaccare." + ANSI_RESET);
		    } else {
			esito = giocatore.attacca(giocatore.getLuogoAttuale().getNemico());
			if (esito) {
			    System.out.println(ANSI_GREEN + "\n>Complimenti hai sconfitto il nemico!" + ANSI_RESET);
			    Salvataggio.salva(mappa, giocatore);
			} else if (giocatore.getPuntiVita() <= 0) {
			    System.out.println(ANSI_RED + "\n**************************************************************\n" 
				    + "             IL NEMICO TI HA DISTRUTTO. GAME OVER!            "
				    + "\n**************************************************************\n" + ANSI_RESET);
			    Salvataggio.reset();
			    System.exit(0);
			}
		    }
		    break;
		    
		case ESAMINA:
		    input = comando.split(" ");
		    oggetto = estraiComplemento(input);
		    esito = giocatore.esamina(oggetto.trim());
		    if (esito == false) {
			System.out.println(ANSI_RED + "\n>Comando non valido. L'oggetto che vuoi esaminare non esiste!" + ANSI_RESET);
		    }
		    break;
		    
		case AIUTO:
		    System.out.println(ANSI_YELLOW + "\n'vai' + <nome_luogo> per spostarti"
			    + "\n'esamina luogo' per esaminare il luogo"
			    + "\n'esamina' + <nome_oggetto> per esaminare un oggetto"
			    + "\n'inventario' per visualizzare l'inventario"
			    + "\n'prendi' + <nome_oggetto> per raccogliere un oggetto e aggiungerlo al tuo inventario"
			    + "\n'lascia' + <nome_oggetto> per lasciare un oggetto dell'inventario"
			    + "\n'equipaggia' + <nome_arma> per equipaggiare un'arma dall'inventario"
			    + "\n'usa' + <nome_oggetto> per usare un'oggetto"
			    + "\n'attacca' per attaccare"
			    + "\n'vita' per visualizzare i punti vita"
			    + "\n'suicidio' per suicidarti e terminare il gioco"
			    + "\n'audio' per stoppare / riprendere la musica di gioco"
			    + "\n'classifica' per mostrare la classifica basata sul tempo di risoluzione del gioco"
			    + "\n'riavvia' per ricominciare una nuova partita"
			    + "\n'esci' per uscire dal gioco con o senza salvataggio\n" + ANSI_RESET);
		    break;
		    
		case INVENTARIO:
		    System.out.println(ANSI_BLUE + "\n**************************************************************");
		    if (!giocatore.getInventario().isEmpty()) {
			for (Oggetto i : giocatore.getInventario()) {
			    System.out.println("- " + i.getNome());
			}
		    } else {
			System.out.println(" Il tuo inventario e' vuoto.");
		    }
		    System.out.println("**************************************************************\n" + ANSI_RESET);
		    break;
		    
		case LASCIA:
		    input = comando.split(" ");
		    oggetto = estraiComplemento(input);
		    esito = giocatore.lascia(oggetto.trim());

		    if (!esito) {
			System.out.println(ANSI_RED + "\n>Qualcosa e' andato storto e non e' stato possibile rilasciare." + ANSI_RESET);
		    } else {
			System.out.println(ANSI_GREEN + "\n>Hai lasciato: " + oggetto + "." + ANSI_RESET);
			Salvataggio.salva(mappa, giocatore);
		    }
		    break;
		    
		case VITA:
		    System.out.println(ANSI_GREEN + "\n>Attualmente hai " + giocatore.getPuntiVita() + " punti vita." + ANSI_RESET);
		    break;
		    
		case SUICIDIO:
		    
		    System.out.print(ANSI_YELLOW + "\n>Sei sicuro di volerti suicidare e di conseguenza perdere? (S/N)\n>" + ANSI_RESET);
		    scelta = scelte.next();
		    scelta = scelta.toLowerCase();
		    
		    if (scelta.equals("s")) {
			System.out.println(ANSI_GREEN + "\n**************************************************************\n"
				+"    Il troppo stress ti ha portato al suicidio. GAME OVER!    "
				+ "\n**************************************************************\n" + ANSI_RESET);
			Salvataggio.reset();
			System.exit(0);
		    } else if (!scelta.equals("n")) {
			System.out.println(ANSI_RED + "\n>Comando non valido. Inserisci S o N!\n" + ANSI_RESET);
		    } else {
			System.out.println("");
		    }
		 
		    break;
		    
		case RACCOGLI:
		    input = comando.split(" ");
		    oggetto = estraiComplemento(input);
		    esito = giocatore.raccogli(oggetto.trim());
		    if (!esito) {
			System.out.println(ANSI_RED + "\n>Comando non valido. Non puoi raccogliere qualcosa che non esiste!" + ANSI_RESET);
		    } else {
			System.out.println(ANSI_GREEN + "\n>Hai raccolto:" + oggetto + "." + ANSI_RESET);
			Salvataggio.salva(mappa, giocatore);
		    }
		    break;
		    
		case USA:
		    input = comando.split(" ");
		    oggetto = estraiComplemento(input);
		    esito = giocatore.usa(oggetto.trim());
		    if (!esito) {
			System.out.println(ANSI_RED + "\n>L'oggetto non esiste, percio' non puoi usarlo!" + ANSI_RESET);
		    }
		    break;
		    
		case RIAVVIA:
		    System.out.print(ANSI_YELLOW + "\n>Sei sicuro di voler riavviare? (S/N)\n>" + ANSI_RESET);
		    scelta = scelte.next();
		    scelta = scelta.toLowerCase();
		    if (scelta.equals("s")) {
			Salvataggio.reset();
			System.out.println(ANSI_GREEN + "\n**************************************************************\n"
				+ "                     RIAVVIO DEL GIOCO...                     "
				+ "\n**************************************************************\n" + ANSI_RESET);
			musica.kill();
			return;
		    } else if (!scelta.equals("n")) {
			System.out.println(ANSI_RED + "\n>Comando non valido. Inserisci S o N!\n" + ANSI_RESET);
		    } else {
			System.out.println("");
		    }
		    break;
		    
		case ESCI:
		    System.out.print(ANSI_YELLOW + "\n>Sei sicuro di voler uscire? (S/N)\n>" + ANSI_RESET);
		    scelta = scelte.next();
		    scelta = scelta.toLowerCase();

		    if (scelta.equals("s")) {
			System.out.print(ANSI_YELLOW + "\n>Vuoi salvare i dati? (S/N)\n>" + ANSI_RESET);
			scelta = scelte.next();
			scelta = scelta.toLowerCase();
			if (scelta.equals("s")) {
			    Salvataggio.salva(mappa, giocatore);
			    System.out.println(ANSI_GREEN + "\n**************************************************************\n"
				    + "   Dati memorizzati correttamente. Uscita dal gioco...    "
				    + "\n**************************************************************\n" + ANSI_RESET);
			    System.exit(0);
			} else if (scelta.equals("n")) {
			    Salvataggio.reset();
			    System.out.println(ANSI_GREEN + "\n**************************************************************\n"
				      + "                   Uscita dal gioco...                   "
				    + "\n**************************************************************\n" + ANSI_RESET);
			    System.exit(0);
			} else {
			    System.out.println(ANSI_RED + "\n>Comando non valido. Inserisci S o N!\n" + ANSI_RESET);
			}
		    } else if (scelta.equals("n")) {
			System.out.println("");
		    } else {
			System.out.println(ANSI_RED + "\n>Comando non valido. Inserisci S o N!\n" + ANSI_RESET);
		    }
		    break;
		    
		case AUDIO:
		    if (musica.isRunning()) {

			System.out.print(ANSI_YELLOW + "\n>Vuoi stoppare l'audio? (S/N)\n>" + ANSI_RESET);
			scelta = scelte.next();
			scelta = scelta.toLowerCase();

			if (scelta.equals("s")) {
			    musica.kill();
			    System.out.println(ANSI_GREEN + "\n>Audio stoppato correttamente!" + ANSI_RESET);

			} else if (scelta.equals("n")) {
			    System.out.println("");
			} else {
			    System.out.println(ANSI_RED + "\n>Comando non valido. Inserisci S o N!\n" + ANSI_RESET);
			}
		    } else {
			System.out.print(ANSI_YELLOW + "\n>Vuoi riattivare l'audio? (S/N)\n>" + ANSI_RESET);
			scelta = scelte.next();
			scelta = scelta.toLowerCase();

			if (scelta.equals("s")) {
			    setupMusica();
			    System.out.println(ANSI_GREEN + "\n>Audio riattivato correttamente!" + ANSI_RESET);

			} else if (scelta.equals("n")) {
			    System.out.println("");
			} else {
			    System.out.println(ANSI_RED + "\n>Comando non valido. Inserisci S o N!\n" + ANSI_RESET);
			}
		    }
		    break;
		    
		case CLASSIFICA:
		    Classifica.stampaClassifica();
		    break;

		default:
		    break;
		}
	    } else {
		System.out.println(ANSI_RED + "\n>Comando non valido." + ANSI_RESET);
	    }

	}

    }

    private void setupMusica() {

	musica = new AudioThread();
	musica.start();

    }

    private String estraiComplemento(String[] input) {

	String complemento = "";

	for (int j = 1; j < input.length; j++) {

	    if (!ePreposizione(input[j]) && !eArticolo(input[j])) {
		complemento = complemento + " " + input[j];
	    }
	}

	return complemento;
    }

    private boolean eArticolo(String parola) {

	String[] articoli = { "il", "la", "lo", "l'", "l' ", "le", "i", "gli", "un", "uno", "una", "un'", "un' " };
	boolean isArticolo = false;

	for (String p : articoli) {
	    if (p.compareTo(parola) == 0)
		isArticolo = true;
	}

	return isArticolo;
    }

    private boolean ePreposizione(String parola) {

	String[] preposizioni = { "di", "a", "da", "in", "con", "su", "per", "tra", "fra", "del", "al", "alla", "dal", "nella", "all'", "all' ", "nell'", "nell' ", "nel",
				"sul", "sull'", "sull' ", "della" };
	boolean isPreposizione = false;

	for (String p : preposizioni) {
	    if (p.compareTo(parola) == 0)
		isPreposizione = true;
	}

	return isPreposizione;
    }

    private int convertiInSecondi(long tempo) {
	int secondi = (int) (tempo / 1000) % 60;
	return secondi;
    }

    private int convertiInMinuti(long tempo) {
	int minuti = (int) ((tempo / (1000 * 60)) % 60);
	return minuti;
    }

}