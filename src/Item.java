public abstract class Item {
    private String itemName;

    public Item(String name) {
        this.itemName = name;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public abstract void useItem(Combatant user, Combatant target, BattleEngine engine);
}
