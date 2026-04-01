public class ActionSpecialSkill implements IAction {

    public ActionSpecialSkill() { }

    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        if (user instanceof Player p) {
            System.out.println(p.getCombatantName() + " uses their Special Skill!");
            p.useSpecialSkill(target, engine);
        }

        return this;
    }
}
