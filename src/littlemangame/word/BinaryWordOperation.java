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

    SET(new BinaryWordOperator() {
        @Override
        public void operate(Word source, WordContainer destination) {
            destination.setWord(source);
        }

    }),
    ADD(new BinaryWordOperator() {
        @Override
        public void operate(Word source, WordContainer destination) {
            destination.incrementBy(source);
        }

    }),
    SUBTRACT(new BinaryWordOperator() {

        @Override
        public void operate(Word source, WordContainer destination) {
            destination.decrementBy(source);
        }

    }),
    DIGITWISE_MAX(new BinaryWordOperator() {

        @Override
        public void operate(Word source, WordContainer destination) {
            destination.digitwiseMax(source);
        }

    }),
    DIGITWISE_MIN(new BinaryWordOperator() {

        @Override
        public void operate(Word source, WordContainer destination) {
            destination.digitwiseMin(source);
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
