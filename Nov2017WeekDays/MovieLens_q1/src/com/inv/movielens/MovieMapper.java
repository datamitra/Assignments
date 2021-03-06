package com.inv.movielens;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieMapper extends Mapper<LongWritable, Text, Text,IntWritable>{
	
	IntWritable one=new IntWritable(1);
	Text movie=new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//Extract movie id
		String mvid=value.toString().split("::")[1];
		movie.set(mvid);
		context.write(movie, one);
	
	}
	
}
