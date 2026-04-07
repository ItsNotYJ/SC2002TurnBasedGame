
public class WizardRole extends PlayerRole {

    public WizardRole() {
        super("Wizard", 20, 50, 200, 10);
    }

    @Override
    public void doSpecialSkill(Player player, Combatant enemy, BattleEngine engine) {
        System.out.println("\n" + player.getCombatantName() + " casts Arcane Blast to all enemies!");
        
        for (Combatant c : engine.getActiveCombatants()) {
            if (c instanceof Enemy e && e.isAlive()) {
                int effectiveDamage = Math.max(0, player.getAttack() - e.getDefense());
                e.takeDamage(effectiveDamage);
                System.out.println("\nArcane Blast hits " + e.getCombatantName() + " for " + effectiveDamage + " damage!");

                // If the wizard kills an enemy using his special skill, we add 10 attack to his stats permanantly
                if (!e.isAlive()) {
                    player.setAttack(player.getAttack() + 10);
                    System.out.println("\n" + enemy.getCombatantName() + " was defeated! Wizard's attack increases by 10!");
                }
            }
        }
    }
}
