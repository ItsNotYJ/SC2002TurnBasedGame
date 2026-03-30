public class ActionItemUse implements IAction {
    private Item usingItem;

    public ActionItemUse(Item item) {
        this.usingItem = item;
    }

    @Override
    public void executeTurn(Combatant user, Combatant target, BattleEngine engine) {

    }
}
