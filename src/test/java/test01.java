import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/3/27
 */
public class test01 {

    public static void main(String[] args) {
        LocalDateTime parse = LocalDateTime.parse("2023-03-07T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime localDateTime = parse.minusDays(3);
        System.out.println(localDateTime);
        System.out.println(Thread.State.values());
        Map<Integer, Integer> map = new HashMap<>();
//        map.put()
    }

}
