import java.util.ArrayList;
import java.util.Comparator;

public class SpeedTurnOrder implements ITurnOrderStrategy {
    public SpeedTurnOrder() { }

    // We sort the combatants list based on their speed stat
    @Override
    public ArrayList<Combatant> determineOrder(ArrayList<Combatant> combatants) {
        // I made use of a lambda expression and Comparator to sort by Combatant speed
        combatants.sort(Comparator.comparingDouble(Combatant::getSpeed).reversed());
        return combatants;
    }
}
