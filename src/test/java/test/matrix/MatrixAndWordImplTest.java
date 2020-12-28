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
                matrixAndWord.getSequence_Of_Cells("", "Nice"));
        assertThrows(EmptyInputException.class, () ->
                matrixAndWord.getSequence_Of_Cells("ABCDQWQER", ""));
        assertThrows(EmptyInputException.class, () ->
                matrixAndWord.getSequence_Of_Cells("", ""));
    }

    @Test
    void incorrectLength_notOk() {
        assertThrows(IncorrectLengthException.class, () ->
                matrixAndWord.getSequence_Of_Cells("ABC", "A"));
        assertThrows(IncorrectLengthException.class, () ->
                matrixAndWord.getSequence_Of_Cells("ABCCDE", "..."));
        assertThrows(IncorrectLengthException.class, () ->
                matrixAndWord.getSequence_Of_Cells("AB", "p"));
    }

    @Test
    void input_Ok() {
        assertEquals("[0,0]->[0,1]->[1,1]->[1,0]",
                matrixAndWord.getSequence_Of_Cells("NIEC", "Nice"));
        assertEquals("[0,1]->[1,1]->[1,2]->[2,2]",
                matrixAndWord.getSequence_Of_Cells("QNWAICSDE", "Nice"));
        assertEquals("[0,2]->[1,2]->[2,2]",
                matrixAndWord.getSequence_Of_Cells("ABCDEABABAABRABABCDAABCDS", "CAR"));
    }

    @Test
    void inputWithNumbers_Ok() {
        assertEquals("[0,0]->[0,1]->[1,1]->[1,0]",
                matrixAndWord.getSequence_Of_Cells("NI11EC121234-=+/", "Nice"));
    }

    @Test
    void inputWithTheSameLetters_Ok() {
        assertEquals("[1,2]->[1,3]->[0,3]->[0,2]",
                matrixAndWord.getSequence_Of_Cells("QLGNAEKIRLRNGEAE", "King"));
        assertEquals("[0,0]->[1,0]->[1,1]->[2,1]",
                matrixAndWord.getSequence_Of_Cells("MKAAILNNL", "MAIN"));
        assertEquals("[1,2]->[1,3]->[2,3]->[3,3]",
                matrixAndWord.getSequence_Of_Cells("QLZNAEKIRLRNGEAG", "King"));
    }

    @Test
    void inputWithDoubleLetters_Ok() {
        assertEquals("[0,0]->[0,1]->[0,2]->[1,2]->[2,2]",
                matrixAndWord.getSequence_Of_Cells("APPCDLAZE", "APPLE"));
        assertEquals("[0,1]->[0,0]->[1,0]->[2,0]->[3,0]->[3,1]->[2,1]",
                matrixAndWord.getSequence_Of_Cells("OCFGRASDRTGVECFD", "CORRECT"));
    }

    @Test
    void fourHundredLetters_Ok() {
        assertEquals("[12,11]->[12,12]->[12,13]->[11,13]->[10,13]->[9,13]->[9,14]"
                + "->[9,15]->[9,16]->[10,16]->[11,16]->[12,16]->[13,16]->[14,16]->[15,16]->"
                + "[16,16]->[17,16]->[18,16]->[19,16]->[19,15]->[19,14]->[19,13]->[19,12]->"
                + "[19,11]->[19,10]->[19,9]->[19,8]", matrixAndWord.getSequence_Of_Cells(
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
                matrixAndWord.getSequence_Of_Cells("SOMEBOBABOMBRADEBZDAYODYS", "SOMEBODY"));
        assertEquals("[0,0]->[1,0]->[2,0]->[3,0]->[3,1]->[4,1]->[4,2]->[4,3]",
                matrixAndWord.getSequence_Of_Cells("SOZDEOBABAMBRABEBZDAYODYS", "SOMEBODY"));
        assertEquals("[0,2]->[1,2]->[2,2]->[3,2]->[3,3]->[2,3]",
                matrixAndWord.getSequence_Of_Cells("ARDFGRAARAOTFZANDAXVGOXCXNKRWHLSWQPR",
                        "Dragon"));
        assertEquals("[0,0]->[0,1]->[1,1]->[2,1]->[2,2]->[2,3]->[2,4]->[3,4]->[4,4]->[4,3]",
                matrixAndWord.getSequence_Of_Cells("EXPERXPOIUPERIEZXCVNASDEC", "EXPERIENCE"));
    }

    @Test
    void inputWithLowerCase_Ok() {
        assertEquals("[0,0]->[1,0]->[2,0]->[3,0]->[3,1]->[4,1]->[4,2]->[4,3]",
                matrixAndWord.getSequence_Of_Cells("SOMEBOBABOMBRADEBZDAYODYS", "SoMeBOdY"));
        assertEquals("[1,0]->[1,1]->[0,1]->[0,2]->[1,2]->[1,3]",
                matrixAndWord.getSequence_Of_Cells("ZANZORGENMBCKJJG", "OraNgE"));
    }

    @Test
    void inputWithTwoVariations_Ok() {
        assertEquals("[0,0]->[1,0]->[2,0]->[2,1]",
                matrixAndWord.getSequence_Of_Cells("MAQALLINL", "Main"));
    }

    @Test
    void wordIsNotFound_Ok() {
        assertThrows(NoSuchElementException.class, () ->
                matrixAndWord.getSequence_Of_Cells("MAIQAFGHIEWLPLGF", "Main"));
        assertThrows(NoSuchElementException.class, () ->
                matrixAndWord.getSequence_Of_Cells("ACFGRRSDRTGVECFD", "CORRECT"));
    }

    @Test
    void wordIsNotFound_NotOk() {
        assertThrows(NoSuchElementException.class, () ->
                matrixAndWord.getSequence_Of_Cells("ASDFAPEOSZLPZXCV", "PEOPLE"));
        assertThrows(NoSuchElementException.class, () ->
                matrixAndWord.getSequence_Of_Cells("DISISVZOXCVNZXCV", "DIVISION"));
    }
}
