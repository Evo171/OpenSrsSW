package Q1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MidTerm {// 3주차 
	private String input_file="collection.xml";
	private String output_flie;
	String data1;
	String query;
	public MidTerm(String file) {
		this.input_file = file;
		
		this.data1=file.substring(0,12);
		String query=file.substring(13);
		
	}

	public void showSnippet() {
		try {
	     	File file = new File(input_file);//xml 파서부분
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            
            NodeList nList = document.getElementsByTagName("doc");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();            
            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true); 
            Element docs = doc.createElement("docs");
            doc.appendChild(docs);

            int count=0;
            for (int temp = 0; temp < nList.getLength(); temp++) {
            	 String body = null ;
 	            String titleData=null;
                Node nNode = nList.item(temp);

                Element code = doc.createElement("doc");
	            docs.appendChild(code);
		     	 code.setAttribute("id", ""+temp+"");

		     	 HashMap <String, Integer> hm=new HashMap <String, Integer>();
		     	 ArrayList <String> ar=new ArrayList <String> (); 
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    titleData=(String) eElement.getElementsByTagName("title").item(0).getTextContent();
                    body =(String) eElement.getElementsByTagName("body").item(0).getTextContent();
                    System.out.println(body);
                    KeywordExtractor ke= new KeywordExtractor();
    		     	KeywordList kl=ke.extractKeyword(query, true);
    		     	Keyword kwrd = null;
    		     	
    		     	for(int i=0;i<kl.size();i++) {
    		     		 kwrd=kl.get(i);
    		     		 if(body.contains(kwrd.getString())) {
    		     			 count++;
    		     			System.out.println(titleData+kwrd.getString()+":"+count);
    		     		 }   		     		 
    		     		//bodyT.appendChild(doc.createTextNode("#"+kwrd.getString()+":"+kwrd.getCnt()+" "));
 
                     }
 
    		     	}


            }

        }
        catch(IOException | ParserConfigurationException | SAXException | NullPointerException e) {
            System.out.println(e);
		
	}
	

	}

	}
		
		
