import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Therapia extends Base
{
    public Therapia(String _keywords)
    {
        super(_keywords, "http://therapia.ua/", "search");
    }

    public void ProcessSearch() throws IOException
    {
        if (Keywords.length() > 0)
        {
            Document doc = Jsoup.connect(FindUrl)
                    .data("todo", "search")
                    .data("page", "")
                    .data("pagesize", "999999")
                    .data("keywords", Keywords)
                    .data("chapterid", "0")
                    .data("x", "10")
                    .data("y", "8")
                    .header("Connection", "keep-alive")
                    .userAgent(UserAgent)
                    .timeout(0)
                    .post();
            Elements spot = null;
            try
            {
                spot = doc.getElementsByClass("searchspot");
            } catch (Exception e) {}

            if (spot != null)
            {
                for (Element link : spot)
                {
                    Element tmp = link.getElementsByTag("a").first();
                    String s = tmp.absUrl("href") + " | " + tmp.text();
                    Results.add(s);
                }
            }
        }
    }
}
