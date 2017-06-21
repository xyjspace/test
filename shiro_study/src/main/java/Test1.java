import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by banma on 2017/6/20.
 */
public class Test1 {
    public static void main(String[] args) {
        A a = new A(){};
        a.setA("123");
        A b = new A(){};
        BeanUtils.copyProperties(a,b);
        System.out.printf(b.toString());
    }
}

@Data
class A{
    String a;
    String b;
    String c;
    String d;

}
