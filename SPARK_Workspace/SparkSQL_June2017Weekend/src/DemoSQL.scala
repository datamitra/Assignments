import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession


object DemoSQL {
  def main(args: Array[String]): Unit = {
    
    Logger.getLogger("org").setLevel(Level.ERROR)

   val spark = SparkSession.builder().master("local[*]").appName("Sample SQL")
   .getOrCreate() 
   val userRDD=spark.sparkContext.textFile(args(0))
   
   // age group 35, how many M/F user are present
   //case class user(
   
   def loadRatings(x:String)={
     val y=x.split("::")
      (y(0),y(1),y(2),y(3),y(4))      
    }
    import spark.implicits._
   userRDD.map(loadRatings).toDF("userid","gender","age","occ","zip").
   createOrReplaceTempView("user")
   
   import scala.io.Source
   val s1=Source.fromFile(args(1)).mkString;
    println(s1);
   
   
   spark.sql(s1). 
   
    
  }
}