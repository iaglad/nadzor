import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Zsmu extends Base
{
    String[] messengers = new String[] {
            "http://zmj.zsmu.edu.ua/",
            "http://pharmed.zsmu.edu.ua/"};

    public Zsmu(String _keywords, int i)
    {
        //super(_keywords, "http://zmj.zsmu.edu.ua/", "search");
        super(_keywords, "", "");
        BaseUrl = messengers[i];
        FindUrl = BaseUrl + "search";
    }

    public void ProcessSearch() throws IOException
    {
        if (Keywords.length() > 0)
        {
            int page = 1;
            Document doc;
            Element table, tmp, child, nodata = null;
            Elements rows;
            String s;

            do {
                doc = Jsoup.connect(FindUrl)
                        .data("query", Keywords)
                        .data("authors", "")
                        .data("title", "")
                        .data("abstract", "")
                        .data("galleyFullText", "")
                        .data("suppFiles", "")
                        .data("dateFromMonth", "")
                        .data("dateFromDay", "")
                        .data("dateFromYear", "")
                        .data("dateToMonth", "")
                        .data("dateToDay", "")
                        .data("dateToYear", "")
                        .data("dateToHour", "23")
                        .data("dateToMinute", "59")
                        .data("dateToSecond", "59")
                        .data("discipline", "")
                        .data("subject", "")
                        .data("type", "")
                        .data("coverage", "")
                        .data("indexTerms", "")
                        .data("searchPage", Integer.toString(page))
                        .userAgent(UserAgent)
                        .timeout(0)
                        .post();

                table = doc.select("TABLE[class = listing]").first();

                rows = table.select("tr");

                for (Element link : rows) {
                    tmp = link.getElementsByAttributeValue("valign", "top").first();
                    nodata = link.getElementsByClass("nodata").first();
                    if (nodata != null) { break;}
                    if (tmp != null) {
                        s = "";
                        child = tmp.children().get(2).children().get(0);
                        s = child.absUrl("href") + " | " + tmp.text();
                        Results.add(s);
                    }
                }
                page += 1;
            } while (nodata == null);
        }
    }

}
