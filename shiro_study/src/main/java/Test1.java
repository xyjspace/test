import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by banma on 2017/6/20.
 */
public class Test1 {
    public static void main(String[] args) {
       StringBuilder sb = new StringBuilder();
       sb.append("java");
//        String a = "java";
        String a = sb.toString();
        String b = "java";
        System.out.println(a.intern() == a);
    }
}

@Data
class A{
    String a;
    String b;
    String c;
    String d;

}
