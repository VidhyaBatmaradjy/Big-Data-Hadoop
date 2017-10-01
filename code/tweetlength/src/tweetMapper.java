import java.io.IOException;
//import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//public class tweetMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

  public class tweetMapper extends Mapper<LongWritable, Text, Text, IntWritable> { 
    private final IntWritable one = new IntWritable(1);
    //private Text data = new Text();
    private IntWritable data = new IntWritable();
    
    private Text bins = new Text();
    
    public void map(LongWritable key, Text line, Context context) throws IOException, InterruptedException {
     
    	int minrange=0;
        int maxrange=0;
    	
    String[] fields = line.toString().split(";");
   
    if(fields.length == 4)
     {
     data.set(fields[2].length());
     
    maxrange= (int)(Math.ceil((double)fields[2].length()/5)*5);
    minrange= (int)((Math.ceil((double)fields[2].length()/5)*5) - 4);
     
    bins.set(String.valueOf(minrange)+ "-" +String.valueOf(maxrange));   
    context.write(bins,one);
     
     
   //   context.write(data,one);
     
     
     }
        
           }
}
