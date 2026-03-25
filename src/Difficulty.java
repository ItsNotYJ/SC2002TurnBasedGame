import java.util.ArrayList;

public abstract class Difficulty {
    public String difficultyName;
    public ArrayList<Enemy> enemiesInDifficulty;
    public ArrayList<Enemy> backupEnemies;

    public String getDifficultyName() {
        return difficultyName;
    }

    public ArrayList<Enemy> getInitialSpawn() {
        return enemiesInDifficulty;
    }

    public ArrayList<Enemy> getBackupSpawn() {
        return backupEnemies;
    }

    public boolean hasBackupSpawn() {
        return backupEnemies.isEmpty();
    }
}
