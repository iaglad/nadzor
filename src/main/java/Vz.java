import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Vz extends Base
{
    public Vz(String _keywords)
    {
        super(_keywords, "http://www.vz.kiev.ua/", "page/1/?s=");
    }

    public void ProcessSearch() throws IOException
    {
        Element el;
        String _s;

        if (Keywords.length() > 0)
        {
            _s = FindUrl + Keywords;
            do
            {
                Document doc = Jsoup.connect(_s)
                        .userAgent(UserAgent)
                        .timeout(0)
                        .get();
                Elements spot = null;
                try
                {
                    spot = doc.getElementsByClass("item");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                if (spot !=null)
                {
                    for (Element link : spot)
                    {
                        Element tmp = link.getElementsByTag("a").first();
                        String s = tmp.absUrl("href") + " | " + tmp.text();
                        Results.add(s);
                    }
                }
                el = doc.select("a.next").first();
                if (el !=null) { _s = el.absUrl("href");}
                //System.out.println(_s);
            } while (el !=null);
        }
    }
}
