package pkg1305;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;

/**
 *
 * @author Gergö Szijarto
 */
public class ExchangeRate {

    private BigDecimal rate;
    private final String base;
    private final String target;

    public ExchangeRate(String base, String target) throws IOException {
        this.base = base;
        this.target = target;
        this.rate = findRate();
    }

    public BigDecimal convert(BigDecimal amount) {
        return amount.multiply(rate);
    }

    public BigDecimal getRate() {
        return rate;
    }

    private BigDecimal findRate() throws MalformedURLException, IOException {
        // Create a neat value object to hold the URL

        String endpoint = "https://api.exchangeratesapi.io/v1/latest";
        String accesskey = "access_key=c561ae7ea4feb3d1b0033de2d4a62c1e";
        URL url = new URL(endpoint + "?" + accesskey + "&base=" + base + "&symbols=" + target);

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");

        // This line makes the request
        InputStream responseStream = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(responseStream);
        BufferedReader bufferedReader = new BufferedReader(isr);
        JSONTokener tokener = new JSONTokener(bufferedReader);
        JSONObject obj = new JSONObject(tokener);
        JSONObject rates = obj.getJSONObject("rates");
        BigDecimal r = rates.getBigDecimal(target);
        System.out.println(r);
        return r;
    }

}
