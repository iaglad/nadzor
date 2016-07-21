import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

public class Medpers extends Base
{
    public Medpers(String _keywords)
    {
        super(_keywords, "http://medpers.dsma.dp.ua/", "component/search/?ordering=newest&searchphrase=all&limit=0&searchword=");
    }

    public void ProcessSearch() throws IOException
    {
        int page = 1;
        Document doc = null;
        String _s;

        if (Keywords.length() > 0)
        {
                try
                {
                    _s = FindUrl + Keywords;
                    doc = Jsoup.connect(_s)
                            .header("Connection", "keep-alive")
                            .userAgent(UserAgent)
                            .timeout(0)
                            .get();
                } catch (Exception e) {}

                Elements spot = null, res = null;
                try
                {
                    spot = doc.getElementsByClass("search-results");
                    res = spot.first().getAllElements();
                } catch (Exception e) {}
                if ((spot != null) & (res != null))
                {
                    String s = "", ss = "";
                    Element tmp = null;

                    for (Element link : res)
                    {
                        if (link.hasClass("result-title"))
                        {
                            tmp = link.getElementsByTag("a").first();
                            s = tmp.absUrl("href");
                        }
                        if (link.hasClass("result-category")) {continue;}
                        if (link.hasClass("result-text"))
                        {
                            tmp = link.getElementsByClass("result-text").first();
                            ss = "no data";
                            try
                            {
                                ss = tmp.text().substring(0, 400);
                            } catch (Exception e)
                            {
                                ss = tmp.text();
                            }
                        }
                        if (link.hasClass("result-created"))
                        {
                            s += " | " + ss;
                            Results.add(s);
                            s = "";
                            ss = "";
                        }
                    }
                }
        }
    }
}
