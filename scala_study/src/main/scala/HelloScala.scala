/**
  * Created by banma on 2017/8/11.
  */
object HelloScala {

  def main(args: Array[String]) {
    println(add(5, 7))

    var a : String = "123";

    a = "hahahaha";
    print(a);

  }


  def add(a: Int, b: Int): Int = {
    return a + b
  }

}
