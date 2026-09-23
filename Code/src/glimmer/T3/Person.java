package glimmer.T3;

public class Person {
    static int count = 0;
    private String name;
    private int age;
    private int sex;//0:女 1:男

    public Person() {

    }

    public Person(String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        count++;
    }

    public void eat() {
        System.out.println(name + "正在吃东西");
    }

    public void sleep() {
        System.out.println(name + "正在睡觉");

    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getSex() {
        return sex;
    }
}
