import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Medix extends Base
{
    public Medix(String _keywords)
    {
        super(_keywords, "http://www.health-medix.com/", "search.php?search_item=1&search_str=");
    }

    public void ProcessSearch() throws IOException
    {
        int page=1;
        int newpage=1;
        Document doc;
        Element table;
        Elements rows;
        URL u;

        super.ProcessSearch();
        if (Keywords.length() > 0)
            do
            {
                u = new URL(FindUrl + URLEncoder.encode(Keywords, CharSet) + "&page=" + Integer.toString(page));
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.connect();
                String content;
                String s;
                int i;
                try (BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSet)))
                {
                    content = "";
                    String line;
                    while ((line = r.readLine()) != null) content += line;
                }
                doc = Jsoup.parse(content, BaseUrl);
                table = doc.getElementById("content_container_big");
                rows = table.select("li");
                s = "";
                for (Element link : rows)
                {
                    s = "";
                    s = doc.baseUri() + link.select("a").attr("href") + " | " + link.text();
                    Results.add(s);
                }

                s = table.getElementsByAttributeValue("height", "20%").first().select("a").last().attr("href");
                i = s.lastIndexOf("=");
                s = s.substring(i + 1, s.length());
                newpage = Integer.parseInt(s);
                if (newpage == page)    break;
                page = newpage;
            } while (true);
    }
}

