package items;

public class Cura extends Oggetto
{

    private static final long serialVersionUID = 1L;
    private int curabilita;

    public Cura(String nome, String descrizione, boolean visibile, int curabilita)
    {
        super(nome, descrizione, visibile);
        this.curabilita = curabilita;
    }

    public int getCurabilita()
    {
        return curabilita;
    }

    public void setCurabilita(int curabilita)
    {
        this.curabilita = curabilita;
    }
}
