import org.apache.spark.SparkConf
import org.apache.spark.launcher.SparkAppHandle
import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j._
import org.apache.spark.sql.SparkSession


object MovieLens {
  def main(args: Array[String]): Unit = {
    
    Logger.getLogger("org").setLevel(Level.ERROR)
    
   
   // How to create a dataset.
   val spark=SparkSession
     .builder()
     .appName("MovileLens")
     .enableHiveSupport()
     .getOrCreate()
   
     val extractYear=(line:String)=>{
     val mvname=line.split("::")(1)
     val year=mvname.substring(mvname.lastIndexOf('(')+1, mvname.lastIndexOf(')'))
     (year,1)
   }
     
     case class year1(year:String,cnt:Int)
     
   import spark.implicits._
   val yearDF=spark
   .sparkContext
   .textFile(args(0))
   .map(extractYear).toDF("year","count")
   
      
   
 yearDF.createTempView("t_year")
 spark.sql("select year,count(year) cnt from t_year group by year order by cnt desc")
.write.csv(args(1))
 
  }
}