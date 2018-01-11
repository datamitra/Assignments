import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.conf.Configurable
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.spark.SparkConf


object WordCount {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
     //Spark Core::
    val sparkConf=new SparkConf()
    val sc = new SparkContext(sparkConf)
    val inputRDD=sc.textFile(args(0))
    
    val conf=new Configuration()
    val fs=FileSystem.get(conf)
    if(fs.exists(new Path(args(1)))){
      fs.delete(new Path(args(1)),true)
      println("Path is removed")
    }
    
    inputRDD.
    flatMap(line=>line.split(" ")).
    map(word=>(word,1)).
    reduceByKey((x,y)=>x+y).saveAsTextFile(args(1))
    
    println("File Saved")
  }
 
  
  
}