package glimmer.T2.Stream;

public class Student {
    String name;
    int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    public String toString() {
        return "Student{name='" + name + "', score=" + score + "}"+"\n";
    }
}