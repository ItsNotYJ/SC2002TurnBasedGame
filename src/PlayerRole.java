public abstract class PlayerRole extends Player {
    private String classRole;

    public String getClassRole() {
        return this.classRole;
    }

    public abstract void doSpecialSkill(Player player, Combatant enemy, BattleEngine engine);
}
