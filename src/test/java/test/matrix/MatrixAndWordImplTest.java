package test.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.matrix.exceptions.EmptyInputException;
import test.matrix.exceptions.IncorrectLengthException;

class MatrixAndWordImplTest {
    private static MatrixAndWordImpl matrixAndWord;

    @BeforeAll
    static void beforeAll() {
        matrixAndWord = new MatrixAndWordImpl();
    }

    @Test
    void isEmptyInput_NotOk() {
        assertThrows(EmptyInputException.class, () ->
                matrixAndWord.getSequenceOfCells("", "Nice"));
        assertThrows(EmptyInputException.class, () ->
                matrixAndWord.getSequenceOfCells("ABCDQWQER", ""));
        assertThrows(EmptyInputException.class, () ->
                matrixAndWord.getSequenceOfCells("", ""));
    }

    @Test
    void incorrectLength_notOk() {
        assertThrows(IncorrectLengthException.class, () ->
                matrixAndWord.getSequenceOfCells("ABC", "A"));
        assertThrows(IncorrectLengthException.class, () ->
                matrixAndWord.getSequenceOfCells("ABCCDE", "..."));
        assertThrows(IncorrectLengthException.class, () ->
                matrixAndWord.getSequenceOfCells("AB", "p"));
    }

    @Test
    void input_Ok() {
        assertEquals("[0,0]->[0,1]->[1,1]->[1,0]",
                matrixAndWord.getSequenceOfCells("NIEC", "Nice"));
        assertEquals("[0,1]->[1,1]->[1,2]->[2,2]",
                matrixAndWord.getSequenceOfCells("QNWAICSDE", "Nice"));
        assertEquals("[0,2]->[1,2]->[2,2]",
                matrixAndWord.getSequenceOfCells("ABCDEABABAABRABABCDAABCDS", "CAR"));
    }

    @Test
    void inputWithNumbers_Ok() {
        assertEquals("[0,0]->[0,1]->[1,1]->[1,0]",
                matrixAndWord.getSequenceOfCells("NI11EC121234-=+/", "Nice"));
    }

    @Test
    void inputWithTheSameLetters_Ok() {
        assertEquals("[1,2]->[1,3]->[0,3]->[0,2]",
                matrixAndWord.getSequenceOfCells("QLGNAEKIRLRNGEAE", "King"));
        assertEquals("[0,0]->[1,0]->[1,1]->[2,1]",
                matrixAndWord.getSequenceOfCells("MKAAILNNL", "MAIN"));
        assertEquals("[1,2]->[1,3]->[2,3]->[3,3]",
                matrixAndWord.getSequenceOfCells("QLZNAEKIRLRNGEAG", "King"));
    }

    @Test
    void inputWithDoubleLetters_Ok() {
        assertEquals("[0,0]->[0,1]->[0,2]->[1,2]->[2,2]",
                matrixAndWord.getSequenceOfCells("APPCDLAZE", "APPLE"));
        assertEquals("[0,1]->[0,0]->[1,0]->[2,0]->[3,0]->[3,1]->[2,1]",
                matrixAndWord.getSequenceOfCells("OCFGRASDRTGVECFD", "CORRECT"));
    }

    @Test
    void fourHundredLetters_Ok() {
        assertEquals("[12,11]->[12,12]->[12,13]->[11,13]->[10,13]->[9,13]->[9,14]"
                + "->[9,15]->[9,16]->[10,16]->[11,16]->[12,16]->[13,16]->[14,16]->[15,16]->"
                + "[16,16]->[17,16]->[18,16]->[19,16]->[19,15]->[19,14]->[19,13]->[19,12]->"
                + "[19,11]->[19,10]->[19,9]->[19,8]", matrixAndWord.getSequenceOfCells(
                "QWERTYUIOPASDFGHJKLZZXCVBinidutilGHDCVERFDSSZBDQERTYiNJYXVWS"
                        + "XGDSAQQQWADAbNHSZYLLVBXZXDAAAXSZaBSASKKZYWQAFSFDZVCXcVCVFGCX"
                        + "EdutilibacificabilitRASUYIZVBNZiUASLOLFuAFDTSIXCBNXrTETIRWUd"
                        + "SQERFEWASVCoMificDNiSAZZZDFJKMMnTRDDaESnSSXCVBGHBNBoRoYFbROi"
                        + "ibacifironoHonSFiIFtlYYZYAASFFFFnQMHlTUaiYZYSAQHDFDSoHGGiINt"
                        + "yZZXXSWFYFQQrWGHtLNTuZFDFSRGSRTFYRYYuLYEdYYFGFTZADFFQRZXdINS"
                        + "iZZXAbcsDAAEARBZiHMTnitatiuasubitatinCLW",
                "HONORIFICABILITUDINITATIBUS"));
    }

    @Test
    void inputWithManyVariations_Ok() {
        assertEquals("[0,0]->[1,0]->[2,0]->[3,0]->[3,1]->[4,1]->[4,2]->[4,3]",
                matrixAndWord.getSequenceOfCells("SOMEBOBABOMBRADEBZDAYODYS", "SOMEBODY"));
        assertEquals("[0,0]->[1,0]->[2,0]->[3,0]->[3,1]->[4,1]->[4,2]->[4,3]",
                matrixAndWord.getSequenceOfCells("SOZDEOBABAMBRABEBZDAYODYS", "SOMEBODY"));
        assertEquals("[0,2]->[1,2]->[2,2]->[3,2]->[3,3]->[2,3]",
                matrixAndWord.getSequenceOfCells("ARDFGRAARAOTFZANDAXVGOXCXNKRWHLSWQPR",
                        "Dragon"));
        assertEquals("[0,0]->[0,1]->[1,1]->[2,1]->[2,2]->[2,3]->[2,4]->[3,4]->[4,4]->[4,3]",
                matrixAndWord.getSequenceOfCells("EXPERXPOIUPERIEZXCVNASDEC", "EXPERIENCE"));
    }

    @Test
    void inputWithLowerCase_Ok() {
        assertEquals("[0,0]->[1,0]->[2,0]->[3,0]->[3,1]->[4,1]->[4,2]->[4,3]",
                matrixAndWord.getSequenceOfCells("SOMEBOBABOMBRADEBZDAYODYS", "SoMeBOdY"));
        assertEquals("[1,0]->[1,1]->[0,1]->[0,2]->[1,2]->[1,3]",
                matrixAndWord.getSequenceOfCells("ZANZORGENMBCKJJG", "OraNgE"));
    }

    @Test
    void inputWithTwoVariations_Ok() {
        assertEquals("[0,0]->[1,0]->[2,0]->[2,1]",
                matrixAndWord.getSequenceOfCells("MAQALLINL", "Main"));
    }

    @Test
    void wordIsNotFound_Ok() {
        assertThrows(NoSuchElementException.class, () ->
                matrixAndWord.getSequenceOfCells("MAIQAFGHIEWLPLGF", "Main"));
        assertThrows(NoSuchElementException.class, () ->
                matrixAndWord.getSequenceOfCells("ACFGRRSDRTGVECFD", "CORRECT"));
    }

    @Test
    void wordIsNotFound_NotOk() {
        assertThrows(NoSuchElementException.class, () ->
                matrixAndWord.getSequenceOfCells("ASDFAPEOSZLPZXCV", "PEOPLE"));
        assertThrows(NoSuchElementException.class, () ->
                matrixAndWord.getSequenceOfCells("DISISVZOXCVNZXCV", "DIVISION"));
    }
}
