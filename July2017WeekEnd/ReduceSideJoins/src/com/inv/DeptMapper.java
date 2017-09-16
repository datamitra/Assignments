package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DeptMapper extends Mapper<LongWritable, Text,Text, Text>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
			
		//Extract join key from dept rec:: 001,hadoop
		String deptId=value.toString().split(",")[0];
		String deptName=value.toString().split(",")[1];
			
			//Send join key and the values you want from dept data set
			context.write(new Text(deptId.trim()+"_dept"), new Text(deptName));
	
	}
	
	
}