import entity.*;
import entity.User;

/**
 * 避免请求的发送者和接收者之间的耦合关系
 * 职责连模式侧重类的行为，每个类都有自己的请求范围，
 * 在客户端执行请求的时候按照链中制定的顺序进行验证吗，直到找到适合自己的请求。
 *
 * 使用场景：
 *  1、有过个对象处理请求，到底怎处理在运行时决定
 *  2、希望在不明确指定接收者的情况下，向多个对象中的一个提交请求
 *  3、可处理一个请求的对象集合应该被动态的指定
 * Created by hasee on 2017/5/18.
 */
public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        User user = new User();
        user.setAccount("xyj");
        user.setIdentity("1");
        user.setPassword("123");
        user.setBalance("12");
        Handler handler = setProcess();
        handler.handlerRequest(user);
    }

    //设置登录流程 登录流程为 检验密码 --> 检验余额 --> 验证身份
    public static Handler setProcess(){
        Handler handler1 = new VerifyPasswordHandler();
        Handler handler2 = new VerifyBalanceHandler();
        Handler handler3 = new VerifyIdentityHandler();
        handler1.setSuccessor(handler2);
        handler2.setSuccessor(handler3);
        return handler1;
    }

}

/**
 * 这个地方可以使用接口，但是抽象类在这里比较方便一些。。
 */
abstract class Handler{
    private Handler successor;

    abstract boolean handlerRequest(User user);

    void setSuccessor(Handler successor){
        this.successor = successor;
    }

    Handler getSuccessor(){
        return this.successor;
    }
}

/**
 * 验证用户是否登录的handler
 */
class VerifyPasswordHandler extends Handler{
    @Override
    public boolean handlerRequest(User user) {
        if("xyj".equals(user.getAccount()) && "123".equals(user.getPassword())){
            System.out.println("用户登录成功");
            if(getSuccessor() != null)
                   getSuccessor().handlerRequest(user);
        }else {
            System.out.println("用户名或者密码错误");
            return false;
        }
        return true;
    }
}

/**
 * 检验余额的handler
 */
class VerifyBalanceHandler extends Handler{
    @Override
    public boolean handlerRequest(User user) {
        if(Integer.valueOf(user.getBalance()) > 0){
            System.out.println("用户余额为: "+ user.getBalance());
            if(getSuccessor() != null)
                getSuccessor().handlerRequest(user);
        }else {
            System.out.println("用户余额不足请充值");
            return false;
        }
        return true;
    }
}

/**
 * 检验用户身份的handler
 */
class VerifyIdentityHandler extends Handler{
    @Override
    public boolean handlerRequest(User user) {
        if("0".equals(user.getIdentity())){
            System.out.println("尊贵的VIP用户，欢迎回来");
            if(getSuccessor() != null)
                getSuccessor().handlerRequest(user);
        }else {
            System.out.println("欢迎回来，充值VIP会有更多的优惠活动哦");
            if (getSuccessor() != null)
                getSuccessor().handlerRequest(user);
        }
        return true;
    }
}