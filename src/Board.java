import java.util.*;

public class Board {
    ArrayList<Card> onBoard;
    ArrayList<Card> deck;
    public Board() {
        onBoard = new ArrayList<>(12);
        deck = new ArrayList<>();
        for (int count = 1; count <= 3; count++) { //count
            for (int shape = 0; shape < 3; shape++) {
                for (int fill = 0; fill < 3; fill++) {
                    for (int color = 0; color < 3; color++) {
                        deck.add(new Card(count, shape, fill, color));
                    }
                }
            }
        }

    }

    public boolean setExists(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                if (i != j) {
                    for (int k = 0; k < cards.size(); k++) {
                        if (i != k && j != k) {
                            if (cards.get(i).validSet(cards.get(j), cards.get(k))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public Card completeSet(Card a, Card b) {
        Card output = new Card(1, 0, 0, 0);
        output.count = completeSetAttribute(a.count, b.count, 1);
        output.shape = completeSetAttribute(a.shape, b.shape, 0);
        output.fill = completeSetAttribute(a.fill, b.fill, 0);
        output.color = completeSetAttribute(a.color, b.color, 0);

        return output;
    }

    public int completeSetAttribute(int a, int b, int start) {
        if (a == b) {
            return a;
        }

        int output = start;
        while (output == a || output == b) {
            output++;
        }

        return output;
    }

    public void deal() {
        Random r = new Random();
        for (int i = 0; i < 12; i++) {
            onBoard.add(deck.remove(r.nextInt(deck.size())));
        }

        reshuffle();
    }

    void reshuffle() {
        Random r = new Random();
        while (!setExists()) {
            for (int i = 0; i < 3; i++) {
                onBoard.remove(r.nextInt(onBoard.size()));
            }

            for (int i = 0; i < 3; i++) {
                onBoard.add(deck.remove(r.nextInt(deck.size())));
            }
        }
    }

    public String toString() {
        String[][] rows = new String[3][6]; //3 rows of 4 cards
        for (int i = 0; i < rows.length; i++) {
            Arrays.fill(rows[i], "");
        }
        int index = 0;
        for (int i = 0; i < onBoard.size(); i++) {
            for (int j = 0; j < 6; j++) {
                rows[i / 4][j] += onBoard.get(i).image[j] + "\t";
            }
        }

        for (int i = 0; i < rows.length; i++) {
            if (!rows[i][0].equals("")) {
                rows[i][3] += "\t" + (i + 1);
            }
        }

        StringBuilder output = new StringBuilder("          a         \t          b         \t          c         \t          d\n");
        for (String[] s : rows) {
            for (String str : s) {
                output.append(str).append("\n");
            }
            output.append("\n");
        }

        return output.toString();
    }


}
