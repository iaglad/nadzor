import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Odmu extends Base
{
    public Odmu(String _keywords)
    {
        super(_keywords, "http://journal.odmu.edu.ua", "");
    }

    public void ProcessSearch() throws IOException
    {
        int page = 1;
        Document doc = null;
        if (Keywords.length() > 0)
        {
            do
            {
                try
                {
                    doc = Jsoup.connect(FindUrl)
                            .data("s", Keywords)
                            .data("paged", Integer.toString(page))
                            .header("Connection", "keep-alive")
                            .userAgent(UserAgent)
                            .timeout(0)
                            .get();
                } catch (Exception e) {
                    break;}

                Elements spot = null, nav = null;
                try
                {
                    spot = doc.getElementsByClass("post");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                try
                {
                    nav = doc.getElementsByClass("nav-previous");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                if (spot != null)
                {
                    for (Element link : spot)
                    {
                        Element tmp = link.getElementsByTag("a").first();
                        String s = tmp.absUrl("href") + " | " + tmp.text();
                        Results.add(s);
                    }
                }
                if (nav == null) { break; }
                page += 1;
            } while (true);
        }
    }
}
