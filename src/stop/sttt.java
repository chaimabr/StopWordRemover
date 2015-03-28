package stop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
public class sttt {
	static List<String> stopWords = new LinkedList<String>();
	
	static String str;
	static String dataDirectory,tweetsFile;
	

	static Gson gson = new Gson();
	public static void main(String[] args) {
		
			
			
				try
				{
					
				        BufferedReader br = new BufferedReader(new FileReader("D:\\stop\\test.json"));  
				        BufferedReader sw = new BufferedReader(new FileReader("D:\\stop\\stopwords.txt")); 
				        while((str = sw.readLine()) != null) 
						{	
							stopWords.add(str);
						}
				        sw.close();
				      
						FileWriter fileWrite = new FileWriter(new File("D:\\stop\\test1.json"));
						//BufferedWriter bufferWr = new BufferedWriter(fileWrite);
						
						while((str = br.readLine()) != null) 
						{	
				              Tweet p = gson.fromJson(str, Tweet.class);
				              Tweet pt = new Tweet();
				            //  System.out.print(p.getCreated_at());
				            if (!(p==null)){
				            	 
				                if (!(p.getText()==null)){
				                	//System.out.print(p.getText()+"\n");
				                	 String s= p.getText();
								     String[] words = s.split(" ");
								     words = s.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
								 	 ArrayList<String> keywords = new ArrayList<String>();

								     for(String word : words)
								     {
								         String wordCompare = word.toLowerCase();
								         if(!stopWords.contains(wordCompare) && !keywords.contains(wordCompare))
								         {
								             keywords.add(word);
								         }
								     }
								    
								     String listString = "";

								     for (String c : keywords)
								     {
								         listString += c + " ";
								     }  
								   
								     pt=p;
								     pt.setText(listString);
								     String pjson = gson.toJson(pt); 
								      System.out.println(p.getText());
								      fileWrite.write(pjson+"\n");  
								     
								}
							}
				          
						}
						//bufferWr.close();
						br.close();
						fileWrite.close(); 
						new File("D:\\stop\\test.json").delete();
						new File("D:\\stop\\test1.json").renameTo(new File("D:\\stop\\test.json"));
					    /////////////////////////******************//////////////////////////////////
					
					
					
				
		        }
		        catch (Exception e)
		        {
		        	e.printStackTrace();
		        }
				
			}




	}

