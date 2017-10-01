import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class AvgMapper extends Mapper<LongWritable, Text, Text, IntWritable> { 
  
    private IntWritable data = new IntWritable();
    private Text avg = new Text("Tweet Averaqe");
    private Text tweet1 = new Text();
    
    public void map(LongWritable key, Text line, Context context) throws IOException, InterruptedException {
     
    	   String[] fields = line.toString().split(";");
          if(fields.length == 4)
             {
    	
    	          data.set(fields[2].length());
 	              context.write(avg, data);
             }
        
    }
}
