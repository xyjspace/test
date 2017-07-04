import com.xyj.shiro.domain.entity.User;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by banma on 2017/7/4.
 */
public class Test {
    public static void main(String[] args) {
//        List<Integer> list = Lists.newArrayList(1, null, 2, 3, 4, 5, null, 7);
//        List<Integer> list2 = list.stream().filter(a -> a != null).map(a -> a = a + 2).collect(Collectors.toList());
//        System.out.println(list2);
        List<User> list3 = Lists.newArrayList();
        String[] a = {"张三","李四","王五","赵六","孙七"};
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 5; i++) {
                User user = new User();
                user.setAge(i+j);
                user.setName(a[i]);
                list3.add(user);
            }
        }
        for (User user: list3) {
            System.out.println(user);
        }
        Map<Integer,List<User>> map = list3.stream().collect(Collectors.groupingBy(User::getAge));

        for(Map.Entry<Integer,List<User>> entry : map.entrySet()){
            System.out.println(entry.getKey());
            for(User user : entry.getValue()){
                System.out.println(user);
            }
        }


    }
}
