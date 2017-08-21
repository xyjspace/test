import scala.collection.immutable.Set
/**
  * Created by banma on 2017/8/18.
  */
object TupleAndSetStudy {
  def main(args: Array[String]): Unit = {

    //如果一个函数有多个返回值，那么可以使用元组。

    var pair = (1,"String")
    println(pair._1)
    println(pair._2)


    //默认拿到的是不可变的set
    var a = Set("123","456")
    a += "789"
    println(a.contains("789"))
    println(a + "10")


    var c = Map[String, Int](
      "a" -> 1, "b" -> 2
    )
    println(c("b"))

  }
}
