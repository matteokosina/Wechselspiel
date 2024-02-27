package exercises.classes;

public class Spielfeld {
    private int[][] spielfeld;
    private final int size;
    private final int number_of_colors;

    public Spielfeld(int size, int number_of_colors) {
        this.size = size;
        this.number_of_colors = number_of_colors;
        this.spielfeld = new int[size][size];
        for (int row = 0; row < spielfeld.length; row++) {
            for (int column = 0; column < spielfeld[row].length; column++) {
                spielfeld[row][column] = (int) (Math.random() * number_of_colors);
                while (!isValid(spielfeld, row, column)) {
                    spielfeld[row][column] = (spielfeld[row][column] + 1) % number_of_colors;
                }
            }
        }
    }

    public int[][] getSpielfeld() {
        return spielfeld;
    }

    // Das passt schon so
    private boolean isValid(int[][] spielfeld, int row, int column) {
        if (spielfeld[row][column] < 0 || spielfeld[row][column] > (size - 1)) return false;
        if (column > 1 && spielfeld[row][column - 2] == spielfeld[row][column - 1]
                && spielfeld[row][column - 1] == spielfeld[row][column]) return false;

        if (row > 1 && spielfeld[row - 2][column] == spielfeld[row - 1][column]
                && spielfeld[row - 1][column] == spielfeld[row][column]) return false;

        return true;
    }

    public int swap(int row1, int column1, int row2, int column2) {
        int[][] original_spielfeld = copy(this.spielfeld);
        int temp = this.spielfeld[row1][column1];
        this.spielfeld[row1][column1] = this.spielfeld[row2][column2];
        this.spielfeld[row2][column2] = temp;
        int points = getPoints(this.spielfeld);
        if (points == 0) this.spielfeld = original_spielfeld;
        return points;
    }

    private int getPoints(int[][] spielfeld) {
        int counter = 0;
        var row_spielfeld = spielfeld.clone();
        // check duplicates in rows
        for (int row = 0; row < spielfeld.length; row++) {
            for (int column = 0; column < spielfeld[row].length; column++) {
                if (spielfeld[row][column] == number_of_colors) continue;
                if (column < (size - 1) && spielfeld[row][column] == spielfeld[row][column + 1]) {
                    if (column < (size - 2) && spielfeld[row][column + 1] == spielfeld[row][column + 2]) {
                        counter += 30;
                        spielfeld[row][column] = number_of_colors;
                        spielfeld[row][column + 1] = number_of_colors;
                        if (column < (size - 3) && spielfeld[row][column + 2] == spielfeld[row][column + 3]) {
                            counter += 10;
                            spielfeld[row][column + 2] = number_of_colors;
                            if (column < (size - 4) && spielfeld[row][column + 3] == spielfeld[row][column + 4]) {
                                counter += 10;
                                spielfeld[row][column + 3] = number_of_colors;
                                spielfeld[row][column + 4] = number_of_colors;
                            } else {
                                spielfeld[row][column + 3] = number_of_colors;
                            }
                        } else {
                            spielfeld[row][column + 2] = number_of_colors;
                        }
                    }
                }
            }
        }
        // check duplicates in columns
        for (int row = 0; row < row_spielfeld.length; row++) {
            for (int column = 0; column < row_spielfeld[row].length; column++) {
                if (row_spielfeld[row][column] == number_of_colors) continue;
                if (row < (size - 1) && row_spielfeld[row][column] == row_spielfeld[row + 1][column]) {
                    if (row < (size - 2) && row_spielfeld[row + 1][column] == row_spielfeld[row + 2][column]) {
                        counter += 30;
                        row_spielfeld[row][column] = number_of_colors;
                        row_spielfeld[row + 1][column] = number_of_colors;
                        if (row < (size - 3) && row_spielfeld[row + 2][column] == row_spielfeld[row + 3][column]) {
                            counter += 10;
                            row_spielfeld[row + 2][column] = number_of_colors;
                            if (row < (size - 4) && row_spielfeld[row + 3][column] == row_spielfeld[row + 4][column]) {
                                counter += 10;
                                row_spielfeld[row + 3][column] = number_of_colors;
                                row_spielfeld[row + 4][column] = number_of_colors;
                            } else {
                                row_spielfeld[row + 3][column] = number_of_colors;
                            }
                        } else {
                            row_spielfeld[row + 2][column] = number_of_colors;
                        }
                    }
                }
            }
        }
        if (counter == 0) return counter;
        else {
            reinitialize(spielfeld);
            reinitialize(row_spielfeld);
            return counter + getPoints(this.spielfeld);
        }

    }

    private void reinitialize(int[][] spielfeld) {
        for (int row = 0; row < spielfeld.length; row++) {
            for (int column = 0; column < spielfeld[row].length; column++) {
                if (spielfeld[row][column] == number_of_colors) {
                    this.spielfeld[row][column] = (int) (Math.random() * number_of_colors);
                }
            }
        }
    }

    private int[][] copy(int[][] src) {
        int[][] dst = new int[src.length][src[0].length];
        for (int row = 0; row < src.length; row++) {
            for (int column = 0; column < src[row].length; column++) {
                dst[row][column] = src[row][column];
            }
        }
        return dst;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int[] row : spielfeld) {
            for (int cell : row) {
                s.append(cell).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
