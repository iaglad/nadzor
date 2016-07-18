import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Tdmu extends Base
{
        String[] messengers = new String[] {
                "http://ojs.tdmu.edu.ua/index.php/act-pit-pediatr/",        //0
                "http://ojs.tdmu.edu.ua/index.php/visnyk-gigieny/",         //1
                "http://ojs.tdmu.edu.ua/index.php/visnyk-nauk-dos/",        //2
                "http://ojs.tdmu.edu.ua/index.php/zdobutky-eks-med/",       //3
                "http://ojs.tdmu.edu.ua/index.php/inf-patol/",              //4
                "http://ojs.tdmu.edu.ua/index.php/pharm-chas/",             //5
                "http://zmj.zsmu.edu.ua/",                                  //6
                "http://pharmed.zsmu.edu.ua/"};                             //7
    //private String keywords;
    //ArrayList<String> list;
    //int messenger;

    public Tdmu(String _keywords, int i)
    {
        super(_keywords, "", "");
        BaseUrl = messengers[i];
        FindUrl = BaseUrl + "search";
        //keywords = new String(_keywords);
        //list = new ArrayList();
        //messenger= i;
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
                        .post();

                table = doc.select("TABLE[class = listing]").first();

                rows = table.select("tr");

                for (Element link : rows)
                {
                    tmp = link.getElementsByAttributeValue("valign", "top").first();
                    nodata = link.getElementsByClass("nodata").first();
                    if (nodata != null) { break;}
                    if (tmp != null)
                    {
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
