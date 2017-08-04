import java.util.Calendar;
import java.util.Date;

/**
 * Created by banma on 2017/7/28.
 */
public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date2 = calendar.getTime();
        System.out.println(date2.toLocaleString());
    }
}
