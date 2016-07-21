import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Dec extends Base
{
    public Dec(String _keywords)
    {
        super(_keywords, "http://www.dec.gov.ua/", "index.php/ua/component/search");
    }

    public void ProcessSearch() throws IOException
    {
        super.ProcessSearch();
        if (Keywords.length() > 0)
        {
            Document doc = Jsoup.connect(FindUrl)
                    .data("searchword", Keywords)
                    .data("limit", "0")
                    .data("ordering", "newest")
                    .data("searchphrase", "all")
                    .data("areas[0]", "faqbookpro")
                    .data("areas[1]", "tags")
                    .data("areas[2]", "categories")
                    .data("areas[3]", "contacts")
                    .data("areas[4]", "content")
                    .data("areas[5]", "newsfeeds")
                    .data("areas[6]", "weblinks")
                    .header("Connection", "keep-alive")
                    .userAgent(UserAgent)
                    .timeout(0)
                    .post();
            Elements spot = null;
            try
            {
                spot = doc.getElementsByClass("search-results").first().getAllElements();
            } catch (Exception e) {}
            if (spot != null)
            {
                String s = "";
                for (Element link : spot)
                {
                    if ("result-title".equalsIgnoreCase(link.className()))
                        s = link.getElementsByTag("a").first().absUrl("href") + " | " + link.getElementsByTag("a").first().text();
                    if ("result-category".equalsIgnoreCase(link.className()))
                        s = s + " [" + link.getElementsByClass("result-category").first().text() + "]";
                    if ("result-text".equalsIgnoreCase(link.className()))
                        s = s + " [" + link.getElementsByClass("result-text").first().text() + "]";
                    if ("result-created".equalsIgnoreCase(link.className())) Results.add(s);
                }
            }
        }
    }
}
