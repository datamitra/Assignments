package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q1Reducer extends Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		//movie id -> rating@mvname
		int sum=0;
		int count=0;
		String mvname="";
		
		for (Text val : values) {
			String[] val1=val.toString().split("@");
			 sum=sum+Integer.parseInt(val1[0]);
			count=count+1;
			mvname=val1[1];
		}
		if(count>=40){
			float avg_rating=(float)(sum*1.0)/count;
			context.write(key, new Text(avg_rating+"@"+mvname));
		}
		
		
		
		
	}

}
