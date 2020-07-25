package App;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;

public class Test {


    public static void main(String[] args) {
        BigDecimal payoutId = new BigDecimal("157322245567");
        System.out.println(payoutId);
    }

    private static String encode(String url)
    {
        try {
            String encodeURL= URLEncoder.encode(url, "UTF-8" );
            return encodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" +e.getMessage();
        }
    }

}
