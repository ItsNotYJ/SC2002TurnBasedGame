import java.util.ArrayList;

public interface IEnemyStrategy {
    void executeAction(Enemy self, ArrayList<Combatant> combatants);
}
