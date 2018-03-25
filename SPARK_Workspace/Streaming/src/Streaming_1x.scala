import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.sql.SparkSession


object Streaming_1x {
  def main(args: Array[String]): Unit = {
    
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val conf = new SparkConf().setMaster("local[*]")
    .setAppName("ReadNetwork")


        val ssc = new StreamingContext(conf, Seconds(5))
    

      
    //lines is DStream
    val lines = ssc.socketTextStream("localhost", 9999)
    
    val word_count= lines.flatMap(x => x.split(" "))
    .map(word => (word, 1))
    .reduceByKey((x,y) => x+y)
    
    word_count.print()
       
    ssc.start()
    ssc.awaitTermination()
    
 }
}
