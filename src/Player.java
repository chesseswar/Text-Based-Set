public class Player implements Comparable<Player> {
    String name;
    int score;
    public Player(String n) {
        name = n;
    }

    @Override
    public int compareTo(Player player) {
        return player.score - score;
    }
}
