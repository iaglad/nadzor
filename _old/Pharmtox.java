import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Pharmtox extends Base
{
    public Pharmtox(String _keywords)
    {
        super(_keywords, "http://pharmtox-j.org.ua/", "");
    }

    public void ProcessSearch() throws IOException
    {
        Element el;
        String _s;

        if (Keywords.length() > 0)
        {
            _s = BaseUrl + "ru/search/node/" + Keywords;
            do
            {
                Document doc = Jsoup.connect(_s)
                        .userAgent(UserAgent)
                        .timeout(0)
                        .get();
                Elements spot = null;
                try
                {
                    spot = doc.getElementsByClass("title");
                } catch (Exception e) {}
                if (spot !=null)
                {
                    for (Element link : spot)
                    {
                        Element tmp = link.getElementsByTag("a").last();
                        if (tmp == null) {continue;}
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
