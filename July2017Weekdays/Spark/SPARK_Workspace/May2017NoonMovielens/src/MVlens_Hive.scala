import org.apache.spark.SparkConf
import org.apache.spark.launcher.SparkAppHandle
import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j._
import org.apache.spark.sql.SparkSession


object MovieLens_hive {
  def main(args: Array[String]): Unit = {
    
    Logger.getLogger("org").setLevel(Level.ERROR)
    
   
   // How to create a dataset.
   val spark=SparkSession
     .builder()
     .master("local[*]")
     .appName("MovileLens")
     .enableHiveSupport()
     .getOrCreate()
   
    spark.sql("show tables").show()
 
  }
}