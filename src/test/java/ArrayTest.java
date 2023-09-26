import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/5
 */
public class ArrayTest {

    public static void main(String[] args) {
            List<String> list = new ArrayList<>(10);
        ArrayList<String> stringArrayList = new ArrayList<String>() {
            {
                add("e");
            }
        };

        list.add("1");
            System.out.println(list.get(0));
    }

}

class MyList extends ArrayList<String> {

    /**
     * 子类重写父类的方法，返回值可以不一样
     * 但这里只能用数组类型，换成Object就不行
     * 应该算是java本身的bug
     */
    @Override
    public String[] toArray() {
        // 为了方便举例直接写死
        return new String[]{"1", "2", "3"};
    }

}