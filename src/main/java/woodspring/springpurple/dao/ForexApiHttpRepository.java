package woodspring.springpurple.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;


@Component
public class ForexApiHttpRepository {
	
	private final String USER_AGENT = "Mozilla/5.0";
	private final String forexApiUrl = "http://www.freeforexapi.com/api/live";
	
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
	
	public String getAllSpotRate() {
		String prefix ="";
		StringBuffer strBuf = new StringBuffer();
		for (String item: pairList) {
			strBuf.append(prefix+item);
			prefix = ",";
		}
		try {
			prefix = sendGet(strBuf.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prefix;
	}
	
	// HTTP POST request
	public String sendPost() throws Exception {

		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
		return response.toString();

	}


}
