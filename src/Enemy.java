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
}
