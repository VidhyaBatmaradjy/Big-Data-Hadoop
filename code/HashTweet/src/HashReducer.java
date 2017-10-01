import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class HashReducer extends Reducer<Text, IntWritable, Text, IntWritable> {


     private IntWritable result = new IntWritable();
     
     public static float round(float value, int scale) {
    	    return (float)(Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale));
    	}
	
	 public void reduce(Text key, Iterable<IntWritable> values, Context context)

              throws IOException, InterruptedException {

       int sum =  0;
     	

        for (IntWritable value : values) {

            //complete code here
            
            sum+= value.get();
          
            

        }
        
         
      result.set(sum);

      context.write(key,result);
       

    }



}