import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.awt.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Locale;

public class HashMapper extends Mapper<LongWritable, Text, Text, IntWritable> { 
    
    private final IntWritable one = new IntWritable(1);
    private Text data = new Text();
	
    public void map(LongWritable key, Text line, Context context) throws IOException, InterruptedException {
         	    	
           String[] fields = line.toString().split(";");
           
	    if(fields.length == 4) {
    	       ArrayList<String> hashtags = new ArrayList<String>();
    	       ArrayList<String> words    = new ArrayList<String>();
    	       String twcontent           = fields[2].toLowerCase();
    	       Matcher matcher            = Pattern.compile("#\\s*([^#]+$|\\w+)").matcher(twcontent.trim());
    	
	       while (matcher.find()) {
       	            String supporttweet="";   		   
    		    for (char c : matcher.group(1).toCharArray()) {
			   if(Character.isLetter(c)) {
			        supporttweet += c;
		           }
			   else
			        {break;}	
		    }
    		    if(supporttweet.startsWith("team")) {   
    		           String check =  supporttweet.substring(4);
			   if(check.endsWith("go")) {
			        words.add(check.substring(0, check.length() - 2));
	    		   }
			   else {
				words.add(supporttweet.substring(4));
			   }
    		     }
             	     else if(supporttweet.startsWith("go")) {
         		    if(supporttweet.startsWith("goteam")) { 
    	    		 	  String check =  supporttweet.substring(6);
    				  if(check.endsWith("go")) {
    				       words.add(check.substring(0, check.length() - 2));
				  }
    				  else {
    				       words.add(supporttweet.substring(6));
    				  }
        		    } 
    			    else {
    		    		   String check =  supporttweet.substring(2);
     				
     				if(check.endsWith("go")) {
    				     words.add(check.substring(0, check.length() - 2));
    		    		}
     				else {
     				     words.add(supporttweet.substring(2));
     				}
    		    	    } 
    		     } 
    	      	     else if(supporttweet.startsWith("support")) {
    		          if(supporttweet.startsWith("supportteam")) {
    			       words.add(supporttweet.substring(11));
    			  }
    		          else {
    			       words.add(supporttweet.substring(7));
    		          }
    	            }
    	       }
    	
    	       String[] locales = Locale.getISOCountries();	
    	       for (String word : words) {
    		    for (String countryCode : locales) {
          		  Locale obj     = new Locale("", countryCode);
                          String country = obj.getDisplayCountry().toLowerCase();
			  String iso     = obj.getISO3Country().toLowerCase();
    	                  String code    = obj.getCountry().toLowerCase();
    			  
			  if((country.equals(word)) ||(iso.equals(word)) ||(code.equals(word))) {
    			       data.set(country);
        		       context.write(data,one);
    			  }  
    		     }
    	       }
    	  }
    }
}
