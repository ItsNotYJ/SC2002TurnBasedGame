import java.util.ArrayList;

public interface IEnemyStrategy {
    void executeTurn(Enemy self, ArrayList<Combatant> combatants);
}
