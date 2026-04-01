public class ItemSmokeBomb extends Item {

    public ItemSmokeBomb() { super("Smoke Bomb"); }

    @Override
    public void useItem(Combatant user, Combatant target, BattleEngine engine) {
        // create the smoke effect
        EffectSmokeBomb smoke = new EffectSmokeBomb();

        // apply the effect to player who used the item
        user.setStatusEffect(smoke);

        // notify console
        System.out.println(user.getCombatantName() + " threw a Smoke Bomb!");
    }
}
