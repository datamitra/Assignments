import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MRDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
		conf.set("searchword", "hadoop");
		
		//System.out.println("NN address is::::"+conf.get("fs.defaultFS"));
		//System.out.println("No of reuducers::::"+conf.get("mapreduce.job.reduces"));

		conf.set("mapreduce.job.reduces", "0");	
		System.out.println("No of reuducers::::"+conf.get("mapreduce.job.reduces"));

		conf.set("searchword", "spark");
		
		
		Job job=Job.getInstance(conf, "Q1Mapper");
		job.setJarByClass(MRDriver.class);
		job.setMapperClass(GrepMapper.class);
		//job.setReducerClass(Q1Reducer.class);
		//job.setNumReduceTasks(0);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		/*job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		*/
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true)?0:1);
		
		
	}
}
