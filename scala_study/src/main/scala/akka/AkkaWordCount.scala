//package akka
//
//import akka.actor.ActorSystem
//import akka.actor.Actor
//import akka.util.Duration
//
///**
//  * Created by banma on 2017/8/29.
//  */
//object AkkaWordCount {
//  def main(args: Array[String]): Unit = {
//
//    val system = ActorSystem("wordCountwithakka");
//
//
//  }
//}
//
//
///**
//  * 输出结果并且关闭系统
//  */
//class Listener extends Actor {
//  override def receive = {
//    case FinalRes(du, res) => {
//      println("caculation time : %s".format(du))
//      println("Res : %s".format(res))
//      context.system.shutdown()
//    }
//  }
//}
//
//class Master extends Actor{
//  override  def receive = {
////    case
//  }
//}
//
//
////？
//sealed trait wcMessage
//
//case class FinalRes(du: Duration, res: String = null) extends wcMessage
//
//case object Map extends wcMessage
//
//case class Task(splitedData: Array[String]) extends wcMessage
//
//case class Reduce(partData: scala.collection.mutable.HashMap[String, Int]) extends wcMessage