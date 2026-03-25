import java.util.ArrayList;

public interface ITurnOrderStrategy {
    // Array method for sort and return of the arrayList of combatants based on a specified stat
    ArrayList<Combatant> determineOrder(ArrayList<Combatant> combatants);
}
