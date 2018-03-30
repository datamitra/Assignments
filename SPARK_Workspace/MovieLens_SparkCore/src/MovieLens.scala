import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object MovieLens {
	def main(args: Array[String]): Unit = {
			Logger.getLogger("org").setLevel(Level.ERROR)

			val conf=new SparkConf() //.set("spark.master", "Local[*]")
			//.set("spark.app.name", "Spark DEMO")
			//.setMaster("local[*]")
			val sc=new SparkContext(conf)

			
			
			val spark=SparkSession
			.builder()
			.master("local[*]")
			.appName("Spark SQL demo")
			.enableHiveSupport()
			.getOrCreate()
			
			
			
			val extractYear=(line:String)=>{ 
				val sindex=line.lastIndexOf("(")+1
						val eindex=line.lastIndexOf(")")
						// return a tuple with year,1
						(line.substring(sindex,eindex),1)
			}

			//extractYear("1::Toy Story (1995)::Animation|Children's|Comedy")
			val years=sc.textFile(args(0)).map(extractYear)
					val result=years.reduceByKey((x,y)=>x+y)
					result.sortBy(x=>x._2,false,1).saveAsTextFile(args(1))

				



	}
}