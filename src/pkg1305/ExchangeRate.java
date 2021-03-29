package pkg1305;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

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
        URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + base + "&symbols=" + target);

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");

        // This line makes the request
        InputStream responseStream = connection.getInputStream();

        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        String returnThis = result.split(":", 0)[2].split("}", 0)[0];
        System.out.println(returnThis);
        return new BigDecimal(returnThis);
    }

}

