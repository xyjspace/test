/**
  * Created by banma on 2017/8/18.
  */
object ListStudy {
  def main(args: Array[String]): Unit = {
    //List是不可变的
    var a = List(1, 2, 3)
    var b = List(1, 2, 4)
    var c = a ::: b
    println(a)
    println(b)
    println(c)

    // Nil 是空列表的简称
    val d = (1) :: (2) :: (3) :: (4) :: Nil
    println(d)

    var e = d.drop(2)
    println(d)
    println(e)

    //判断list中是否所有元素都小于3
    var f = e.forall(a => a < 3)
    println(f)

    //过滤操作
    var y = e.filter(a => a < 4)
    println(y)

    //list转成String 输出
    var z = e.mkString(",")
    println(z)

    //从list中第二个元素开始，组成一个新的List返回
    e.tail

  }
}
