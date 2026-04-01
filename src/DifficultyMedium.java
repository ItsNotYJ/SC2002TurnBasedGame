import java.util.ArrayList;

public class DifficultyMedium extends Difficulty {

    public DifficultyMedium() {
        this.difficultyName = "Medium";

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new EnemyGoblin());
        enemies.add(new EnemyWolf());
        this.enemiesInDifficulty = enemies;

        ArrayList<Enemy> backupEnemies = new ArrayList<>();
        backupEnemies.add(new EnemyWolf());
        backupEnemies.add(new EnemyWolf());
        this.backupEnemies = backupEnemies;
        System.out.print("");
    }
}
