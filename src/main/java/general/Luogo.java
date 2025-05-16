package general;

import items.Oggetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Luogo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String descrizione; // descrizione del luogo
    private List<Luogo> luoghiAdiacenti; // lista dei luoghi accessibili dal luogo corrente
    private String nomeLuogo; // nome del luogo
    private List<Oggetto> oggetti; // lista degli oggetti presenti nel luogo
    private boolean bloccato; // indica se il luogo è bloccato o no
    private String messaggio; // messaggio di errore
    private Nemico nemico; // nemico
    private Oggetto oggettoSbloccante; // indica l'ogetto che sblocca il luogo
    private String testoEsamina;
    private String descrizioneSec;
    private boolean visitato;

    public Luogo() {
        bloccato = false;
        luoghiAdiacenti = new ArrayList<Luogo>();
        oggetti = new ArrayList<Oggetto>();
        nemico = null;
        oggettoSbloccante = null;
        messaggio = "Non ti e' concesso andare li'";
        testoEsamina = null;
        visitato = false;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Luogo> getLuoghiAdiacenti() {
        return luoghiAdiacenti;
    }

    public String getNomeLuogo() {
        return nomeLuogo;
    }

    public void setNomeLuogo(String nomeLuogo) {
        this.nomeLuogo = nomeLuogo;
    }

    public List<Oggetto> getOggetti() {
        return oggetti;
    }

    public void setOggetti(ArrayList<Oggetto> oggetti) {
        this.oggetti = oggetti;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public boolean isBloccato() {
        return bloccato;
    }

    public void setBloccato(boolean bloccato) {
        this.bloccato = bloccato;
    }

    public Nemico getNemico() {
        return nemico;
    }

    public void setNemico(Nemico nemico) {
        this.nemico = nemico;
    }

    public Oggetto getOggettoSbloccante() {
        return oggettoSbloccante;
    }

    public void setOggettoSbloccante(Oggetto oggettoSbloccante) {
        this.oggettoSbloccante = oggettoSbloccante;
    }

    public String getTestoEsamina() {
        return testoEsamina;
    }

    public void setTestoEsamina(String testoEsamina) {
        this.testoEsamina = testoEsamina;
    }

    public boolean isVisitato() {
        return visitato;
    }

    public void setVisitato(boolean visitato) {
        this.visitato = visitato;
    }

    public Luogo adiacenteA(String luogoDest) {

        Luogo adiacente = null;

        for (Luogo l : luoghiAdiacenti) {
            if (l.getNomeLuogo().equals(luogoDest)) {
                adiacente = l;
            }
        }

        return adiacente;
    }

    public String getDescrizioneSec() {
        return descrizioneSec;
    }

    public void setDescrizioneSec(String descrizioneSec) {
        this.descrizioneSec = descrizioneSec;
    }
}
