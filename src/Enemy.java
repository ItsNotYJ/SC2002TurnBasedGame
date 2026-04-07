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

    // No override required as method is not utilized
    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) { return null; }

    @Override
    public void applyEffect(Combatant target) { }

    @Override
    public void removeEffect(Combatant target) { }

    @Override
    public int getEffectDuration() {
        return 0;
    }

    @Override
    public String getEffectName() {
        return "";
    }

    @Override
    public boolean isEffectExpired() {
        return false;
    }

    @Override
    public void decreaseDuration() { }
}
