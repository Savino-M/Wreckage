package general;

import items.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Giocatore implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ANSI_RED = "\033[0;31m";
    private static final String ANSI_BLUE = "\033[0;34m";
    private static final String ANSI_CYAN = "\033[0;36m";
    private static final String ANSI_YELLOW = "\033[0;33m";
    private static final String ANSI_GREEN = "\033[0;32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private Luogo luogoAttuale;
    private List<Oggetto> inventario;
    private int puntiVita = 100;
    private Arma armaInUso;

    public Giocatore() {
        this.inventario = new ArrayList<Oggetto>();
    }

    public boolean esamina(String oggetto) {

        boolean esito = false;

        if (oggetto.equals("luogo")) { // se viene richiesto di esaminare il luogo
            System.out.println(ANSI_YELLOW + this.luogoAttuale.getTestoEsamina() + ANSI_RESET);
            // stampa la descrizione del luogo

            esito = true;
        } else {

            for (Oggetto item : this.getLuogoAttuale().getOggetti()) { // si effettua una ricerca fra gli oggetti
                // presenti nel luogo

                if (item.getNome().equals(oggetto) && item.isVisibile()) {
                    System.out.println(ANSI_YELLOW + item.getDescrizione() + ANSI_RESET);
                    // stampa la descrizione dell'oggetto

                    if (item instanceof Contenitore) { // se l'oggetto esaminato è un contenitore
                        Contenitore a = (Contenitore) item; // viene convertito in un contenitore(cast)
                        for (Oggetto u : a.getOggetti()) { // e tutti gli oggetti che contiene diventano visibili
                            u.setPrendibile(true);
                            u.setVisibile(true);
                        }
                    }
                    esito = true;
                    break;
                }
            }
            if (esito == false) { // se la ricerca nel luogo non ha dato esito positivo
                for (Oggetto item : this.getInventario()) { // si cerca l'oggetto nel proprio inventario
                    if (item.getNome().equals(oggetto)) {
                        System.out.println(ANSI_YELLOW + item.getDescrizione() + ANSI_RESET);
                        // e si stampa la descrizione
                        esito = true;
                        break;
                    }
                }
            }

        }

        return esito;
    }

    public boolean raccogli(String oggetto) {

        boolean esito = false;

        for (Oggetto item : this.getLuogoAttuale().getOggetti()) { // se l'oggetto è presente nel luogo
            if (item.getNome().equals(oggetto) && item.isVisibile() && item.isPrendibile()) { // se è prendibile
                inventario.add(item); // si aggiunge l'oggetto all'inventario
                this.getLuogoAttuale().getOggetti().remove(item); // e lo si rimuove dal luogo
                esito = true;
                break;
            }
        }
        return esito;
    }

    public boolean lascia(String oggetto) {

        boolean esito = false;

        for (Oggetto item : this.inventario) { // si ricerca l'oggetto nell'inventario
            if (item.getNome().equals(oggetto)) { // se lo si trova
                inventario.remove(item); // lo si rimuove dall'inventario
                this.getLuogoAttuale().getOggetti().add(item); // e lo si aggiunge al luogo
                esito = true;
                break;
            }
        }
        return esito;
    }

    public boolean move(String luogoDest) {

        boolean esito = false;
        Luogo l;

        try {

            luogoAttuale.setVisitato(true);
            l = luogoAttuale.adiacenteA(luogoDest); // viene restituito il luogo adiacente dove ci si vuole spostare

            // se il luogo è sbloccato, oppure si ha la chiave per accedervi
            if (l != null && ((!l.isBloccato()) || (haOggetto(l.getOggettoSbloccante().getNome())))) {

                if (l.isBloccato()) { // se il luogo è bloccato

                    for (Oggetto item : this.inventario) { // si ricerca nell'inventario la chiave
                        if (item.getNome().equals(l.getOggettoSbloccante().getNome())) {
                            inventario.remove(item); // la si rimuove
                            l.setBloccato(false); // si sblocca il luogo permanentemente
                            break;
                        }
                    }
                }
                this.luogoAttuale = l; // si cambia il luogo

                if (!luogoAttuale.isVisitato()) {
                    System.out.println(ANSI_CYAN + luogoAttuale.getDescrizione() + ANSI_RESET);
                } else {
                    System.out.println(ANSI_CYAN + luogoAttuale.getDescrizioneSec() + ANSI_RESET);
                }
                esito = true;

            } else if (l == null) {
                System.out
                        .println(ANSI_RED + "\n>Comando non valido, hai inserito un luogo inaccessibile!" + ANSI_RESET);
            } else if (l.isBloccato()) { // se il luogo è bloccato
                System.out.println(ANSI_RED + luogoAttuale.getMessaggio() + ANSI_RESET);
            }

        } catch (Exception e) {

        }

        return esito;
    }

    public boolean usa(String oggetto) {

        boolean esito = false;

        for (Oggetto item : this.getInventario()) { // si cerca l'oggetto da usare nell'inventario
            if (item.getNome().equals(oggetto)) {
                if (item instanceof Arma) { // se è un'arma la si equipaggia
                    this.equipaggiaArmaInUso((Arma) item);
                    esito = true;
                    System.out.println(ANSI_GREEN + "\n>Hai equipaggiato l'arma: " + item.getNome() + "." + ANSI_RESET);
                    break;

                } else if (item instanceof Cura) { // se è una cura la si usa
                    Cura cura = (Cura) item;
                    System.out.println(ANSI_GREEN + "\n>Ti sei rigenerato di "
                            + (cura.getCurabilita() - this.getPuntiVita()) + " punti vita." + ANSI_RESET);
                    this.cura(cura.getCurabilita() - this.getPuntiVita());
                    this.getInventario().remove(item);
                    esito = true;
                    break;
                }
            }
        }

        if (esito == false) {
            for (Oggetto item : this.getLuogoAttuale().getOggetti()) { // se l'oggetto si trova nel luogo
                if (item.getNome().equals(oggetto)) {

                    if (item instanceof Utilita && item.isUsabile()) { // ed è usabile
                        System.out.println(ANSI_GREEN + ((Utilita) item).getEffetto() + ANSI_RESET);
                        // lo si usa
                        ((Utilita) item).getLuogoSbloccabile().setBloccato(false); // per sbloccare il luogo ad esso
                        // correlato
                        esito = true;
                        break;
                    }
                }
            }
        }

        return esito;
    }

    public boolean attacca(Nemico nemico) {

        boolean esito = false;
        int dannoEffettivo = 0;

        if (this.getArmaInUso() != null) { // se ho un'arma equipaggiata attacco il nemico
            dannoEffettivo = (int) (Math.random() * this.getArmaInUso().getDannoMax() + 1)
                    + (this.getArmaInUso().getDannoMax() / 2); // viene generato il danno
            nemico.setVita(nemico.getVita() - dannoEffettivo);
            System.out.println(ANSI_GREEN + "\n>Hai colpito il nemico togliendogli " + dannoEffettivo + " punti vita!"
                    + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED
                    + "\n>Non hai nessuna arma equipaggiata. Se non ne possiedi, corri indietro per trovarne una!"
                    + ANSI_RESET);
        }

        if (nemico.getVita() > 0) {
            dannoEffettivo = (int) (Math.random() * nemico.getDannoMax() + 1) + (nemico.getDannoMax() / 2);
            this.setPuntiVita(this.getPuntiVita() - dannoEffettivo);
            System.out.println(ANSI_RED + ">" + nemico.getNome() + " ti ha colpito togliendoti " + dannoEffettivo
                    + " punti vita." + ANSI_RESET);
            System.out
                    .println(ANSI_YELLOW + ">Il nemico ha ancora " + nemico.getVita() + " punti vita.\n" + ANSI_RESET);

        } else {
            esito = true;
            for (Oggetto o : nemico.getDropList()) {
                System.out.println(ANSI_BLUE + ">Hai raccolto: " + o.getNome() + ANSI_RESET);
                this.inventario.add(o);
            }
            this.getLuogoAttuale().setNemico(null);
        }

        return esito;
    }

    public boolean haOggetto(String oggetto) {

        boolean esito = false;

        for (Oggetto o : this.getInventario()) {
            if (o.getNome().equals(oggetto)) {
                esito = true;
                break;
            }
        }

        return esito;
    }

    public Luogo getLuogoAttuale() {
        return luogoAttuale;
    }

    public void setLuogoAttuale(Luogo luogoAttuale) {
        this.luogoAttuale = luogoAttuale;
    }

    public List<Oggetto> getInventario() {
        return inventario;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }

    public Arma getArmaInUso() {
        return armaInUso;
    }

    public void equipaggiaArmaInUso(Arma armaInUso) {
        this.armaInUso = armaInUso;
    }

    public void cura(int valore) {
        this.setPuntiVita(this.puntiVita + valore);
    }

}
