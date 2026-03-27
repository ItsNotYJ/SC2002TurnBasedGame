public class ActionSpecialSkill implements IAction {

    public ActionSpecialSkill() { }

    @Override
    public void execute(Combatant user, Combatant target, BattleEngine engine) {
        if (user instanceof Player) {
            Player player = (Player) user;
            System.out.println(player.getCombatantName() + " uses their Special Skill!");
            player.getSelectedRole().doSpecialSkill(player, target, engine);
        }
    }
}
