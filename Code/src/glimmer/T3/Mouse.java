package glimmer.T3;

public class Mouse extends Animal {
    public Mouse(String name,int id) {
        super(name,id);
    }
    public void eat() {
        System.out.println(super.getName() + "正在吃奶酪");
    }
}
