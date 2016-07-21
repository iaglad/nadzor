//import com.sun.org.apache.xpath.internal.operations.String;

import java.io.IOException;
import java.util.ArrayList;

public class PharmFinder {
    public PharmFinder()
    {

    }
    public ArrayList<String> Find(String keywords) throws IOException
    {
        Integer finders_count = 24;
        ArrayList<String> results = new ArrayList<String>();

//        System.out.println("therapia - 1 - " + Integer.toString(finders_count));
//        Therapia therapia = new Therapia(keywords);
//        therapia.ProcessSearch();
//        results.add("Результаты с сайта http://therapia.ua/ ");
//        results.addAll(therapia.Results);
//
//        System.out.println("xdmu 0 - 2 - " + Integer.toString(finders_count));
//        Xdmu xdmu = new Xdmu(keywords, 0);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[0]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("xdmu 1 - 3 - " + Integer.toString(finders_count));
//        xdmu = null;
//        xdmu = new Xdmu(keywords, 1);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[1]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("xdmu 2 - 4 - " + Integer.toString(finders_count));
//        xdmu = null;
//        xdmu = new Xdmu(keywords, 2);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[2]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("xdmu 3 - 5 - " + Integer.toString(finders_count));
//        xdmu = null;
//        xdmu = new Xdmu(keywords, 3);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[3]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("xdmu 4 - 6 - " + Integer.toString(finders_count));
//        xdmu = null;
//        xdmu = new Xdmu(keywords, 4);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[4]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("xdmu 5 - 7 - " + Integer.toString(finders_count));
//        xdmu = null;
//        xdmu = new Xdmu(keywords, 5);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[5]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("vz - 8 - " + Integer.toString(finders_count));
//        Vz vz = new Vz(keywords);
//        vz.ProcessSearch();
//        results.add("Результаты с сайта http://www.vz.kiev.ua/ ");
//        results.addAll(vz.Results);
//
//        System.out.println("zmj.zsmu.edu.ua - 9 - " + Integer.toString(finders_count));
//        xdmu = null;
//        xdmu = new Xdmu(keywords, 6);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[6]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("medix - 10 - " + Integer.toString(finders_count));
//        Medix medix = new Medix(keywords);
//        medix.ProcessSearch();
//        results.add("Результаты с сайта health-medix.com");
//        results.addAll(medix.Results);
//
//        System.out.println("mztu - 11 - " + Integer.toString(finders_count));
//        Common mztu = new Common(keywords, 0);
//        mztu.ProcessSearch();
//        results.add("Результаты с сайта mztu.com.ua");
//        results.addAll(mztu.Results);
//
//        System.out.println("odmu - 12 - " + Integer.toString(finders_count));
//        Odmu odmu = new Odmu(keywords);
//        odmu.ProcessSearch();
//        results.add("Результаты с сайта odmu.edu.ua");
//        results.addAll(odmu.Results);
//
//        System.out.println("dec - 13 - " + Integer.toString(finders_count));
//        Dec dec = new Dec(keywords);
//        dec.ProcessSearch();
//        results.add("Результаты с сайта dec.gov.ua");
//        results.addAll(dec.Results);
//
//        System.out.println("pharmed.zsmu.edu.ua - 14 - " + Integer.toString(finders_count));
//        xdmu = null;
//        xdmu = new Xdmu(keywords, 7);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[7]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("nphj.nuph.edu.ua - 15 - " + Integer.toString(finders_count));
//        xdmu = null;
//        xdmu = new Xdmu(keywords, 8);
//        xdmu.ProcessSearch();
//        results.add("Результаты с сайта " + xdmu.messengers[8]);
//        results.addAll(xdmu.Results);
//
//        System.out.println("hepatology - 16 - " + Integer.toString(finders_count));
//        Hepatology hepatology = new Hepatology(keywords);
//        hepatology.ProcessSearch();
//        results.add("Результаты с сайта http://hepatology.org.ua");
//        results.addAll(hepatology.Results);
//
//        System.out.println("dopovidi-nanu - 17 - " + Integer.toString(finders_count));
//        Nanu nanu = new Nanu(keywords);
//        nanu.ProcessSearch();
//        results.add("Результаты с сайта http://dopovidi-nanu.org.ua");
//        results.addAll(nanu.Results);
//
//        System.out.println("ecpb - 18 - " + Integer.toString(finders_count));
//        Ecpb ecpb = new Ecpb(keywords);
//        ecpb.ProcessSearch();
//        results.add("Результаты с сайта http://ecpb.org.ua/");
//        results.addAll(ecpb.Results);
//
//        System.out.println("mif-ua - 19 - " + Integer.toString(finders_count));
//        Mif mif = new Mif(keywords);
//        mif.ProcessSearch();
//        results.add("Результаты с сайта http://mif-ua.com/");
//        results.addAll(mif.Results);
//
//        System.out.println("medpers - 20 - " + Integer.toString(finders_count));
//        Medpers medpers = new Medpers(keywords);
//        medpers.ProcessSearch();
//        results.add("Результаты с сайта http://medpers.dsma.dp.ua");
//        results.addAll(medpers.Results);
//
//        System.out.println("rhpt - 21 - " + Integer.toString(finders_count));
//        Rpht rpht = new Rpht(keywords);
//        rpht.ProcessSearch();
//        results.add("Результаты с сайта http://rpht.com.ua");
//        results.addAll(rpht.Results);
//
//        System.out.println("umsa - 22 - " + Integer.toString(finders_count));
//        Umsa umsa = new Umsa(keywords);
//        umsa.ProcessSearch();
//        results.add("Результаты с сайта http://umsa.edu.ua");
//        results.addAll(umsa.Results);
//
//        System.out.println("ujdvc - 23 - " + Integer.toString(finders_count));
//        Common ujdvc = new Common(keywords, 0);
//        ujdvc.ProcessSearch();
//        results.add("Результаты с сайта http://ujdvc.com.ua");
//        results.addAll(ujdvc.Results);
//

        return results;
    }
}
