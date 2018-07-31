//import org.apache.http.client.utils.URIBuilder;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URISyntaxException;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        String dateformat = "y/M/d H:m:s";
//        SimpleDateFormat sdf = new SimpleDateFormat(dateformat, Locale.getDefault());
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.HOUR_OF_DAY, 2);
//        cal.add(Calendar.DAY_OF_MONTH, 8);
//        cal.add(Calendar.MONTH, 5);
//        Date d = cal.getTime();
//        String output = sdf.format(d);
//        System.out.println("Time=" + output);
//
//
//        String uri = "m_event_source_s:dhcp AND m_rcv_dt:[2017-01-19T22:00:00Z TO 2017-01-19T22:59:59Z]";
////        String uri = "https://rn2-lampp-lapp1249.rno.apple.com:1263/solr/lamp_q/query?q=m_rcv_dt:[2017-01-19T20:00:00Z TO 2017-01-19T20:59:59Z]&facet=true&facet.field=m_event_source_s";
//        String sQuery = "https://rn2-lampp-lapp1249.rno.apple.com:1263/solr/lamp_q/query?";
//        String queryString = "m_rcv_dt:[2017-01-19T20:00:00Z TO 2017-01-19T20:59:59Z]";
//        try {
////            System.out.print(URI.create(URLEncoder.encode(uri, "UTF-8")));
//            URIBuilder builder = new URIBuilder(sQuery);
////            String queryStringEncoded = URLEncoder.encode(queryString, "UTF-8");
//            builder.addParameter("q", uri);
////            builder.addParameter("facet", "true");
////            builder.addParameter("facet.field", "m_event_source_s");
//            builder.addParameter("rows", 0 + "");
//            builder.addParameter("wt", "json");
//            System.out.println(builder.toString());
//        }/* catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }*/ catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//}
