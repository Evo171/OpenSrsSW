package Q1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

public class makeKeyword {// 3주차 
	private String input_file;
	private String output_flie = "./index.xml";
	
	public makeKeyword(String file) {
		this.input_file = file;
	}

	public void convertXml() {
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

            for (int temp = 0; temp < nList.getLength(); temp++) {
            	 String body = null ;
 	            String titleData=null;
                Node nNode = nList.item(temp);

                Element code = doc.createElement("doc");
	            docs.appendChild(code);
		     	 code.setAttribute("id", ""+temp+"");

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {// title과 body생성
                    Element eElement = (Element) nNode;
                    titleData=(String) eElement.getElementsByTagName("title").item(0).getTextContent();
                    body =(String) eElement.getElementsByTagName("body").item(0).getTextContent();
                    Element bodyT = doc.createElement("body");
                    KeywordExtractor ke= new KeywordExtractor();
    		     	KeywordList kl=ke.extractKeyword(body, true);
    		     	Keyword kwrd = null;
    		     	
    		     	for(int i=0;i<kl.size();i++) {//for문을 이용해 생성된 문자소 붙이기
    		     		 kwrd=kl.get(i);

    		     		 
    		     		bodyT.appendChild(doc.createTextNode("#"+kwrd.getString()+":"+kwrd.getCnt()+" "));
 
                     }

    		     	Element name = doc.createElement("title");
     	            name.appendChild(doc.createTextNode(titleData));
     	            code.appendChild(name);
	     	            code.appendChild(bodyT);
	     	            code = doc.createElement("doc");
	     	            docs.appendChild(code);

    		     	}
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();//xml생성
		      	  
	            Transformer transformer = transformerFactory.newTransformer();
	            
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
	            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); 
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new FileOutputStream(new File(output_flie)));

	            transformer.transform(source, result);

            }

        }
        catch(IOException | ParserConfigurationException | SAXException | NullPointerException | TransformerException e) {
            System.out.println(e);
		System.out.println("3주차 실행완료");
	}
	

	}
	}
		
		
