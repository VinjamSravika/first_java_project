package com.example.ruleengineservice.integration;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TwelveDataAPIClient {  // <- class starts here

    private static final String API_KEY = "31fc3e619ba444929c531ad509f7556b";  // Replace with your API key

    public static String getStockQuote(String symbol) {  // method inside the class
        String response = "";
        try {
            String urlString = "https://api.twelvedata.com/quote?symbol=" + symbol + "&apikey=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();

            response = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}  // <- class ends here
