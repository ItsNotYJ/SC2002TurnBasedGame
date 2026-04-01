import java.util.ArrayList;

public interface IEnemyStrategy extends IAction {
    IAction executeTurn(Enemy self, ArrayList<Combatant> combatants);
}
