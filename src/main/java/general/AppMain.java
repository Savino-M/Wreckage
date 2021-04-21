package general;

import java.util.Scanner;

public class AppMain
{

    private static final String ANSI_RED = "\033[0;31m";
    private static final String ANSI_BLUE = "\033[0;34m";
    private static final String ANSI_CYAN = "\033[0;36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\033[0;32m";

    public static void main(String args[])
    {

        String scelta = null;
        boolean valido = false;
        boolean intro = false;
        Partita p = null;
        String[] introduzione = new String[3];

        introduzione[0] = "\n>2320 news dal mondo:\n" + ">Nulla e' piu' come prima della guerra nucleare...\n"
                + ">Stati Uniti e Cina con i loro bombardamenti hanno causato disastri ambientali.";
        introduzione[1] = "\n>Immediata e' stata l'alterazione genetica di razze animali e di quella umana."
                + "\n>I nuovi mostri sono aggressivi ed affamati."
                + "\n>Solo il 20% della popolazione e' salva, rinchiusa in bunker e costantemente in fuga dai nemici impazziti.";
        introduzione[2] = "\n>Riuscirai a raggiungere sano e salvo il bunker piu' vicino a te?"
                + "\n>Tieniti pronto, questo e' solo l'inizio!";

        try (Scanner leggi = new Scanner(System.in))
        {

            while (true)
            {

                scelta = null;
                valido = false;
                intro = false;
                p = null;

                System.out.println("\n" + ANSI_RED + ">Benvenuto in WRECKAGE, il gioco ha inizio!" + ANSI_RESET);

                if (Salvataggio.esistePartita())
                { // se esiste un salvataggio precedente

                    while (valido == false)
                    {
                        System.out.print("\n" + ANSI_BLUE
                                + ">E' gia' presente una partita. Vuoi continuare da dove eri rimasto? (S/N)"
                                + ANSI_RESET + "\n>");
                        scelta = leggi.nextLine();
                        scelta = scelta.toLowerCase();

                        switch (scelta)
                        {

                            case "s":
                                valido = true;

                                // viene caricata la partita salvata
                                p = new Partita(Salvataggio.caricaMappa(), Salvataggio.caricaGiocatore());
                                break;

                            case "n":
                                valido = true;
                                intro = true; // deve essere stampata l'introduzione
                                Salvataggio.reset();
                                p = new Partita(); // viene creata una nuova partita
                                break;

                            default:
                                System.out.println(ANSI_RED + "\n>Comando non valido. Inserisci S o N!" + ANSI_RESET);
                                break;
                        }
                    }

                } else
                {
                    intro = true; // deve essere stampata l'introduzione
                    p = new Partita();
                }

                if (intro == true)
                {

                    for (int i = 0; i < 3; i++)
                    { // stampa dell'introduzione

                        System.out.println(ANSI_CYAN + introduzione[i] + ANSI_RESET);
                        System.out.print("\n" + ANSI_GREEN + ">Premi invio per continuare..." + ANSI_RESET + "\n>");
                        scelta = leggi.nextLine();
                    }
                }

                p.play(); // inizia la partita
            }
        }
    }

}
