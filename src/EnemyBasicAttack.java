import java.util.ArrayList;

public class EnemyBasicAttack implements IEnemyStrategy {
    public EnemyBasicAttack() { }

    @Override
    public void executeTurn(Enemy self, ArrayList<Combatant> combatants) {
        System.out.println();
        for (Combatant c : combatants) {
            if (c instanceof Player && c.isAlive()) {

                for (IStatusEffect e : c.getActiveEffects()) {
                    if (e instanceof EffectSmokeBomb) {
                        System.out.println("\nThe enemy is blurred by the fog of your smoke bomb! He is unable to attack!");
                        return;
                    }
                }

                int effectiveDamage = Math.max(0, self.getAttack() - c.getDefense());

                // After the enemy damages the player, we exit the loop to prevent multiple hits
                c.takeDamage(effectiveDamage);
                System.out.println("You have taken " + effectiveDamage + " from the enemy: " + self.getCombatantName());
                break;
            }
        }
    }
}
