package agh.ics.oop;

public class OptionsParser {
    MoveDirection[] parse(String[] words) {
        int n = 0;
        for (String a : words)
        {
            if (this.isCorrect(a)) n++;
            else throw new IllegalArgumentException(a + " is not legal move specification");
        }

        MoveDirection[] result;
        result = new MoveDirection[n];
        int i = 0;
        for (String a : words) {
            if (this.isCorrect(a)) {
                switch (a) {
                    case "f", "forward" -> result[i] = MoveDirection.FORWARD;
                    case "b", "backward" -> result[i] = MoveDirection.BACKWARD;
                    case "l", "left" -> result[i] = MoveDirection.LEFT;
                    case "r", "right" -> result[i] = MoveDirection.RIGHT;
                }
                i++;
            }
        }
        return result;
    }

    private Boolean isCorrect(String a) {
        return a.equals("f") || a.equals("forward") || a.equals("b") || a.equals("backward") || a.equals("r") ||
                a.equals("right") || a.equals("l") || a.equals("left");
    }
}
