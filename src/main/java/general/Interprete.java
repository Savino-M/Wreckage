package general;

public class Interprete
{

    public Azione interpretaStringa(String comando)
    {

        Azione azione = null;

        if (comando == null || comando.equals(" "))
        { // se il comando è vuoto

            azione = Azione.ActionPassa;

        }
        else
        {
            String[] input = comando.split(" ");
            azione = interpretaAzione(input); // identifica l'azione richiesta

        }
        return azione;
    }

    public Azione interpretaAzione(String input[])
    {

        Azione azione = null;

        String parola = input[0];

        out:
        {
            for (Azione a : Azione.values())
            { // per ogni azione
                for (String sinonimo : a.getSinonimi())
                { // vengono esaminati i sinonimi

                    if (parola.equals(sinonimo))
                    { // se viene riconosciuto il comando dell'utente
                        azione = a; // si memorizza l'azione
                        break out;
                    }
                }
            }
        }
        return azione;
    }

}
