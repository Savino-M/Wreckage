package general;

enum Tipo { // Tipi di azioni
    MOVIMENTO, AIUTO, PASSA, USA, ESAMINA, SUICIDIO, RACCOGLI, LASCIA, INVENTARIO, ATTACCA, VITA, RIAVVIA, ESCI, AUDIO, CLASSIFICA
}

public enum Azione {

    // Azioni possibili
    ActionMuovi(new String[] { "sposta", "vai", "dirigiti", "muoviti", "entra", "spostati", "muvoi" }, Tipo.MOVIMENTO),
    ActionAiuto(new String[] { "aiuto", "help", "comandi" }, Tipo.AIUTO), 
    ActionPassa(new String[] { " " }, Tipo.PASSA),
    ActionUsa(new String[] { "usa", "equipaggia", "utilizza" }, Tipo.USA), 
    ActionEsamina(new String[] { "esamina", "guarda", "vedi", "osserva" }, Tipo.ESAMINA),
    ActionMuori(new String[] { "muori", "suicidati", "suicidio" }, Tipo.SUICIDIO), 
    ActionPrendi(new String[] { "raccogli", "prendi" }, Tipo.RACCOGLI),
    ActionLascia(new String[] { "lascia", "butta", "getta" }, Tipo.LASCIA), 
    ActionInventario(new String[] { "inventario" }, Tipo.INVENTARIO),
    ActionAttacca(new String[] { "attacca", "colpisci", "spara" }, Tipo.ATTACCA), 
    ActionMostraVita(new String[] { "vita" }, Tipo.VITA),
    ActionRestart(new String[] { "restart", "riavvia", "ricomincia" }, Tipo.RIAVVIA), 
    ActionEsci(new String[] { "esci", "quit", "esc", "exit", "uscita" }, Tipo.ESCI),
    ActionGesticiAudio(new String[] { "audio", "musica", "suono", "suoni", "musiche", "traccia" }, Tipo.AUDIO),
    ActionMostraClassifica(new String[] { "classifica", "classifiche", "tempo", "tempi" },Tipo.CLASSIFICA);

    private String[] sinonimi; // parole accettate per riconoscere un'azione
    private Tipo tipo; // tipo di azione corrispondente

    Azione(String[] sinonimi, Tipo tipo) {
	this.sinonimi = sinonimi;
	this.tipo = tipo;
    }

    public String[] getSinonimi() {
	return sinonimi;
    }

    public Tipo getTipo() {
	return tipo;
    }
};
