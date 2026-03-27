import java.util.ArrayList;
public class WizardRole extends PlayerRole {

    public WizardRole() {

        super("Wizard", 20, 50, 200, 10);
    }

    @Override
    public void doSpecialSkill(Player player, Combatant target, BattleEngine engine) {
        System.out.println("Wizard casts Arcane Blast!");

        // retrieve all active enemies from BattleEngine
        //ArrayList<Enemy> enemies = engine.getActiveEnemies(); //needs to be added to BattleEngine

        // loop through all enemies
        //for (Enemy enemy : enemies) {
        //    if (enemy.isAlive()) {
                // calculate & deal damage to each enemy
        //        int damage = Math.max(0, player.getAttack() - enemy.getDefense());
        //        enemy.takeDamage(damage);
        //        System.out.println("Arcane Blast hits " + enemy.getCombatantName() + " for " + damage + " damage!");

                // if blasts defeat enemy, increase wizard attack by 10
        //        if (!enemy.isAlive()) {
        //            player.setAttack(player.getAttack() + 10);
        //            System.out.println(enemy.getCombatantName() + " was defeated! Wizard's attack increases by 10!");
        //        }
        //    }
    //    }
    }
}
