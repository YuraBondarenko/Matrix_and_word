package test.matrix;

import java.util.NoSuchElementException;
import test.matrix.exceptions.EmptyInputException;
import test.matrix.exceptions.IncorrectLengthException;

public class MatrixAndWordImpl implements MatrixAndWord {
    private int firstPosition;
    private int secondPosition;
    private int size;
    private int lengthOfMatrix;
    private String word;
    private String letters;
    private String firstLetterPosition;
    private Character[][] characters;

    @Override
    public String getSequence_Of_Cells(String letters, String word) {
        this.letters = letters.toUpperCase();
        this.word = word.toUpperCase();
        size = 0;
        checkInputData();
        lengthOfMatrix = (int) Math.sqrt(this.letters.length());
        characters = new Character[lengthOfMatrix][lengthOfMatrix];
        getCharacterArray();
        StringBuilder result = new StringBuilder();
        result.append(getFirstLetter());
        return finder(result);
    }

    private String finder(StringBuilder result) {
        int counter = 0;
        boolean isFoundLetter = false;
        while (counter < this.word.length()) {
            for (int i = firstPosition != 0 ? firstPosition - 1 : 0; i < lengthOfMatrix; i++) {
                for (int j = secondPosition != 0 ? secondPosition - 1 : 0;
                        j < lengthOfMatrix; j++) {
                    if (size < this.word.length()
                            && characters[i][j] == this.word.charAt(size)
                            && (((i == firstPosition + 1 || i == firstPosition - 1)
                            && j == secondPosition)
                            || ((j == secondPosition + 1 || j == secondPosition - 1)
                            && i == firstPosition))
                            && checkNextLetters(i, j, size)) {
                        result.append("[").append(i).append(",").append(j).append("]");
                        if (size != this.word.length() - 1) {
                            result.append("->");
                        }
                        size++;
                        isFoundLetter = true;
                        firstPosition = i;
                        secondPosition = j;
                        characters[i][j] = ' ';
                        break;
                    }
                }
                if (isFoundLetter) {
                    isFoundLetter = false;
                    break;
                }
            }
            counter++;
        }
        checkIsWordFound();
        return result.toString();
    }

    private void getCharacterArray() {
        int counter = 0;
        for (int i = 0; i < lengthOfMatrix; i++) {
            for (int j = 0; j < lengthOfMatrix; j++) {
                characters[i][j] = letters.charAt(counter);
                counter++;
            }
        }
    }

    private String getFirstLetter() {
        for (int i = 0; i < lengthOfMatrix; i++) {
            for (int j = 0; j < lengthOfMatrix; j++) {
                if (characters[i][j] == word.charAt(0) && size < 1
                        && checkNextLetters(i, j, size)) {
                    firstLetterPosition = "[" + i + "," + j + "]" + "->";
                    this.firstPosition = i;
                    this.secondPosition = j;
                    size++;
                    characters[i][j] = ' ';
                    break;
                }
            }
        }
        return firstLetterPosition;
    }

    private void checkIsWordFound() {
        if (size != word.length()) {
            throw new NoSuchElementException("Word isn't found");
        }
    }

    private void checkInputData() {
        if (letters.equals("") || word.equals("")) {
            throw new EmptyInputException("Input cannot be empty");
        }
        if (letters.length() % Math.sqrt(letters.length()) != 0) {
            throw new IncorrectLengthException("Incorrect input length");
        }
    }

    private boolean checkNextLetters(int localX, int localY, int sizeCounter) {
        int localSize = sizeCounter + 1;
        while (localSize < word.length()) {
            if (localY != lengthOfMatrix - 1
                    && characters[localX][localY + 1] == word.charAt(sizeCounter + 1)
                    && checkNextLetters(localX, localY + 1, sizeCounter + 1)) {
                break;
            } else if (localY != 0
                    && characters[localX][localY - 1] == word.charAt(sizeCounter + 1)
                    && checkNextLetters(localX, localY - 1, sizeCounter + 1)) {
                break;
            } else if (localX != 0
                    && characters[localX - 1][localY] == word.charAt(sizeCounter + 1)
                    && checkNextLetters(localX - 1, localY, sizeCounter + 1)) {
                break;
            } else if (localX != lengthOfMatrix - 1
                    && characters[localX + 1][localY] == word.charAt(sizeCounter + 1)
                    && checkNextLetters(localX + 1, localY, sizeCounter + 1)) {
                break;
            } else {
                return false;
            }
        }
        return true;
    }
}
