package com.inv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AlphaPartitioner extends Partitioner<Text, IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int noOfReducers) {
		// TODO Auto-generated method stub
		
		char ch=key.toString().toLowerCase().charAt(0);
		//if a then return 0  97
		//if b then return 1 98 
		if(ch>=97 && ch <=122){
			return (ch-96)%noOfReducers;
		}else return 0;
		
		
		
	}

}
