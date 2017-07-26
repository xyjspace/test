import lombok.Data;

/**
 * Created by banma on 2017/7/24.
 */
public class Test {
    public static void main(String[] args) {

        A  a = new A();
        a.setName("haha");
        changeA(a);
        System.out.println(a);

    }

    public static void changeA(A a){
        A b = new A();
        b=a;
        b.setName("xixi");
        System.out.println(a);
    }
}

@Data
class A{
    String name;
}
