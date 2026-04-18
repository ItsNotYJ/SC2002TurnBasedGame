
import java.util.ArrayList;

public class WizardRole extends PlayerRole {

    public WizardRole() {
        super("Wizard", 20, 50, 200, 10);
    }

    @Override
    public void doSpecialSkill(Player player, Combatant enemy, BattleEngine engine) {
        System.out.println("\n" + player.getCombatantName() + " casts Arcane Blast to all enemies!");

        // We fix the attack at the start of the arcane blast so that it doesn't increase mid attack
        int castAttack = player.getAttack();
        ArrayList<Enemy> defeatedEnemies = new ArrayList<>();

        for (Combatant c : engine.getActiveCombatants()) {
            if (c instanceof Enemy e && e.isAlive()) {
                int effectiveDamage = Math.max(0, castAttack - e.getDefense());
                e.takeDamage(effectiveDamage);
                System.out.println("\nArcane Blast hits " + e.getCombatantName() + " for " + effectiveDamage + " damage!");

                if (!e.isAlive()) {
                    defeatedEnemies.add(e);
                }
            }
        }

        // Only after the skill is done, we show the increase and defeated enemies
        for (Enemy defeatedEnemy : defeatedEnemies) {
            player.setStatusEffect(new EffectArcaneBlast());
            System.out.println("\n" + defeatedEnemy.getCombatantName() + " was defeated! Wizard's attack increases by 10!");
        }
    }
}
