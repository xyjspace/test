package design.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * Created by hasee on 2017/5/6.
 */
public class ObserverPattern {

    public static void main(String[] args) {
        User user1 = new User("张三");
        User user2 = new User("李四");
        User user3 = new User("王五");
        Ticket ticket = new Ticket();
        ticket.attach(user1);
        ticket.attach(user2);
        ticket.attach(user3);
        ticket.notify("第一波抢票开始了");
        ticket.detach(user2);
        ticket.notify("第二波抢票开始了");
    }

}

/**
 * 抽象被观察者
 */
interface Subject{
    //注册通知
    void attach(Observer observer);
    //取消注册
    void detach(Observer observer);
    //通知
    void notify(String message);
}

/**
 * 抽象观察者
 */
interface Observer{
    void update();
}

class  Ticket implements Subject{
    List<Observer> list = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        list.add(observer);
        System.out.println("=========="+((User)observer).getName()+"订阅了通知===========");
    }

    @Override
    public void detach(Observer observer) {
        list.remove(observer);
        System.out.println("=========="+((User)observer).getName()+"取消了通知===========");
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
        for (Observer observer: list) {
            observer.update();
        }
    }
}

/**
 * 具体观察者
 */
class User implements Observer{
    private String name;
    public User(String name){
        this.name = name;
    }
    @Override
    public void update() {
        System.out.println(name + "开始抢票了");
    }
    public String getName() {
        return name;
    }
}
