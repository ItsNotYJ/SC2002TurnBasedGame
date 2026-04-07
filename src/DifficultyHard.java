import java.util.ArrayList;

public class DifficultyHard extends Difficulty {
    public DifficultyHard() {
        this.setDifficultyName("Hard");

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new EnemyGoblin("Goblin A"));
        enemies.add(new EnemyGoblin("Goblin B"));
        this.setEnemiesInDifficulty(enemies);

        ArrayList<Enemy> backupEnemies = new ArrayList<>();
        backupEnemies.add(new EnemyWolf("Wolf A"));
        backupEnemies.add(new EnemyWolf("Wolf B"));
        backupEnemies.add(new EnemyGoblin("Goblin A"));
        this.setBackupEnemies(backupEnemies);
    }
}
