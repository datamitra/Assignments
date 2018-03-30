import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object MovieLens_SparkSQL {
	def main(args: Array[String]): Unit = {
			Logger.getLogger("org").setLevel(Level.ERROR)
		
			
			val spark=SparkSession
			.builder()
			.master("local[*]")
			.appName("Spark SQL demo")
			.enableHiveSupport()
			.getOrCreate()
			
			val mvDF=spark.read.text(args(0))
			mvDF.printSchema()
			mvDF.show()
			import spark.implicits._
			//mvDF.rdd.map(line=> (line.split("::")(0),line.split("::")(1)))
			//.toDF("mvid","mvname")
			
val mvDF1=mvDF.map(row => (row.getString(0).split("::")(0),
    row.getString(0).split("::")(1))).toDF("mvid","mvname")
    mvDF1.printSchema()
			    
			//   mvDF1.createOrReplaceTempView("movies");
			mvDF1.write.saveAsTable("movies_table")
			spark.sql("select * from movies_table").show()
			


	}
}