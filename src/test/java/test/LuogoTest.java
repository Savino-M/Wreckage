package test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Luogo;
import general.Mappa;

public class LuogoTest {

    @Test
    public void testAdiacente() {

	Mappa mappa = new Mappa();
	Luogo esternoCasa = new Luogo();

	esternoCasa.setNomeLuogo("esterno casa");

	assertTrue(mappa.getSeminterratoCasa().adiacenteA(esternoCasa.getNomeLuogo()).getNomeLuogo().equals(esternoCasa.getNomeLuogo()));
    }

}
