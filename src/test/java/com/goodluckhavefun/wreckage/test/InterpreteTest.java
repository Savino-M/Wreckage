package com.goodluckhavefun.wreckage.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.goodluckhavefun.wreckage.general.Azione;
import com.goodluckhavefun.wreckage.general.Interprete;

public class InterpreteTest {

    @Test
    public void testInterpreta() {

	String comando = "vai esterno casa";
	Interprete interprete = new Interprete();
	Azione azione = Azione.ActionMuovi;

	assertTrue(interprete.interpretaStringa(comando).getTipo() == azione.getTipo());
	
	comando = "prendi coltello";
	azione = Azione.ActionPrendi;
	
	assertTrue(interprete.interpretaStringa(comando).getTipo() == azione.getTipo());
	
	comando = "inventario";
	azione = Azione.ActionInventario;
	
	assertTrue(interprete.interpretaStringa(comando).getTipo() == azione.getTipo());
    }

}
