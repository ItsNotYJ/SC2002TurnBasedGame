import java.util.ArrayList;

public abstract class Difficulty {
    private String difficultyName;
    private ArrayList<Enemy> enemiesInDifficulty;
    private ArrayList<Enemy> backupEnemies;

    public Difficulty() { }

    public String getDifficultyName() {
        return difficultyName;
    }

    public ArrayList<Enemy> getInitialSpawn() {
        return enemiesInDifficulty;
    }

    public ArrayList<Enemy> getBackupSpawn() {
        return backupEnemies;
    }

    public void setDifficultyName(String name) {
        this.difficultyName = name;
    }

    public void setEnemiesInDifficulty(ArrayList<Enemy> enemies) {
        this.enemiesInDifficulty = enemies;
    }

    public void setBackupEnemies(ArrayList<Enemy> backupEnemies) {
        this.backupEnemies = backupEnemies;
    }

    public boolean hasBackupSpawn() {
        return !backupEnemies.isEmpty();
    }
}
