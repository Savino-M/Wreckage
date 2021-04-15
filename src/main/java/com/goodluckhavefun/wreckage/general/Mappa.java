package com.goodluckhavefun.wreckage.general;

import java.io.Serializable;

import com.goodluckhavefun.wreckage.items.Arma;
import com.goodluckhavefun.wreckage.items.Contenitore;
import com.goodluckhavefun.wreckage.items.Cura;
import com.goodluckhavefun.wreckage.items.Utilita;

public class Mappa implements Serializable {

    private static final long serialVersionUID = 1L;
    private Luogo seminterratoCasa;
    private Luogo esternoCasa;
    private Luogo stazioneDiServizio;
    private Luogo stazioneLatoNord;
    private Luogo internoStazione;
    private Luogo esternoStazioneRadio;
    private Luogo internoStazioneRadio;
    private Luogo km167;
    private Luogo esternoBunker;
    private Luogo internoBunker;

    public Mappa() {

	inizializzaLuoghi();
	inizializzaOggetti();
    }

    private void inizializzaLuoghi() {

	// Inizializzo i luoghi

	seminterratoCasa = new Luogo();
	seminterratoCasa.setNomeLuogo("seminterrato casa"); // nome del luogo
	seminterratoCasa.setDescrizione("\n>Ti trovi nel tuo seminterrato di casa. (per i comandi disponibili digita help)");
	// descrizione del luogo
	seminterratoCasa.setTestoEsamina("\n>Alla tua destra c'e' un coltello... puo' tornarti utile no? Di fronte c'e' l'uscita per l' <<esterno di casa>>.");
	seminterratoCasa.setDescrizioneSec("\n>Sei ritornato nel simenterrato di casa.");
	seminterratoCasa.setMessaggio("\n>Per aprire la porta hai bisogno delle chiavi, genio!" + "\n>Magari sono nel comodino alla tua sinistra, lo esamini?"); 
	// messaggio di errore

	esternoCasa = new Luogo();
	esternoCasa.setNomeLuogo("esterno casa");
	esternoCasa.setDescrizione("\n>Sei giunto all'esterno di casa, tutto sembra tranquillo... La tua auto e' in fondo al vicolo.");
	esternoCasa.setTestoEsamina("\n>In lontananza c'e' una <<stazione di servizio>>, devi usare l'auto ma e' bloccata da un'aracnide mutante." + "\n>E' ora di combattere!");
	esternoCasa.setMessaggio("\n>Un ragno gigante ti sbarrando la strada, devi prima farlo fuori!");
	esternoCasa.setDescrizioneSec("\n>Eccoti ritornato nell'esterno di casa...");
	esternoCasa.setBloccato(true); // rendo inaccessibile il luogo a meno che non si verifichi un evento

	stazioneDiServizio = new Luogo();
	stazioneDiServizio.setNomeLuogo("stazione servizio");
	stazioneDiServizio.setDescrizione("\n>Sei appena giunto alla stazione di servizio.");
	stazioneDiServizio.setDescrizioneSec("\n>Sei tornato alla stazione di servizio."
		+ "\n>Puoi visitare l' <<interno della stazione>>, la <<stazione lato nord>>, o proseguire verso l' <<esterno della stazione radio>>.");
	stazioneDiServizio.setTestoEsamina("\n>Hai notato? C'e' una locandina di un bunker nelle vicinanze, esaminalo per saperne di piu'."
		+ "\n>Alla tua destra c'e' l' <<interno della stazione>> per le provviste, ma serve elettricita' per aprire le porte."
		+ "\n>Nessuna informazione nota sulla <<stazione lato nord>>, potresti dare un'occhiata!");
	stazioneDiServizio
		.setMessaggio("\n>Non puoi accedere all'interno della stazione senza corrente. " + "\n>Devi prima trovare un generatore! Potrebbe essere alla <<stazione lato nord>>?");
	stazioneDiServizio.setBloccato(true);

	stazioneLatoNord = new Luogo();
	stazioneLatoNord.setNomeLuogo("stazione lato nord");
	stazioneLatoNord.setDescrizione("\n>Sei sul lato nord della stazione di servizio.");
	stazioneLatoNord.setDescrizioneSec("\n>Hai gia' visitato il lato nord, hai per caso dimenticato qualcosa?");
	stazioneLatoNord.setTestoEsamina("\n>Ehi un momento, quello sembra essere un generatore, usalo per attivare le porte!");

	internoStazione = new Luogo();
	internoStazione.setNomeLuogo("interno stazione");
	internoStazione.setDescrizione("\n>Sei entrato nella stazione di servizio.");
	internoStazione.setDescrizioneSec("\n>Hai gia' visitato il negozio, hai per caso dimenticato qualcosa?");
	internoStazione.setTestoEsamina("\n>Che fortuna, proprio cio' di cui avevi bisogno, una pistola. La vuoi raccogliere?"
		+ "\n>Ora puoi uscire e proseguire il tuo viaggio verso l' <<esterno della stazione radio>>...");
	internoStazione.setBloccato(true);

	esternoStazioneRadio = new Luogo();
	esternoStazioneRadio.setNomeLuogo("esterno stazione radio");
	esternoStazioneRadio.setDescrizione("\n>Sei all'esterno della stazione radio.");
	esternoStazioneRadio.setTestoEsamina(
		"\n>L'accesso all' <<interno della stazione radio>> sembra essere bloccato." + "\n>Come se non bastasse c'e' anche una bestia impazzita da affrontare...\n>Inizia il combattimento!");
	esternoStazioneRadio.setDescrizioneSec("\n>Sei ritornato all'esterno, prosegui verso il <<km 167>>...");
	esternoStazioneRadio.setMessaggio("\n>Mmh, sembra che per entrare ci sia bisogno di un badge, magari lo possiede quella bestia!");

	internoStazioneRadio = new Luogo();
	internoStazioneRadio.setNomeLuogo("interno stazione radio");
	internoStazioneRadio.setDescrizione("\n>Sei dentro la stazione radio.");
	internoStazioneRadio.setDescrizioneSec("\n>Hai gia' visitato la stazione radio, hai per caso dimenticato qualcosa? ");
	internoStazioneRadio.setTestoEsamina("\n>Ecco, ascolta... proprio ora stanno trasmettendo la posizione del bunker. Manca poco!"
		+ "\n>Ti sei segnato le coordinate, torna all'esterno, e prosegui con il viaggio in auto al <<km 167>>!");
	internoStazioneRadio.setBloccato(true);

	km167 = new Luogo();
	km167.setNomeLuogo("km 167");
	km167.setDescrizione("\n>A soli 7 km dall'arrivo c'e' una donna per strada e sembra avere tante scorte di cibo e un pass per il bunker...");
	km167.setTestoEsamina("\n>Con se' possiede anche un badge di accesso al bunker. Ma ehi, non starai mica pensando di derubarla?"
		+ "\n>Prosegui con attacco oppure dirigiti direttamente all'<<esterno del bunker>>.");
	km167.setDescrizioneSec("\n>Eccoti ritornato al km 167.");

	esternoBunker = new Luogo();
	esternoBunker.setNomeLuogo("esterno bunker");
	esternoBunker.setDescrizione("\n>Sei giunto fuori dal bunker. All'ingresso ci sono delle guardie... "
		+ "\n>Sei quasi salvo! Non ti rimane altro che entrare nell' <<interno del bunker>>.");
	esternoBunker.setMessaggio("\n>Le guardie hanno notato il pass della donna... " + "\n>Hai ucciso una persona innocente a causa della tua avidita' e crudelta', non meriti la salvezza."
			+ "\n>Vieni rinchiuso in cella e muori di fame dopo 23 giorni.");
	esternoBunker.setTestoEsamina("\n>Le guardie ti stanno facendo domande. Sono sospettose!");
	esternoBunker.setDescrizioneSec("\n>Eccoti ritornato all'esterno del bunker.");

	internoBunker = new Luogo();
	internoBunker.setNomeLuogo("interno bunker");
	internoBunker.setDescrizione("\n>Complimenti sei riuscito ad entrare nel bunker integro. " + "\n>Adesso puoi benissimo dormire sonni tranquilli...");

	// Creo la mappa

	seminterratoCasa.getLuoghiAdiacenti().add(esternoCasa);

	esternoCasa.getLuoghiAdiacenti().add(stazioneDiServizio);
	esternoCasa.getLuoghiAdiacenti().add(seminterratoCasa);

	stazioneDiServizio.getLuoghiAdiacenti().add(stazioneLatoNord);
	stazioneDiServizio.getLuoghiAdiacenti().add(internoStazione);
	stazioneDiServizio.getLuoghiAdiacenti().add(esternoCasa);
	stazioneDiServizio.getLuoghiAdiacenti().add(esternoStazioneRadio);

	stazioneLatoNord.getLuoghiAdiacenti().add(stazioneDiServizio);
	stazioneLatoNord.getLuoghiAdiacenti().add(esternoStazioneRadio);

	internoStazione.getLuoghiAdiacenti().add(stazioneDiServizio);

	esternoStazioneRadio.getLuoghiAdiacenti().add(internoStazioneRadio);
	esternoStazioneRadio.getLuoghiAdiacenti().add(stazioneDiServizio);
	esternoStazioneRadio.getLuoghiAdiacenti().add(stazioneLatoNord);
	esternoStazioneRadio.getLuoghiAdiacenti().add(km167);

	internoStazioneRadio.getLuoghiAdiacenti().add(esternoStazioneRadio);

	km167.getLuoghiAdiacenti().add(esternoBunker);
	km167.getLuoghiAdiacenti().add(esternoStazioneRadio);

	esternoBunker.getLuoghiAdiacenti().add(internoBunker);
	esternoBunker.getLuoghiAdiacenti().add(km167);
    }

    private void inizializzaOggetti() {

	Arma coltello = new Arma("coltello", "\n>Ideale per fare a fettine i nemici.", true, 40);

	Utilita chiavi = new Utilita("chiavi", "\n>Aprono le porte per uscire di casa.", false);
	chiavi.setPrendibile(false); // rendo l'oggetto non raccoglibile
	chiavi.setUsabile(false); // rendo l'oggetto non usabile

	Utilita chiaviAuto = new Utilita("chiavi auto", "\n>Sono le chiavi dell'auto.", false);
	chiavi.setPrendibile(false);
	chiavi.setUsabile(false);

	Utilita badge = new Utilita("badge", "\n>Badge di un dipendente della stazione radio.", false);
	chiavi.setPrendibile(false);
	chiavi.setUsabile(false);

	Contenitore comodino = new Contenitore("comodino",
		"\n>Ottimo! Hai trovato le chiavi, raccoglile e dirigiti verso l' <<esterno di casa>> " + "per recuperare la tua auto.", true);
	comodino.getOggetti().add(chiavi);

	Utilita generatore = new Utilita("generatore", "\n>Pare che dia corrente alla stazione di servizio", true);
	generatore.setEffetto("\n>Hai attivato il generatore. Torna alla <<stazione di servizio>> le porte dell'interno sono aperte!");
	generatore.setPrendibile(false);
	generatore.setLuogoSbloccabile(internoStazione);

	Arma pistola = new Arma("pistola", "\n>Uccisione assicurata.", true, 55);

	Cura cura = new Cura("cura", "\n>Rigenera punti vita.", false, 100);

	Utilita locandina = new Utilita("locandina", "\n>Ti sei appena segnato la loro frequenza radio sempre in ascolto!", true);
	locandina.setPrendibile(false);
	locandina.setUsabile(false);

	Nemico aracnide = new Nemico("Aracnide mutante", 100, 20);
	aracnide.getDropList().add(cura);
	aracnide.getDropList().add(chiaviAuto);

	Nemico bestia = new Nemico("Bestia", 100, 40);
	bestia.getDropList().add(badge);
	bestia.getDropList().add(cura);

	Nemico donna = new Nemico("Donna", 100, 20);
	donna.getDropList().add(cura);

	// Aggiungo gli oggetti nella mappa

	seminterratoCasa.getOggetti().add(coltello);
	seminterratoCasa.getOggetti().add(chiavi);
	seminterratoCasa.getOggetti().add(comodino);
	
	esternoCasa.setOggettoSbloccante(chiavi);
	esternoCasa.setNemico(aracnide);
	
	stazioneDiServizio.setOggettoSbloccante(chiaviAuto);
	stazioneDiServizio.getOggetti().add(locandina);
	
	stazioneLatoNord.getOggetti().add(generatore);
	
	internoStazione.getOggetti().add(pistola);
	
	esternoStazioneRadio.setNemico(bestia);
	
	internoStazioneRadio.setOggettoSbloccante(badge);
	
	km167.setNemico(donna);
    }

    public Luogo getSeminterratoCasa() {
	return seminterratoCasa;
    }
    
    public Luogo getKm167() {
        return km167;
    }

}
