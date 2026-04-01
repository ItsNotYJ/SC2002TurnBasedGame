import java.util.ArrayList;

public interface IEnemyStrategy extends IAction {
    void executeTurn(Enemy self, ArrayList<Combatant> combatants);
}
