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

    public Item getUsingItem() { return usingItem; }

    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {// takes the engines execute command and passes it to item
        if (usingItem != null) {
            usingItem.useItem(user, target, engine);

            // We remove the item from the player's inventory after use
            if (user instanceof Player p)
                p.removeItem(usingItem);

        } else {
            System.out.println("No item available!");
        }
        
        return this;
    }
}
