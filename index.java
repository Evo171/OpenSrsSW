package Q1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class index {
	public static double idf(List<List<String>> lists, String word) 
	{ 
		double n = 5; 
	
	    for (List<String> list : lists) 
	
	    { for (String targetWord : list) 
	
	    { if (word.equalsIgnoreCase(targetWord)) 
	{ ; 
	break;
	} } 
	} 
	
	return Math.log(lists.size() / n); }
	
	public static double tfIdf( List<List<String>> lists, String word,int key) 
	{ 
		return  key*idf(lists, word); 
		}

	public static void main(String [] args) {
		try {

			
			File file = new File("index.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
        
            NodeList nList = document.getElementsByTagName("docs");
            FileOutputStream fileStream=new FileOutputStream("./index.post");
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileStream);
			
			HashMap<String, String> indexMap=new HashMap<String, String>();
			List<List<String>> documents = null;
			
			
			//ArrayList <String>ir = new ArrayList<String> ();
			Node nNode = nList.item(0);
			String body = null ;
			
            for (int temp = 0; temp <5; temp++) {
            	ArrayList <String>ar = new ArrayList<String> ();
            	ArrayList <String>ir = new ArrayList<String> ();
                Element eElement = (Element) nNode;     	
                  
                  
                    body =(String) eElement.getElementsByTagName("body").item(temp).getTextContent();
                    
                    
                    Pattern pattern = Pattern.compile("[#](.*?)[:]");
                    Matcher matcher = pattern.matcher(body);
                    while (matcher.find()) {
                    	//System.out.println(matcher.group(1));
                        ar.add(matcher.group(1));
                        if (matcher.group(1) == null) break;
                       
                    } 
                    /*for(String i:ar) {
        	     		System.out.print(temp+i);
        	     		
        	     		
        	     	}*/
                    
                    
                    Pattern pattern1 = Pattern.compile("[:](.*?)[ ]");
                    Matcher matcher1 = pattern1.matcher(body);
                    while (matcher1.find()) {

                        ir.add(matcher1.group(1));
                        if (matcher1.group(1) == null) break;
                    }
                    
                    List<String> doc1 = Arrays.asList(body);
                    documents = Arrays.asList(doc1);
                   
                 // System.out.println(ar.size());
        	     //System.out.println(ir.size());
        	     	
           for(int i=0;i<ar.size();i++) {//ÇØ½Ã¸Ê »ý¼º
        	   
        	
        	   
	     		String name=null;
	     		name=ar.get(i);
        	   int cnt=0;
        	   
	    	 cnt=Integer.parseInt(ir.get(i));
	    		
	    		
	    		
	    		String[] tList=new String [5];
	     		String entry=" ";
	     		for(int k=0;k<5;k++) {
	     			
	     			tList[k]=" "+k+":"+0.0;
	     			entry+=tList[k];
	     			
	     		}
	     
	     		if(!(indexMap.containsKey(ar.get(i)))) {
	     	
	    		indexMap.put(ar.get(i),entry);
	     		}
	     		if(cnt>0) {
	    			String tt=indexMap.get(name);
					String alter=" "+temp+":"+0.0;
					String te=" "+temp+":"+-Math.round(tfIdf(documents,ar.get(i),cnt) * 100) / 100.0;
					if(tt.contains(alter)) {
						tt=tt.replace(alter,te);
						
						indexMap.replace(ar.get(i), tt);
					}
					else {
						indexMap.replace(ar.get(i), tt);
					}
						
				}
				
				}

           
	     	}objectOutputStream.writeObject(indexMap);
	     	
	     	
            objectOutputStream.close();
	     	
	     	
	     	
	     	FileInputStream fileInStream=new FileInputStream("C:\\Users\\ee\\SimpleR\\q1\\index.post");
			ObjectInputStream objectInputStream=new ObjectInputStream(fileInStream);
			Object object=objectInputStream.readObject();
			HashMap<String, String> object2 = (HashMap<String, String>)object;
			HashMap<String, String> hashMap=object2;

		
			 
			Iterator<String> it=hashMap.keySet().iterator();
			while(it.hasNext()) {
				String key=it.next();
			String value=hashMap.get(key);
				System.out.print(key+"->"+value);
			     System.out.println("  ");}
				
            			
			objectInputStream.close();
             
	     	

            
		} catch (IOException | ParserConfigurationException | SAXException |NullPointerException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

	
}
