
public class WizardRole extends PlayerRole {

    public WizardRole() {
        super("Wizard", 20, 50, 200, 10);
    }

    @Override
    public void doSpecialSkill(Player player, Combatant enemy, BattleEngine engine) {
        for (Combatant c : engine.getActiveCombatants()) {
            if (c instanceof Enemy e && e.isAlive()) {
                int effectiveDamage = Math.max(0, player.getAttack() - e.getDefense());
                e.takeDamage(effectiveDamage);
                System.out.println("Arcane Blast hits " + enemy.getCombatantName() + " for " + effectiveDamage + " damage!");


                // If the wizard kills an enemy using his special skill, we add 10 attack to his stats permanantly
                if (!e.isAlive()) {
                    player.setAttack(player.getAttack() + 10);
                    System.out.println(enemy.getCombatantName() + " was defeated! Wizard's attack increases by 10!");
                }
            }
        }
    }
}
