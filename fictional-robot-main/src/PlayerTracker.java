import components.map.Map;
import components.map.Map1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Proof of concept for PlayerTracker component. Tracks basketball player
 * statistics such as points, rebounds, assists, steals, and blocks.
 */

public class PlayerTracker {

    /**
     * A Map where each key is a player name and each value is another Map of
     * stat names to values.
     */

    private Map<String, Map<String, Integer>> playerStats;

    /**
     * Constructor initializes an empty PlayerTracker.
     */
    public PlayerTracker() {
        this.playerStats = new Map1L<>();
    }

    /**
     * Adds a new player to the tracker with all stats set to 0.
     *
     * @param playerName
     *            the name of player being added
     */

    public void addPlayer(String playerName) {
        Map<String, Integer> stats = new Map1L<>();
        stats.add("points", 0);
        stats.add("rebounds", 0);
        stats.add("assists", 0);
        stats.add("steals", 0);
        stats.add("blocks", 0);
        this.playerStats.add(playerName, stats);
    }

    /**
     * Updates a specific stat for a given player.
     *
     * @param playerName
     *            the name of the player
     * @param stat
     *            the stat to update
     * @param value
     *            the new value for the stat
     */

    public void updateStat(String playerName, String stat, int value) {
        Map<String, Integer> stats = this.playerStats.value(playerName);
        stats.replaceValue(stat, value);
    }

    /**
     * Returns the value of a specific stat for a given player.
     *
     * @param playerName
     *            the name of the player
     * @param stat
     *            the stat to retrieve
     * @return the value of the stat
     */
    public int getStat(String playerName, String stat) {
        return this.playerStats.value(playerName).value(stat);
    }

    /**
     * Returns the average of a specific stat across all players.
     *
     * @param stat
     *            the stat to average
     * @return the average value of the stat across all players
     */

    public double avgStat(String stat) {
        double total = 0;
        double count = this.playerStats.size();
        for (Map.Pair<String, Map<String, Integer>> entry : this.playerStats) {
            total += entry.value().value(stat);
        }
        return total / count;
    }

    /**
     * Returns the name of the player with the highest value for a given stat.
     *
     * @param stat
     *            the stat to check
     * @return the name of the player with the highest value
     */

    public String highestPlayer(String stat) {
        String best = "";
        int bestValue = -1;
        for (Map.Pair<String, Map<String, Integer>> entry : this.playerStats) {
            int val = entry.value().value(stat);
            if (val > bestValue) {
                bestValue = val;
                best = entry.key();
            }
        }
        return best;
    }

    /**
     * Main method showing the PlayerTracker in action.
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        PlayerTracker tracker = new PlayerTracker();

        // Add players
        tracker.addPlayer("LeBron James");
        tracker.addPlayer("Stephen Curry");
        tracker.addPlayer("Nikola Jokic");

        // Update stats
        tracker.updateStat("LeBron James", "points", 28);
        tracker.updateStat("LeBron James", "assists", 8);
        tracker.updateStat("LeBron James", "rebounds", 7);

        tracker.updateStat("Stephen Curry", "points", 32);
        tracker.updateStat("Stephen Curry", "assists", 6);
        tracker.updateStat("Stephen Curry", "rebounds", 5);

        tracker.updateStat("Nikola Jokic", "points", 26);
        tracker.updateStat("Nikola Jokic", "assists", 9);
        tracker.updateStat("Nikola Jokic", "rebounds", 14);

        // Print individual stats
        out.println("=== Player Stats ===");
        out.println("LeBron Points: " + tracker.getStat("LeBron James", "points"));
        out.println("Curry Points: " + tracker.getStat("Stephen Curry", "points"));
        out.println("Jokic Rebounds: " + tracker.getStat("Nikola Jokic", "rebounds"));

        // Print averages
        out.println("\n=== League Averages ===");
        out.println("Avg Points: " + tracker.avgStat("points"));
        out.println("Avg Assists: " + tracker.avgStat("assists"));
        out.println("Avg Rebounds: " + tracker.avgStat("rebounds"));

        // Print leaders
        out.println("\n=== Stat Leaders ===");
        out.println("Points Leader: " + tracker.highestPlayer("points"));
        out.println("Assists Leader: " + tracker.highestPlayer("assists"));
        out.println("Rebounds Leader: " + tracker.highestPlayer("rebounds"));

        out.close();
    }
}