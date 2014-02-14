/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.word;

/**
 *
 * @author brian
 */
public enum BinaryWordOperation {

    ADD(new BinaryWordOperator() {
        @Override
        public void operate(Word source, WordContainer destination) {
            destination.incrementBy(source);
        }

    }),
    SET(new BinaryWordOperator() {
        @Override
        public void operate(Word source, WordContainer destination) {
            destination.setWord(source);
        }

    });
    private final BinaryWordOperator binaryWordOperator;

    private BinaryWordOperation(BinaryWordOperator binaryWordOperator) {
        this.binaryWordOperator = binaryWordOperator;
    }

    public void operate(Word source, WordContainer destination) {
        binaryWordOperator.operate(source, destination);
    }

    private static interface BinaryWordOperator {

        void operate(Word source, WordContainer destination);

    }

}
