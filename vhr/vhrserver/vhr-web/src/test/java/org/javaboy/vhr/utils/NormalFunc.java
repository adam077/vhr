package org.javaboy.vhr.utils;

import java.util.Comparator;

public class NormalFunc {
    static class Student implements Comparable<Student> {
        int height;
        int weight;

        @Override
        public int compareTo(Student o) {
            return Comparator
                    .<Student>comparingInt(s -> s.height)
                    .thenComparing(s -> s.weight)
                    .reversed()
                    .compare(this, o);
        }
    }

}
