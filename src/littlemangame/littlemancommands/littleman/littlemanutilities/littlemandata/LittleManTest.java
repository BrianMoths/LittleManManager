/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata;

import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public enum LittleManTest {

    ZERO(new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.equals(Word.ZERO_WORD);
        }

    }),
    NOT_ZERO(new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return !word.equals(Word.ZERO_WORD);
        }

    }),
    GREATER_THAN_ZERO(new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.compareTo(Word.ZERO_WORD) > 0;
        }

    }),
    GREATER_OR_EQUAL_TO_ZERO(new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.compareTo(Word.ZERO_WORD) >= 0;
        }

    }),
    LESS_THAN_ZERO(new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.compareTo(Word.ZERO_WORD) < 0;
        }

    }),
    LESS_OR_EQUAL_ZERO(new LittleManRegisterTester() {

        @Override
        boolean testWord(Word word) {
            return word.compareTo(Word.ZERO_WORD) <= 0;
        }

    });

    private final LittleManTester littleManTester;

    private LittleManTest(LittleManTester littleManTester) {
        this.littleManTester = littleManTester;
    }

    boolean test(LittleManData littleManData) {
        return littleManTester.test(littleManData);
    }

    private static interface LittleManTester {

        boolean test(LittleManData littleManData);

    }

    private static abstract class LittleManRegisterTester implements LittleManTester {

        Word getRegisterWord(LittleManData littleManData) {
            return littleManData.getRegister().getWord();
        }

        abstract boolean testWord(Word word);

        @Override
        public boolean test(LittleManData littleManData) {
            return testWord(getRegisterWord(littleManData));
        }

    }

}
