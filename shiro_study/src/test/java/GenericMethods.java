/**
 * Created by banma on 2017/7/11.
 */
public class GenericMethods {
    public <T, V, Z> void f(T x, String y, Z z) {
        System.out.println(x.getClass().getName() + "," + y.getClass().getName() + "," + z.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f(1,"1", 2L);
    }
}
