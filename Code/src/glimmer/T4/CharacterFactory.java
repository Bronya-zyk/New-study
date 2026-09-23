package glimmer.T4;
enum CharacterType {
    SABER, ARCHER, CASTER
}//学长玩fgo？
public class CharacterFactory {
    public static Character createCharacter(CharacterType type) {
        switch (type) {
            case SABER:
                return new Saber();
            case ARCHER:
                return new Archer();
            case CASTER:
                return new Caster();
            default:
                throw new IllegalArgumentException("Invalid character type: " + type);
                //这行是AI补全的，意思是入参不合法
            }

    }
     public static void main(String[] args) {
        Character saber = CharacterFactory.createCharacter(CharacterType.SABER);
        saber.attack();

        Character archer = CharacterFactory.createCharacter(CharacterType.ARCHER);
        archer.attack();

        Character caster = CharacterFactory.createCharacter(CharacterType.CASTER);
        caster.attack();
    }
}
