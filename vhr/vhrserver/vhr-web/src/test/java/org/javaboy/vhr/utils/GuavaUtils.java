package org.javaboy.vhr.utils;


import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public class GuavaUtils {
    /*
    工具集
    可以用缓存，但是对于扩展的服务，本地缓存适合吗
     */

    public static void main(String[] ags) {
        do2();
    }

    public static void Optional() {
        Optional<Integer> a = Optional.of(5);
        List<Optional<Integer>> ss = Lists.newArrayList(
                a
        );
        for (Optional<Integer> tmp : ss) {
            System.out.println(tmp.isPresent());
        }
    }

    void doCheckArgument(Integer compare) {
        System.out.println(Objects.equal(compare, 1));
        checkArgument(compare >= 0, "Argument was %d but expected nonnegative", compare);
    }

    static void do2() {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a", 1);
        multimap.put("a", 2);
        multimap.put("a", 4);
        multimap.put("a", 4);
        multimap.put("b", 3);
        multimap.put("c", 5);

        System.out.println(multimap.keys());//[a x 4, b, c]
        System.out.println(multimap.get("a"));//[1, 2, 4, 4]
        System.out.println(multimap.get("b"));//[3]
        System.out.println(multimap.get("c"));//[5]
        System.out.println(multimap.get("d"));//[]

    }

    void do3() {
        System.out.println(Ints.asList(1, 2, 3, 4));
        System.out.println(Ints.compare(1, 2));
        System.out.println(Ints.join(" ", 1, 2, 3, 4));
        System.out.println(Ints.max(1, 3, 5, 4, 6));
        System.out.println(Ints.tryParse("1234"));
    }

    ImmutableSet<String> do4() {
        Set<String> set1 = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> set2 = ImmutableSet.of("two", "three", "five", "seven");
        Sets.SetView<String> intersection = Sets.intersection(set1, set2); // contains "two", "three", "seven"
        // I can use intersection as a Set directly, but copying it can be more efficient if I use it a lot.
        return intersection.immutableCopy();
    }

    void do5() {
        String param = "未读代码";
        String name = Preconditions.checkNotNull(param);
        System.out.println(name); // 未读代码
        String param2 = null;
        String name2 = Preconditions.checkNotNull(param2); // NullPointerException
        System.out.println(name2);
    }
}
