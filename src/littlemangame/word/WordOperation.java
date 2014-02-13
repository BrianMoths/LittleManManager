/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.word;

import littlemangame.word.Word;
import littlemangame.word.WordContainer;

/**
 *
 * @author brian
 */
public enum WordOperation {

    ADD(new WordOperator() {
        @Override
        public void operate(Word source, WordContainer destination) {
            destination.incrementBy(source);
        }

    }),
    SET(new WordOperator() {
        @Override
        public void operate(Word source, WordContainer destination) {
            destination.setWord(source);
        }

    });
    private final WordOperator wordOperator;

    private WordOperation(WordOperator wordOperator) {
        this.wordOperator = wordOperator;
    }

    public void operate(Word source, WordContainer destination) {
        wordOperator.operate(source, destination);
    }

    private static interface WordOperator {

        void operate(Word source, WordContainer destination);

    }

}
