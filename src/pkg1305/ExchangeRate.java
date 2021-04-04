package pkg1305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;

/**
 *
 * @author Gergö Szijarto
 */
public class ExchangeRate {

    private final BigDecimal rate;
    private final String base;
    private final String target;
    private final String accesskey = "c561ae7ea4feb3d1b0033de2d4a62c1e";

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
        // Construct the request URL
        String endpoint = "https://api.exchangeratesapi.io/v1/latest";
        URL url = new URL(endpoint + "?access_key=" + accesskey + "&base=" + base + "&symbols=" + target);

        // Open a connection on the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request headers
        connection.setRequestProperty("accept", "application/json");

        // This line makes the request
        InputStream responseStream = connection.getInputStream();
        // Parse the response into a json object
        InputStreamReader isr = new InputStreamReader(responseStream);
        BufferedReader bufferedReader = new BufferedReader(isr);
        JSONTokener tokener = new JSONTokener(bufferedReader);
        JSONObject obj = new JSONObject(tokener);
        // Find exchange rate for target currency
        JSONObject rates = obj.getJSONObject("rates");
        BigDecimal r = rates.getBigDecimal(target);
        return r;
    }

}
