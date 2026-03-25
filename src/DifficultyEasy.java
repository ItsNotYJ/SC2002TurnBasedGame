import java.util.ArrayList;

public class DifficultyEasy extends Difficulty {
    public DifficultyEasy() {
        this.difficultyName = "Easy";

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int i = 0; i < 3; i++) {
            enemies.add(new EnemyGoblin());
        }
        this.enemiesInDifficulty = enemies;
        this.backupEnemies = new ArrayList<Enemy>();
    }
}
