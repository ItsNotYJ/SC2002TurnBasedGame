public class ActionBasicAttack implements IAction {

    private Combatant user;
    private Combatant target;

    public ActionBasicAttack(Combatant user, Combatant target) {
        this.user = user;
        this.target = target;
    }

    @Override
    public void execute(Combatant user, Combatant target, BattleEngine engine) {
        int damage = this.user.getAttack() - this.target.getDefense();

        if (damage < 0) {
            damage = 0;
        }

        this.target.takeDamage(damage);
    }
}