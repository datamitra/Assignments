import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet.P;


public class MRDriver extends Configured implements Tool{
	@Override
	public int run(String[] arg0) throws Exception {
		
		Integer i=1000;
		System.out.println("hash code of int 1000:::"+i.hashCode());
		System.out.println("hash code of String Hi::"+"HI".hashCode());
		
		
		
		for (int j = 0; j < arg0.length; j++) {
			System.out.println("!!!!!args in RUN  are:::"+arg0[j]);
		}
		//Configuration conf=new Configuration();
		Configuration conf=this.getConf();
		Job job=Job.getInstance(conf, "Q1Mapper");
		job.setJarByClass(MRDriver.class);
		job.setMapperClass(PartitonerDemo.class);
		//job.setReducerClass(Q1Reducer.class);
		job.setNumReduceTasks(10);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		return job.waitForCompletion(true)?0:1;
		
		
		
	}
	
	public static void main(String[] args) throws Exception {

		for (int j = 0; j < args.length; j++) {
			System.out.println("!!!!!args in MAIN are:::"+args[j]);
		}
		
		System.exit(ToolRunner.run(new MRDriver(), args));
		
		
		
	}

	
}
