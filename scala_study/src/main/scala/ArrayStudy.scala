/**
  * Created by banma on 2017/8/18.
  */
object ArrayStudy {

  def main(args: Array[String]): Unit = {

    val a: Array[Int] = new Array[Int](3)
    a(0) = 1
    a(2) = 3
    a(1) = 2

    //下面这两个是等价的
    a(0) = 2
    a.update(0, 2)

    //这两个是等价的
    print(a(0))
    print(a.apply(0))

    for (b <- a) {
      println(b)
    }

    //相当于调用了Array的静态工厂。
    var c = Array("123", "string", 1)

    for (b <- c) {
      println(b)
    }
  }

}
