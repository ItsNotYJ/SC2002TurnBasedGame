import java.util.ArrayList;

public class DifficultyEasy extends Difficulty {
    public DifficultyEasy() {
        this.setDifficultyName("Easy");

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new EnemyGoblin("Goblin A"));
        enemies.add(new EnemyGoblin("Goblin B"));
        enemies.add(new EnemyGoblin("Goblin C"));

        this.setEnemiesInDifficulty(enemies);
        this.setBackupEnemies(new ArrayList<>());
    }
}
