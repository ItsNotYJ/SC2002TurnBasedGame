public class ItemPotion extends Item {

    public ItemPotion() { super("Potion"); }

    @Override
    public void useItem(Combatant user, Combatant target, BattleEngine engine){
        // call the heal method from combatant class
        user.healHP(100);
        System.out.println(user.getCombatantName() + " used a Potion and recovered 100 HP!");
    }
}
