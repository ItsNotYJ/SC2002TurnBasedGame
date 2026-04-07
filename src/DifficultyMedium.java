import java.util.ArrayList;

public class DifficultyMedium extends Difficulty {

    public DifficultyMedium() {
        this.setDifficultyName("Medium");

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new EnemyGoblin("Goblin A"));
        enemies.add(new EnemyWolf("Wolf A"));
        this.setEnemiesInDifficulty(enemies);

        ArrayList<Enemy> backupEnemies = new ArrayList<>();
        backupEnemies.add(new EnemyWolf("Wolf A"));
        backupEnemies.add(new EnemyWolf("Wolf B"));
        this.setBackupEnemies(backupEnemies);
        System.out.print("");
    }
}
