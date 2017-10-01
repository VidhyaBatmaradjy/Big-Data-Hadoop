import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class AvgReducer extends Reducer<Text, IntWritable, Text, FloatWritable> {


     private FloatWritable result = new FloatWritable();
     
     public static float round(float value, int scale) {
    	    return (float)(Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale));
    	}
	
     public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        long sum   =  0;
        long count =  0;
       
        for (IntWritable value : values) {

            sum  += value.get();
            count+= 1;
        }
        
        float average = sum/count;
        result.set((int) average);
        context.write(key,result);
       
    }

}
