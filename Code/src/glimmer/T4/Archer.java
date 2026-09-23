package glimmer.T4;

public class Archer implements Character{
    private String name;
    private String skill;

    public Archer() {
        this.name = "卫宫";
        this.skill = "Unlimited Blade Works";
    }
    public void attack() {
        System.out.println("[弓兵] " + name + " 使用 " + skill + " 发动攻击！");
    }
}
