import java.io.IOException;
import java.util.ArrayList;

public class Trest
{
    public static void main(String[] args) throws IOException
    {
        PharmFinder pf = new PharmFinder();

        //ArrayList<String> results = pf.Find("лікування");
        //ArrayList<String> results = pf.Find("синтез");
        ArrayList<String> results = pf.Find("интерферон");


        for( String entry : results )
        {
            System.out.println( entry );
        }
        System.out.println( results.size() );
   }
}