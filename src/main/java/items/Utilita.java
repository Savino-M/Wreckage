package items;

import general.Luogo;

public class Utilita extends Oggetto {

    private static final long serialVersionUID = 1L;
    private String effetto;
    private Luogo luogoSbloccabile;

    public Utilita(String nome, String descrizione, boolean visibile) {
	super(nome, descrizione, visibile);
	this.effetto = null;
	this.luogoSbloccabile = null;
    }

    public String getEffetto() {
	return effetto;
    }

    public void setEffetto(String effetto) {
	this.effetto = effetto;
    }

    public Luogo getLuogoSbloccabile() {
	return luogoSbloccabile;
    }

    public void setLuogoSbloccabile(Luogo luogoSbloccabile) {
	this.luogoSbloccabile = luogoSbloccabile;
    }

}
