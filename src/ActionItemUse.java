public class ActionItemUse implements IAction {
    private String actionName = "use Item";
    private Item usingItem;

    public ActionItemUse(Item item) {
        this.usingItem = item;
        // update the action name to match the specific item
        if (item != null) {
            this.actionName = "Use " + item.getItemName();
        }
    }

    public String getActionName() {
        return actionName;
    }

    @Override
    public void execute(Combatant user, Combatant target, BattleEngine engine) {// takes the engines execute command and passes it to item
        if (usingItem != null) {
            usingItem.useItem(user, target, engine);
        } else {
            System.out.println("No item was selected!");
        }
    }
}
