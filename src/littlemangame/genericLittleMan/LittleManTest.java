/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.genericLittleMan;

import littlemangame.genericLittleMan.GenericLittleManData;
import littlemangame.littleman.littlemanutilities.location.ComputerLocation;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public enum LittleManTest {

    ZERO(ComputerLocation.REGISTER, new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.equals(Word.ZERO_WORD);
        }

    }),
    NOT_ZERO(ComputerLocation.REGISTER, new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return !word.equals(Word.ZERO_WORD);
        }

    }),
    GREATER_THAN_ZERO(ComputerLocation.REGISTER, new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.compareTo(Word.ZERO_WORD) > 0;
        }

    }),
    GREATER_OR_EQUAL_TO_ZERO(ComputerLocation.REGISTER, new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.compareTo(Word.ZERO_WORD) >= 0;
        }

    }),
    LESS_THAN_ZERO(ComputerLocation.REGISTER, new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.compareTo(Word.ZERO_WORD) < 0;
        }

    }),
    LESS_OR_EQUAL_ZERO(ComputerLocation.REGISTER, new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.compareTo(Word.ZERO_WORD) <= 0;
        }

    });

    private final ComputerLocation computerLocation;
    private final LittleManTester littleManTester;

    private LittleManTest(ComputerLocation computerLocation, LittleManTester littleManTester) {
        this.computerLocation = computerLocation;
        this.littleManTester = littleManTester;
    }

    public ComputerLocation getComputerLocation() {
        return computerLocation;
    }

    boolean test(GenericLittleManData<?> littleManData) {
        return littleManTester.test(littleManData);
    }

    private static interface LittleManTester {

        boolean test(GenericLittleManData<?> littleManData);

    }

    private static abstract class LittleManRegisterTester implements LittleManTester {

        Word getRegisterWord(GenericLittleManData<?> littleManData) {
            return littleManData.getRegister().getWord();
        }

        abstract boolean testWord(Word word);

        @Override
        public boolean test(GenericLittleManData<?> littleManData) {
            return testWord(getRegisterWord(littleManData));
        }

    }

}
