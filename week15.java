package la1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class week15 {

	public static void main(String[] args) throws MalformedURLException {
		String clientId = "eM_LFXZ44jLQAp8MmvJG";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
        String clientSecret = "dy1zYxhfqv";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";		
        try {
        	 Scanner sc = new Scanner(System.in);
             String st = sc.next();
			String text=URLEncoder.encode(st,"UTF-8");
			
			String apiURL="https://openapi.naver.com/v1/search/movie?query=" + text;
			URL url=new URL(apiURL);
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode=con.getResponseCode();
			BufferedReader br;
			if(responseCode==200) {
				br=new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br=new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			StringBuffer response=new StringBuffer();
			while((inputLine=br.readLine())!=null) {
				response.append(inputLine);
				//System.out.println(inputLine);
			}
			br.close();
			//System.out.println(response);
			
			JSONParser jsonParser=new JSONParser();
			JSONObject jsonObject=(JSONObject)jsonParser.parse(response.toString());
			JSONArray infoArray=(JSONArray) jsonObject.get("items");
			
			for(int i=0;i<infoArray.size();i++) {
				JSONObject itemObject=(JSONObject)infoArray.get(i);
				 System.out.println("=item_" + i + "==========================================");
				 //"        "
		         System.out.println("title:\t\t"+ itemObject.get("title"));
		         System.out.println("subtitle:\t" + itemObject.get("subtitle"));
		         System.out.println("director:\t" + itemObject.get("director"));
		         System.out.println("actor:\t\t" + itemObject.get("actor"));
		         System.out.println("userRating:\t" + itemObject.get("userRating") + "\n");
				
				
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
