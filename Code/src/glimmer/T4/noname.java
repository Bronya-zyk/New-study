package glimmer.T4;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.List;

public class noname {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.forEach(integer -> System.out.println(integer));
    }
}
