package com.inv;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GrepMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
	
	String searchword="";
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		Configuration conf=context.getConfiguration();
		searchword=conf.get("sword");
		System.out.println("++++Search word::"+conf.get("sword"));
		System.out.println("++++COLOR"+conf.get("COLOR"));
		System.out.println("++++COLOR1"+conf.get("COLOR1"));
		System.out.println("++++COLOR2"+conf.get("COLOR2","I am Def COLOR"));
		System.out.println("++++dfs.blocksize"+conf.get("dfs.blocksize"));
		
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		
		/*conf.set("COLOR","RED");		
		conf.set("mapreduce.job.reduces", "0");
		conf.set("mapreduce.job.name", "GREP CODE");
		*/
		
		
		if(value.toString().toLowerCase().contains(searchword.toLowerCase())){
			//case insensitive
			//Hadoop HADOOP hadoop 
			context.write(value, NullWritable.get());
		}
	}
}
