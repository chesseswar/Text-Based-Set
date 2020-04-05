public class Card {
    static final String[] colors = {"\u001B[31m", "\u001B[34m", "\u001B[32m"};
    static final String RESET = "\u001B[0m";

    static final String[][][] art = {{{"  /\\  ", " /  \\ ", "/    \\", "\\    /", " \\  / ", "  \\/  "},
                                      {"  /\\  ", " /==\\ ", "/====\\", "\\====/", " \\==/ ", "  \\/  "},
                                      {"  /\\  ", " //\\\\ ", "///\\\\\\", "\\\\\\///", " \\\\// ", "  \\/  "}},
                                     {{"|‾‾‾‾|", "|    |", "|    |", "|    |", "|    |", "|____|"},
                                      {"|‾‾‾‾|", "|====|", "|====|", "|====|", "|====|", "|____|"},
                                      {"||||||", "||||||",  "||||||", "||||||", "||||||", "||||||"}},
                                     {{"  /‾‾/", " /  / ", "/  /  ", "\\  \\  ", " \\  \\ ", "  \\__\\"},
                                      {"  /‾‾/", " /==/ ", "/==/  ", "\\==\\  ", " \\==\\ ", "  \\__\\"},
                                      {"  ////", " //// ", "////  ", "\\\\\\\\  ", " \\\\\\\\ ", "  \\\\\\\\"}}};


    int count; //1, 2, or 3
    int shape; //0 == diamond, 1 == oval, 2 == squiggle
    int fill; //0 == clear, 1 == lined, 2 == solid
    int color; //0 == red, 1 == blue, 2 == green

    String[] image;
    public Card(int ct, int s, int f, int clr) {
        count = ct;
        shape = s;
        fill = f;
        color = clr;
        image = image();
    }

    public boolean validSet(Card a, Card b) {
        return valid(count, a.count, b.count) && valid(shape, a.shape, b.shape) && valid(fill, a.fill, b.fill) && valid(color, a.color, b.color);
    }

    public boolean valid(int a, int b, int c) {
        return (a != b && a != c && b != c) || (a == b && a == c);
    }

    public String[] image() {
        String[] output = new String[6];
        for (int i = 0; i < 6; i++) {
            output[i] = colors[color];
        }

        for (int i = 0; i < 6 ; i++) {
            if (count == 1) {
                output[i] += "       ";
                output[i] += art[shape][fill][i];
                output[i] += "       ";
            } else if (count == 2) {
                output[i] += "   ";
                output[i] += art[shape][fill][i] + "  ";
                output[i] += art[shape][fill][i];
                output[i] += "   ";
            } else {
                output[i] += shape == 2 ? " " : "";
                output[i] += art[shape][fill][i] + (shape == 2 ? "" : " ");
                output[i] += art[shape][fill][i] + (shape == 2 ? "" : " ");
                output[i] += art[shape][fill][i];
                output[i] += shape == 2 ? " " : "";
            }
        }

        for (int i = 0; i < 6; i++) {
            output[i] += RESET;
        }

        return output;
    }



}
