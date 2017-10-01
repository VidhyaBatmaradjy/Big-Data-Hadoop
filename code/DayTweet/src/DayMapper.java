import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DayMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final IntWritable one = new IntWritable(1);

	private Text data = new Text();

	public void map(LongWritable key, Text line, Context context) throws IOException, InterruptedException {

	        String[] fields     = line.toString().split(";");
		String riofromDate  = "2016-08-05";
		String riotoDate    = "2016-08-21";
		String parafromDate = "2016-09-07";
		String paratoDate   = "2016-09-18";

		if (fields.length == 4) {
			String strValue = String.valueOf(fields[0]);
			if (strValue.matches("^\\d+(\\.\\d+)?")) {

			    Date tweetsdate       = new Date(Long.parseLong(fields[0].trim()));
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    String strDate        = dateFormat.format(tweetsdate);

		            if ((strDate.compareTo(riofromDate) >= 0 && strDate.compareTo(riotoDate) <= 0) || (strDate.compareTo(parafromDate) >= 0 && strDate.compareTo(paratoDate) <= 0)) {

				 data.set(strDate);
				 context.write(data, one);
			    }

			} else {
				// not okay !
			}

		}

	}
}
