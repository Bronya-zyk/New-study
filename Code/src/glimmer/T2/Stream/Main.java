package glimmer.T2.Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", 85),
                new Student("Bob", 58),
                new Student("Charlie", 90),
                new Student("David", 45),
                new Student("Eve", 72),
                new Student("Frank", 60),
                new Student("Grace", 55),
                new Student("Heidi", 95)
        );
        List<Student> passingStudents = students.stream().
                filter(student -> student.getScore() >= 60) // 过滤：分数≥60
                .sorted(Comparator.comparing(Student::getName))
                .map(stu -> new Student(stu.getName().toUpperCase(), stu.getScore()))
                .collect(Collectors.toList()); // 收集成List

        System.out.println(passingStudents);
    }
}
