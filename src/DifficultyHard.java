import java.util.ArrayList;

public class DifficultyHard extends Difficulty {
    public DifficultyHard() {
        this.difficultyName = "Hard";

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new EnemyGoblin());
        enemies.add(new EnemyGoblin());
        this.enemiesInDifficulty = enemies;

        ArrayList<Enemy> backupEnemies = new ArrayList<>();
        backupEnemies.add(new EnemyWolf());
        backupEnemies.add(new EnemyWolf());
        backupEnemies.add(new EnemyGoblin());
        this.backupEnemies = backupEnemies;
    }
}
