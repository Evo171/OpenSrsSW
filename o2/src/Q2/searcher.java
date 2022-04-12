package Q2;

import java.io.FileInputStream;//���� �ΰ��� ������ , �浹ȸ�� 
//�浹 �Ϻη� �߻�, �ι���ũ�� �ڻ���,�� ���� �ϳ��� �ܾ� ,����3.8 3.8�� ,�ܾ� 3�� 3����,������ �����ϸ� 1,�ٸ��� 0,05�� �߰� �ڻ���;
//
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class searcher {
	private String data_path;
	private String output_flie = "./index.post -q ";
	public searcher(String path) {
		
		this.data_path =path.substring(0,12);
		String query=path.substring(13);
		
		
		
		System.out.println(data_path);
		System.out.println(query);
		FileInputStream fileInStream;
		try {
			fileInStream = new FileInputStream(data_path);
		
		ObjectInputStream objectInputStream=new ObjectInputStream(fileInStream);
		Object object=objectInputStream.readObject();
		HashMap<String, String> object2 = (HashMap<String, String>)object;
		HashMap<String, String> hashMap=object2;
		
		
	
		 
		Iterator<String> it=hashMap.keySet().iterator();
		
		
		/*while(it.hasNext()) {
			String key=it.next();
		String value=hashMap.get(key);//
		 
     
		
			System.out.print(key+"->"+value);
			
		     System.out.println("  ");}*/
		/*for(double i:dWeight) {
			System.out.println(i);
		}*/		
		
		HashMap <String, Integer> kkey=new HashMap <String, Integer>();
		
		//ArrayList <Integer> weight=new ArrayList <Integer>();
		
		KeywordExtractor ke= new KeywordExtractor();
	    KeywordList kl=ke.extractKeyword(query, true);
	    
	     
	     for(int i=0;i<kl.size();i++) {//for���� �̿��� ������ ���ڼ� ���̱�
	    	 //double [kl.size()]=new double [];
	    	 Keyword kwrd=kl.get(i);
     		 
     		/*if(!kkey.containsKey(kwrd.getString())) {
     			kkey.put(kwrd.getString(),kwrd.getCnt());
     			
     		}
     		else {
     			int re=kkey.get(kwrd.getString());
     			re++;
     			kkey.replace(kwrd.getString(), re);
     		}*/
	    	 kkey.put(kwrd.getString(),kwrd.getCnt());
     		 
 
     		
     	        //qi1=kwrd.getCnt()+dWeight.get(0);    
 
     		}
     		 
     		 //System.out.println(kText);

	    
  		
	     CalcSim(kkey,hashMap);
	     objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     	
		
		
		
	}
	public static void CalcSim(HashMap <String, Integer> kkey,HashMap<String, String> hashMap) {
		

		
		Iterator<String> it=kkey.keySet().iterator();

		/*while(it.hasNext()) {
			String key=it.next();
		qKey.add(key);
			}*/
	
		double id0=0,id1=0,id2=0,id3=0,id4=0;
		 double f0 = 0;
		 double cos0=0,cos1=0,cos2=0,cos3=0,cos4=0;
		 
		ArrayList <Double> total=new ArrayList <Double>();
		while(it.hasNext()) {
			int count=0;
			double []f1= {};
			String key=it.next();
			ArrayList <Double> w=new ArrayList <Double>();
			//System.out.print(key+"->");
		if (hashMap.containsKey(key)) {
			Pattern pattern = Pattern.compile("[:](.*?)[ ]");
 	         Matcher matcher = pattern.matcher(hashMap.get(key));
 	         while (matcher.find()) {
 	        	 w.add(Double.parseDouble(matcher.group(1)));	 
 	             if (matcher.group(1) == null) break;
 	             
 	         }
 	        
 	         f0+=Math.pow(kkey.get(key),2);//���� ����ġ,Right
 	        
 	        	 
 	        	// f1[count]+=Math.pow(w.get(count),2);
 	         
 	         
 	        id0+=kkey.get(key)*w.get(0);
 	        id1+=kkey.get(key)*w.get(1);
 	        id2+=kkey.get(key)*w.get(2);
 	        id3+=kkey.get(key)*w.get(3);
 	        id4+=kkey.get(key)*w.get(4);
 	        cos0+=Math.pow(w.get(0), 2);
 	       cos1+=Math.pow(w.get(1), 2);
 	      cos2+=Math.pow(w.get(2), 2);
 	     cos3+=Math.pow(w.get(3), 2);
 	    cos4+=Math.pow(w.get(4), 2);
 	        
 	    
 	        
 	      // ��1 ����1 �и�1 ��� 1

 	      System.out.println(key+kkey.get(key)+w.get(0));
 	      System.out.println(key+kkey.get(key)+w.get(1));
 	      System.out.println(key+kkey.get(key)+w.get(2));
 	      System.out.println(key+kkey.get(key)+w.get(3));
 	     System.out.println(key+kkey.get(key)+w.get(4));
 	   
 	         }
		
		//System.out.println(f1);
		
		//total.add(Math.sqrt(f0)*Math.sqrt(f1[count]));
		//System.out.println(total.get(count));		
		}System.out.println("tdff -------------     ");

		System.out.println(cos0);
		System.out.println(cos1);
		System.out.println(cos2);
		System.out.println(cos3);
		System.out.println(cos4);
		total.add(Math.sqrt(f0)*Math.sqrt(cos0));
		total.add(Math.sqrt(f0)*Math.sqrt(cos1));
		total.add(Math.sqrt(f0)*Math.sqrt(cos2));
		total.add(Math.sqrt(f0)*Math.sqrt(cos3));
		total.add(Math.sqrt(f0)*Math.sqrt(cos4));
		
		for(Double i:total) {//Right
			System.out.println(i);
		}
		
		
		System.out.println(" -------------     ");
		/*for(int i=0;i<4;i++) {
			System.out.println(total.get(i));
		}*/
		 /*System.out.println(id0);
			System.out.println(id1);
		System.out.println(id2);
			System.out.println(id3);
			System.out.println(id4);*/
        
	       /* for(int i=0;i<5;i++)
	        	  System.out.println(w.get(i));
	        System.out.println("   ");*/
       
		
		double scores[]= {id0,id1,id2,id3,id4};
		
		 for (int i = 0; i < scores.length; i++) {
	            for (int j = i + 1; j < scores.length; j++) {
	                if (scores[i] < scores[j]) {
	                    double tmep = scores[i];
	                    scores[i] = scores[j];
	                    scores[j] = tmep;
	                }
	            }
	        }
		 String []title= {"��","���","���̽�ũ��","�ʹ�","�Ľ�Ÿ"};
		 HashMap <String,Double> print=new HashMap <String,Double>();
		 for(int i=0;i<5;i++) {
			 print.put(title[i],scores[i]);
			 
		 }
		 ArrayList<Double> numbers = new ArrayList<Double>();
		 numbers.add(id0);
		 numbers.add(id1);numbers.add(id2);numbers.add(id3);numbers.add(id4);
		 for(double i:numbers)
			 System.out.println(i);
		
		
		 Collections.sort(numbers,Collections.reverseOrder());
		 for(int i=0;i<3;i++) {
			 
		 if(numbers.get(i)!=0) {
     		if(numbers.get(i)==id0)
    			System.out.println((i+1)+"����:"+title[0]+":"+String.format("%.2f",numbers.get(i)) );
    		else if(numbers.get(i)==id1)
    			System.out.println((i+1)+"����:"+title[1]+":"+String.format("%.2f", numbers.get(i)) );
    		else if(numbers.get(i)==id2)
    			System.out.println((i+1)+"����:"+title[2]+":"+String.format("%.2f", numbers.get(i)) );
    		else if(numbers.get(i)==id3)
    			System.out.println((i+1)+"����:"+title[3]+":"+String.format("%.2f", numbers.get(i)) );
    		else if(numbers.get(i)==id4)
    			System.out.println((i+1)+"����:"+title[4]+":"+String.format("%.2f", numbers.get(i)) );
    	}

    	else 
    		System.out.println("�˻��� ������ �����ϴ�." );
			
			 
			 
		 }
		
		
	    }
	

		
		
			
		
		
	
	
	
	

	
}
