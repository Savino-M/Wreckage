package items;

public class Arma extends Oggetto
{

    private static final long serialVersionUID = 1L;
    private int dannoMax;

    public Arma(String nome, String descrizione, boolean visibile, int dannoMax)
    {
        super(nome, descrizione, visibile);
        this.dannoMax = dannoMax;
    }

    public int getDannoMax()
    {
        return dannoMax;
    }

    public void setDannoMax(int dannoMax)
    {
        this.dannoMax = dannoMax;
    }
}
