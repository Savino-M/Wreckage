package general;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Classifica
{

    private static final String ANSI_CYAN = "\033[0;36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\033[0;33m";

    public static void scriviClassifica(String nome, int minuti, int secondi)
    {

        FileReader reader;
        Properties dBproperties = new Properties();

        try
        {
            reader = new FileReader("res/db/db.properties");
            dBproperties.load(reader);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }


        try
        {
            Connection conn = DriverManager.getConnection("jdbc:h2:./res/db/classifica", dBproperties.getProperty("user"), dBproperties.getProperty("password"));
            Statement stm = conn.createStatement();
            stm.executeUpdate("INSERT INTO classifica VALUES ('" + nome + "', INTERVAL '" + minuti + ":" + secondi + "' MINUTE TO SECOND)");

            stm.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void stampaClassifica()
    {

        FileReader reader;
        Properties dBproperties = new Properties();
        int i = 0;

        try
        {
            reader = new FileReader("res/db/db.properties");
            dBproperties.load(reader);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }


        try
        {

            Connection conn = DriverManager.getConnection("jdbc:h2:./res/db/classifica", dBproperties.getProperty("user"), dBproperties.getProperty("password"));
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT username, time '00:00:00' + tempo FROM Classifica ORDER BY tempo");

            System.out.println(ANSI_YELLOW + "\n**************************************************************"
                    + "\n                         CLASSIFICA:                          "
                    + "\n**************************************************************" + ANSI_RESET);

            while (rs.next())
            {
                i++;
                System.out.println(ANSI_CYAN + i + ") " + rs.getString(1) + " " + ANSI_RESET + rs.getTime(2));
            }

            System.out.println(ANSI_YELLOW + "**************************************************************\n" + ANSI_RESET);

            stm.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
