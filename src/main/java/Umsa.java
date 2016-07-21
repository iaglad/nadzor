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
import java.util.LinkedHashMap;
import java.util.Map;

public class Umsa extends Base
{
    public Umsa(String _keywords)
    {
        super(_keywords, "http://www.umsa.edu.ua/", "phprusearch/");
    }

    public void ProcessSearch() throws IOException
    {
        URL u;
        Document doc;
        String content, _text;

        if (Keywords.length() > 0)
        {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("query", Keywords);
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet())
            {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), CharSet));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), CharSet));
            }
            byte[] postDataBytes = postData.toString().getBytes(CharSet);

            u = new URL(FindUrl);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            try (BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), CharSet)))
            {
                content = "";
                String line;
                while ((line = r.readLine()) != null) content += line;
            }

            doc = Jsoup.parse(content, BaseUrl);

            Elements spot = null;
            try
            {
                spot = doc.select("b");
            } catch (Exception e) { }

            if (spot != null)
            {
                String s = "";
                for (Element link : spot)
                {
                    try
                    {
                        _text = link.text();
                        if (_text.contains("http://")) { Results.add(_text + " | "); }
                    } catch (Exception e) { continue; }
                }
            }
        }
    }
}