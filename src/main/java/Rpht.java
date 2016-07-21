import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Rpht extends Base
{
    public Rpht(String _keywords)
    {
        super(_keywords, "http://rpht.com.ua/", "");
    }

    public void ProcessSearch() throws IOException
    {
        Element el;
        String _s;

        if (Keywords.length() > 0)
        {
            _s = BaseUrl + "ru-site-search-/searchString/" + Keywords + "/page/1";
            do
            {
                Document doc = Jsoup.connect(_s)
                        .userAgent(UserAgent)
                        .timeout(0)
                        .get();
                Elements spot = null;
                try
                {
                    spot = doc.getElementsByClass("article");
                } catch (Exception e) {}
                if (spot !=null)
                {
                    for (Element link : spot)
                    {
                        Element tmp = link.getElementsByTag("a").last();
                        String s = tmp.absUrl("href") + " | " + tmp.text();
                        Results.add(s);
                    }
                }
                el = doc.select("li.next").first();
                if ((el == null) || el.hasClass("hidden")) {break;}
                _s = el.child(0).absUrl("href");
            } while (true);
        }
    }
}
