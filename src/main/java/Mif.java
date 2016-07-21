import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

public class Mif extends Base
{
    public Mif(String _keywords)
    {
        super(_keywords, "http://www.mif-ua.com/", "");
    }

    public void ProcessSearch() throws IOException
    {
        int page = 1;
        Document doc = null;
        String _s;

        if (Keywords.length() > 0)
        {
            do
            {
                try
                {
                    _s = BaseUrl + "search/?page=" + Integer.toString(page) + "&q=" + Keywords;
                    doc = Jsoup.connect(_s)
                            .userAgent(UserAgent)
                            .timeout(0)
                            .get();
                } catch (Exception e) {break;}

                Elements spot = null, nav = null;
                try
                {
                    spot = doc.getElementsByClass("header");
                } catch (Exception e) {}
                try
                {
                    nav = doc.getElementsByClass("next");
                } catch (Exception e) {}
                if (spot != null)
                {
                    for (Element link : spot)
                    {
                        Element tmp = link.getElementsByTag("a").last();
                        String s = tmp.absUrl("href") + " | " + tmp.text();
                        Results.add(s);
                    }
                }
                if ((nav == null) || (nav.hasClass("disabled"))) { break; }
                page += 1;
            } while (true);
        }
    }
}
