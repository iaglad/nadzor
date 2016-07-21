import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Ecpb extends Base
{
    public Ecpb(String _keywords)
    {
        super(_keywords, "http://ecpb.org.ua", "");
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
                    //spot = doc.getElementsByClass("entry-title");
                    spot = doc.getElementsByClass("hentry");
                } catch (Exception e) {}
                try
                {
                    nav = doc.getElementsByClass("nav-previous");
                } catch (Exception e) {}
                if (spot != null)
                {
                    for (Element link : spot)
                    {
                        Element tmp = link.getElementsByTag("a").first();
                        String s = tmp.absUrl("href");
                        tmp = link.getElementsByClass("entry-summary").first();
                        String ss = "no data";
                        try
                        {
                            ss = tmp.text().substring(0, 400);
                        } catch (Exception e)
                        {
                            ss = tmp.text();
                        }
                        s += " | " + ss;
                        Results.add(s);
                    }
                }
                if (nav == null) { break; }
                page += 1;
            } while (true);
        }
    }
}
