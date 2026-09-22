package glimmer.T3;

public class Penguin extends Animal {
    public Penguin(String name,int id) {
        super(name,id);
    }

    public void eat() {
        System.out.println(super.getName() + "正在吃鱼");
    }
}
