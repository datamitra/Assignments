package com.inv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmpMapper extends Mapper<LongWritable, Text,Text, Text>{

	private HashMap<String, String> deptMap=new HashMap<String,String>();
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
	URI[] ls =context.getCacheFiles();
	for (int i = 0; i < ls.length; i++) {
		System.out.println("******* Files are "+ls[i].toString());
	}
	
	File fs=new File("dept");
	BufferedReader reader=new BufferedReader(new FileReader(fs));
	String line="";
	try{
		while((line=reader.readLine())!=null){
	
		//001,hadoop 
		deptMap.put(line.split(",")[0], line.split(",")[1]);
		}
	}finally{
		if(reader!=null){
			reader.close();
		}
	}
	
		
	}
	
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
			
		//Extract join key from emp rec  1,name,2000,001
		String deptId=value.toString().split(",")[3];
		String deptName="";
		try{
			//get deptName
			deptName=deptMap.get(deptId);
		}finally{
			deptName=(deptName==null)?"Not-Found":deptName;
		}
		
			//Send join key and the value you want from employee data set
			context.write(value,new Text(deptName));
	
	}
	
	
}
