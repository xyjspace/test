/**
 * Created by banma on 2017/7/13.
 */
class Demo8 {
    public static void main(String[] args) {
        Duoxiancheng duoxiancheng = new Duoxiancheng();
        Thread a = new Thread(duoxiancheng);
        Thread b = new Thread(duoxiancheng);
        a.start();
        b.start();

    }
}

class Duoxiancheng implements Runnable {

    private int a = 100;

    public void run() {
        while (a > 0) {
            System.out.println(Thread.currentThread() + "----" + a);
            a--;
        }
    }

}