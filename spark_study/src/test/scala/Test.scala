/**
  * Created by banma on 2017/9/3.
  */
object Test {
  def main(args: Array[String]): Unit = {
    var a = new As(1,2)
    var b = new As(3,4)
    var c = a.get(b)

    println(c)



  }
}


class As(val a:Int ,val b:Int){

  def get(b: As) = {
    println(b.b)

    b.b
    }

}
