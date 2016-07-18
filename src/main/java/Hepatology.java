import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Hepatology extends Base
{
    public Hepatology(String _keywords)
    {
        super(_keywords, "http://hepatology.org.ua/ru/", "page/1/?s=");
    }

    public void ProcessSearch() throws IOException
    {
        Element el;
        String _s;
        int page = 1;
        String _f;

        if (Keywords.length() > 0)
        {
            //_s = FindUrl + Keywords;
            try
            {
                do
                {
                    _s = BaseUrl + "page/" + Integer.toString(page) + "/?s=" + Keywords;
                    Document doc = Jsoup.connect(_s)
                            .userAgent(UserAgent)
                            .timeout(0)
                            .get();

                    Elements spot = null;
                    try
                    {
                        spot = doc.getElementsByClass("col-md-8").last().children();
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
                            if ((link.hasClass("hentry")) & !(tmp.absUrl("href").toLowerCase().contains("pro-zhurnal"))) { Results.add(s); }
                        }
                    }
                    page +=1;
                } while (true);
            } catch (Exception e) {}
        }
    }
}
