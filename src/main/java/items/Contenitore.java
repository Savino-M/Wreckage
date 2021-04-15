package items;

import java.util.ArrayList;
import java.util.List;

public class Contenitore extends Oggetto {

    private static final long serialVersionUID = 1L;
    private List<Oggetto> oggetti; // lista degli oggetti contenuti

    public Contenitore(String nome, String descrizione, boolean visibile) {
	super(nome, descrizione, visibile);
	oggetti = new ArrayList<Oggetto>();
	this.setPrendibile(false);
	this.setUsabile(false);
    }

    public List<Oggetto> getOggetti() {
	return oggetti;
    }
}
