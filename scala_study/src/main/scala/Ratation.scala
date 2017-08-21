/**
  * Created by banma on 2017/8/21.
  */
object testRatation {
  def main(args: Array[String]): Unit = {
    val a = new Ratation(1, 4)
    print(a)
    val c = a.add(new Ratation(5))
    print(c)

  }
}


class Ratation(m: Int, n: Int) {

  //辅助构造器
  def this(m:Int) = this(m,1)

  private val a = m

  private val b = n

  require(n != 0)


  override def toString: String = m.toString + '/' + n.toString

  def add(that: Ratation) = {
    new Ratation(a + that.a, b + that.b)
  }
}
