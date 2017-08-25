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

public class EmpMapper extends Mapper<LongWritable, Text, Text,Text>{
	
	private HashMap<String, String> deptMap=new HashMap<String,String>();
	
	enum RECORDS{
		GOOD_REC,
		BAD_REC
	}
	
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		
		URI[] files=context.getCacheFiles();
		System.out.println("++++++ In Mapper ++++++++++++++++++");

		for (int i = 0; i < files.length; i++) {
			System.out.println("++++++ In Mapper:::Files added are::::"+files[i].getPath());
			
		}
		File file=new File("dept");
		BufferedReader reader=new BufferedReader(new FileReader(file));
		try{
			 
			String str="";
			while((str=reader.readLine())!=null){
				String deptid=str.split(",")[0];
				String deptname=str.split(",")[1];
				
				deptMap.put(deptid, deptname);
				
				context.getCounter("DeptREC", "deptlines").increment(1);
				
				
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
			//1,name,2000,001
		context.getCounter(RECORDS.GOOD_REC).increment(1);
		
		String deptid=value.toString().split(",")[3];
		String deptname="";
		try{
			 deptname=deptMap.get(deptid);
		}finally{
			deptname=(deptname==null || deptname.equals(""))?"NotFound": deptname;
		}
		
		
		context.write(value, new Text(deptname));
	//001_emp ,   1,name,2000,001
	
	}

}
