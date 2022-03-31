package Q1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class makeCollection {

	private String data_path;
	private String output_flie = "./collection.xml";
	
	public makeCollection(String path) {
		this.data_path = path;
	}
 
	public void makeXml(){
		String DATA_DIRECTORY = "C:/Users/ee/Desktop/data/"; 
		File dir = new File(DATA_DIRECTORY); 
		File files[] = dir.listFiles(); 
		
		org.jsoup.nodes.Document html; 
	
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();            
            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true); 
            Element docs = doc.createElement("docs");
            doc.appendChild(docs);
            Element code = doc.createElement("doc");
            docs.appendChild(code);
            for(int i=0;i<files.length;i++) {
    			html = Jsoup.parse(files[i],"UTF-8");
    			
    			String titleData=html.title();
    	        String bodyData=html.body().text();
            
 
            
            code.setAttribute("id", ""+i+"");
            
 
            
            Element name = doc.createElement("title");
            name.appendChild(doc.createTextNode(titleData));
            code.appendChild(name);
 
            
            Element bodyT = doc.createElement("body");
            bodyT.appendChild(doc.createTextNode(bodyData));
            code.appendChild(bodyT);

            code = doc.createElement("doc");
            docs.appendChild(code);
            }
 
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
      	  
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); 
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(new File(output_flie)));

            transformer.transform(source, result);
			
		} catch (IOException | TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		System.out.println("2주차 실행완료");
	}
   
		
      
			

        	
        
 
    }
    
    
           
            		
            		
            		
           
    	
        	
             
             
  
         
    	
    	
    	
   
 
}