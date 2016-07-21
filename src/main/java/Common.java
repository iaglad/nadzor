import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Common extends  Base
{
    String[] messengers = new String[] {
            "http://www.mztu.com.ua/",        //0
            "http://www.ujdvc.com.ua/"};                                 //1

    public Common(String _keywords, int i)
    {
        super(_keywords, "", "");
        BaseUrl = messengers[i];
        FindUrl = BaseUrl + "search_res.php?q=";

    }

    public void ProcessSearch() throws IOException
    {
        Document doc;
        Element table;
        Elements rows;
        URL u;
        int page;
        int newpage = 1;

        if (Keywords.length() > 0)
            do
            {
                page = newpage;
                u = new URL(FindUrl + URLEncoder.encode(Keywords, CharSet) + "&page=" + Integer.toString(page));
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.connect();
                BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSet));
                String content = "";
                String line;
                while ((line = r.readLine()) != null)
                {
                    content += line + "\n";
                }
                doc = Jsoup.parse(content, BaseUrl);
                Elements spot = null;
                try
                {
                    spot = doc.getElementById("major_content_left_top").select("a[href]");
                } catch (Exception e) {}
                if (spot != null)
                    for (Element link : spot)
                    {
                        String _s = link.outerHtml();
                        if (!_s.contains("find_article.php"))
                        {
                            if (_s.contains("Наступні")) newpage = page + 1;
                            continue;
                        }
                        String s = link.absUrl("href") + " | " + link.text();
                        Results.add(s);
                    }
                if (newpage == page)  break;
            } while (true);
    }
}
