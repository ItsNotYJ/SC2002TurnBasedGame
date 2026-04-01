import java.util.ArrayList;

public class EnemyBasicAttack implements IEnemyStrategy {
    public EnemyBasicAttack() { }

    @Override
    public IAction executeTurn(Enemy self, ArrayList<Combatant> combatants) {
        return null;
    }

    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        return null;
    }
}
