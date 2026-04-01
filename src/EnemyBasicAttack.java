import java.util.ArrayList;

public class EnemyBasicAttack implements IEnemyStrategy {
    public EnemyBasicAttack() { }

    @Override
    public IAction executeAction(Enemy self, ArrayList<Combatant> combatants) {
        for (Combatant c : combatants) {
            if (c instanceof Player && c.isAlive()) {
                return new ActionBasicAttack(self, c);
            }
        }
        return null; // fallback 
    }
}
