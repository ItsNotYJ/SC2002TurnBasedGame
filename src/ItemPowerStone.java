public class ItemPowerStone extends Item {

    public ItemPowerStone() {
        super("Power Stone");
    }

    @Override
    public void useItem(Combatant user, Combatant target, BattleEngine engine) {
        System.out.println(user.getCombatantName() + " crushes the Power Stone! It glows with intense energy...");

        // checking to make sure the user is a Player
        if (user instanceof Player player) {

            // force the player to use their special skill
            player.useSpecialSkill(target, engine);

            // immediately set the cooldown back to 0
            player.resetSkillCooldown();

            System.out.println("The Power Stone shatters, leaving no cooldown!");
        } else {
            // in case an enemy somehow gets their hands on this item
            System.out.println("The Power Stone has no effect on " + user.getCombatantName() + ".");
        }
    }
}

