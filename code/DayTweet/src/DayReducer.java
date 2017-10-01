import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DayReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)

			throws IOException, InterruptedException {

		int sum = 0;

		for (IntWritable value : values) {

			// complete code here

			sum += value.get();

		}

		// float average = round(((float)sum/count),2);

		result.set(sum);

		context.write(key, result);

	}

}