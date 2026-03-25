import java.util.ArrayList;

public interface IEnemyStrategy {
    IAction executeAction(Enemy self, ArrayList<Combatant> combatants);
}
