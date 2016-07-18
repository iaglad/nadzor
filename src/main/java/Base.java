import java.io.IOException;
import java.util.ArrayList;

public class Base
{
    String Keywords;
    String FindUrl, BaseUrl, UserAgent;
    ArrayList<String> Results;
    String CharSet;

    public Base()
    {
        Keywords = "";
        FindUrl = "";
        BaseUrl = "";
        CharSet = "windows-1251";
        UserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36 OPR/36.0.2130.65";
        Results = null;
    }
    public Base(String _keywords, String _baseurl, String _findurl)
    {
        this();
        Keywords = _keywords;
        BaseUrl = _baseurl;
        FindUrl = BaseUrl + _findurl;
        Results = new ArrayList();
    }

    public void ProcessSearch() throws IOException
    {

    }
}