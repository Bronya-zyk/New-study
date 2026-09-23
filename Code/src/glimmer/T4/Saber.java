package glimmer.T4;

public class Saber implements Character{
    private String name;
    private String skill;
    public Saber() {
        this.name = "阿尔托莉雅";
        this.skill = "Excalibur";//咖喱棒
    }
    public void attack() {
        System.out.println("[剑士] " + name + " 使用 " + skill + " 发动攻击！");
    }
}

