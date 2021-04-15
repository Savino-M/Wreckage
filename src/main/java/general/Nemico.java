package general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import items.Oggetto;

public class Nemico implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private int vita;
    private int dannoMax;
    private List<Oggetto> dropList;

    public Nemico(String nome, int vita, int dannoMax) {
	this.nome = nome;
	this.vita = vita;
	this.dannoMax = dannoMax;
	dropList = new ArrayList<Oggetto>();
    }

    public int getVita() {
	return vita;
    }

    public void setVita(int vita) {
	this.vita = vita;
    }

    public String getNome() {
	return nome;
    }

    public int getDannoMax() {
	return dannoMax;
    }

    public List<Oggetto> getDropList() {
	return dropList;
    }

}
