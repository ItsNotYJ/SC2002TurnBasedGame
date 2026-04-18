public class ItemPotion extends Item {

    public ItemPotion() { super("Potion"); }

    @Override
    public void useItem(Combatant user, Combatant target, BattleEngine engine){
        int hpBeforeHeal = user.getCurrentHP();

        // call the heal method from combatant class
        user.healHP(100);

        int recoveredHP = user.getCurrentHP() - hpBeforeHeal;
        if (recoveredHP > 0) {
            System.out.println("\n" + user.getCombatantName() + " used a Potion and recovered " + recoveredHP + " HP!");
        } else {
            System.out.println("\n" + user.getCombatantName() + " used a Potion but is already at full HP.");
        }
    }
}
