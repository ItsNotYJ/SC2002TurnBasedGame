import java.util.ArrayList;

public class Enemy extends Combatant {
    private IEnemyStrategy strategy;

    public Enemy(String name, int attack, int defense, int speed, int maxHP) {
        super(name, speed, defense, attack, maxHP);

        // As of this iteration of the game, the enemy only has a basic attack action
        this.strategy = new EnemyBasicAttack();
    }

    public void performTurn(ArrayList<Combatant> combatants) {
        strategy.executeTurn(this, combatants);
    }

    public static void removeDeadCombatants(ArrayList<Combatant> combatants) {
        Player player = null;

        for (Combatant c : combatants) {
            if (c instanceof Player) {
                player = (Player) c;
                break;
            }
        }

        for (int i = combatants.size() - 1; i >= 0; i--) {
            Combatant c = combatants.get(i);

            if (!(c instanceof Player) && !c.isAlive()) {
                // 25% loot chance
                if (player != null && Math.random() < 0.25) {
                    int healAmount = player.getCurrentHP() * 20 / 100;
                    int beforeHP = player.getCurrentHP();

                    //20% of current hp given back
                    player.healHP(healAmount);

                    int recovered = player.getCurrentHP() - beforeHP;
                    if (recovered > 0) {
                        System.out.println(c.getCombatantName() + " dropped loot! " +
                                player.getCombatantName() + " recovered " + recovered + " HP.");
                    } else {
                        System.out.println(c.getCombatantName() + " dropped loot, but " +
                                player.getCombatantName() + " is already at full HP.");
                    }
                }

                combatants.remove(i);
            }
        }
    }
}
