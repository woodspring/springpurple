package woodspring.springpurple.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Component;


@Component
public class InvestingRepository {
	
	private final String USER_AGENT = "Mozilla/5.0";
	private final String forexApiUrl = "https://ca.investing.com/currencies/";
	
	private ArrayList<String> pairList = new ArrayList<String>();
	
	// HTTP GET request
	public String sendGet(String query) throws Exception {

		String urlStr = ( query == null || query.isEmpty()) ? forexApiUrl : forexApiUrl+"?pairs="+query;
		
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + urlStr);
		System.out.println("Response Code : " + responseCode);
		System.out.println("location : "+ con.getHeaderField("Location"));

		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
int index=0;
		while ((inputLine = in.readLine()) != null) {
System.out.println("line "+index+++" ->["+ inputLine+"]");
			response.append(inputLine);
			if ( inputLine.contains("supportedPairs")) {
				String[] twoStr = inputLine.split("\\:\\[");
				System.out.println("twoStr:"+ twoStr[0]);
				System.out.println("twoStr:"+ twoStr[1]);
				String[] symbleList = twoStr[1].split(",");
				for ( String item : symbleList) {
					if (!item.contains("code")) {
						pairList.add(item.substring(1,7));
						
					}
				}
			}
		}
		in.close();

		//print result
		System.out.println(response.toString());
		System.out.println("pairList:"+ pairList);
		return response.toString();

	}
	
	// HTTP GET request
	public String sendGetFxOption(String query) throws Exception {

		String urlStr = ( query == null || query.isEmpty()) ? forexApiUrl+"forex-options" : forexApiUrl+"?pairs="+query;
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + urlStr);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
int index=0;
		while ((inputLine = in.readLine()) != null) {
System.out.println("line "+index+++" ->["+ inputLine+"]");
			response.append(inputLine);
			if ( inputLine.contains("supportedPairs")) {
				String[] twoStr = inputLine.split("\\:\\[");
				System.out.println("twoStr:"+ twoStr[0]);
				System.out.println("twoStr:"+ twoStr[1]);
				String[] symbleList = twoStr[1].split(",");
				for ( String item : symbleList) {
					if (!item.contains("code")) {
						pairList.add(item.substring(1,7));
						
					}
				}
			}
		}
		in.close();

		//print result
		System.out.println(response.toString());
		System.out.println("pairList:"+ pairList);
		return response.toString();

	}

}
