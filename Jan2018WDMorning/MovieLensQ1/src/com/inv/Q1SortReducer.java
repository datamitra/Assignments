package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q1SortReducer extends Reducer<IntWritable,Text, Text, IntWritable>{
	int i=0;
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {
		if(i<10){ //for Top10
			for(Text mvid:values){
				context.write(mvid, new IntWritable(key.get()*-1));
				i=i+1;
			}
		}
		
	}

}
