

object Prog2_RegEx {
  
  def main(args: Array[String]): Unit = {
    
    //REGEX:
    // [A-Za-z] [^A-Z] [0-9]
    // for white space \\s ,Not White space \\S
    //White space \\w
    //Digits \d
    //Look for zip code that is 5 digits length:
    // \\s\\d{5}\\s   here first space and 5 characters and space  
     //{n,m} minimum n times and maximum m times
    //{n,} minimum n times to any max number
    //Word That is 2 to 20 characters in length
    //[A-Za-z]{2,20}
    // Two characters startwith A or C in ::  city1 CA AK 12345
    // A[KLRZ]|C[AOT]
    // + means what ever precedes must occur one or more times
    //If you are searching for . ^ * + ? {} [] \ | () you  must put 
    //a backslash in front of them
    
    // star * 0 or more,+ 1 or more, ? 0 or 1 
    
    val samplestring: String=" Average life on earth is 60y"
    
    val pattern= """.* ([\d]+).*""".r    
    // shpuld be in tripel quotes, .* bunch of chars , space, then digits and any chars
    
    //val pattern= """.* ([\d]+).*""".r
        //println(pattern findFirstIn samplestring)
    val pattern(answerstring) = samplestring
    val ans=answerstring.toInt
    //println(pattern findFirstIn samplestring)
    println(ans)
    
  }
  
}