import java.util.ArrayList;
import java.util.Comparator;

public class SpeedTurnOrder implements ITurnOrderStrategy {
    public SpeedTurnOrder() { }

    // We sort the combatants list based on their speed stat
    @Override
    public ArrayList<Combatant> determineOrder(ArrayList<Combatant> combatants) {
        // Create a copy to avoid modifying the original list reference directly during sorting
        ArrayList<Combatant> sortedList = new ArrayList<>(combatants);

        // Sort descending based on the speed stat
        sortedList.sort(new Comparator<Combatant>() {
            @Override
            public int compare(Combatant c1, Combatant c2) {
                return Integer.compare(c2.getSpeed(), c1.getSpeed());
            }
        });

        return sortedList;
    }
}
