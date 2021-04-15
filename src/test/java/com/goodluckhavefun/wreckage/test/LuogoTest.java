package com.goodluckhavefun.wreckage.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.goodluckhavefun.wreckage.general.Luogo;
import com.goodluckhavefun.wreckage.general.Mappa;

public class LuogoTest {

    @Test
    public void testAdiacente() {

	Mappa mappa = new Mappa();
	Luogo esternoCasa = new Luogo();

	esternoCasa.setNomeLuogo("esterno casa");

	assertTrue(mappa.getSeminterratoCasa().adiacenteA(esternoCasa.getNomeLuogo()).getNomeLuogo().equals(esternoCasa.getNomeLuogo()));
    }

}
