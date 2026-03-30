import java.util.ArrayList;

public class EnemyBasicAttack implements IEnemyStrategy {
    public EnemyBasicAttack() { }

    @Override
    public void executeAction(Enemy self, ArrayList<Combatant> combatants) {
        for (Combatant c : combatants) {
            // Look for player, attack then exit loop
            if (c instanceof Player) {
                c.takeDamage(self.getAttack() - c.getDefense());
                break;
            }
        }
    }
}
