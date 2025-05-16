package items;

import java.io.Serializable;

public class Oggetto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String descrizione;
    private boolean visibile; // indica se l'oggetto è nascosto o meno
    private boolean prendibile; // indica se l'oggetto si può raccogliere
    private boolean usabile; // indica se l'oggetto si può usare

    public Oggetto(String nome, String descrizione, boolean visibile, boolean prendibile, boolean usabile) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.visibile = visibile;
        this.prendibile = prendibile;
        this.usabile = usabile;
    }

    public Oggetto(String nome, String descrizione, boolean visibile) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.visibile = visibile;
        this.prendibile = true;
        this.usabile = true;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public boolean isPrendibile() {
        return prendibile;
    }

    public void setPrendibile(boolean prendibile) {
        this.prendibile = prendibile;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isUsabile() {
        return usabile;
    }

    public void setUsabile(boolean usabile) {
        this.usabile = usabile;
    }

}
