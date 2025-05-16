import general.Azione;
import general.Interprete;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InterpreteTest {

    @Test
    public void testInterpreta() {

        String comando = "vai esterno casa";
        Interprete interprete = new Interprete();
        Azione azione = Azione.ActionMuovi;

        comando = "prendi coltello";
        azione = Azione.ActionPrendi;

        comando = "inventario";
        azione = Azione.ActionInventario;

    }

}
