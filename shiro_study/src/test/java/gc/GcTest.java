package gc;

/**
 * Created by banma on 2017/10/16.
 */
public class GcTest {
    public static void main(String[] args) {
//        byte[] a, b, c, d;
//        a = new byte[2 * 1024 * 1024];
//        b = new byte[2 * 1024 * 1024];
//        c = new byte[2 * 1024 * 1024];
//        d = new byte[4 * 1024 * 1024];
        A a = new A();
        if(a instanceof C){
            System.out.println("yes");
        }else{
            System.out.println("No");
        }


    }

}

class A{
    protected final static class B implements C{
        @Override
        public void getName(){
            System.out.println("hahahaha");
        }
    }
}



