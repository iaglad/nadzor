import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.security.util.Length;

import java.io.IOException;
import java.util.ArrayList;

public class Nanu extends Base
{
    public Nanu(String _keywords)
    {
        super(_keywords, "http://dopovidi-nanu.org.ua/ru/", "");
    }

    public void ProcessSearch() throws IOException
    {
        Element el;
        String _s;
        int page = 0;
        String _f;
        Elements nav = null;

        if (Keywords.length() > 0)
        {
            try
            {
                do
                {
                    _s = BaseUrl + "search?page=" + Integer.toString(page) + "&f[search]=" + Keywords;
                    Document doc = Jsoup.connect(_s)
                            .userAgent(UserAgent)
                            .timeout(0)
                            .get();

                    Elements spot = null;
                    try
                    {
                        spot = doc.getElementsByClass("biblio-category-section");
                    } catch (Exception e) {}
                    if (spot !=null)
                    {
                        for (Element link : spot)
                        {
                            for (Element sublink : link.getAllElements())
                            {
                                if (!sublink.hasClass("biblio-entry")) { continue; }
                                Element tmp = sublink.getElementsByTag("a").first();
                                if (tmp != null) {
                                    String s = tmp.absUrl("href") + " | " + tmp.text();
                                    if (tmp.absUrl("href").toLowerCase().contains("/node/"))
                                    {
                                        Results.add(s);
                                    }
                                }
                            }
                        }
                    }
                    nav = doc.getElementsByClass("pager-next");
                    if (nav.size() == 0) { break; }
                    page +=1;
                } while (true);
            } catch (Exception e) {}
        }
    }
}
