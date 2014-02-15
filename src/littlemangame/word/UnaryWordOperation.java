/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.word;

/**
 *
 * @author brian
 */
public enum UnaryWordOperation {

    INCREMENT(new UnaryWordOperator() {
        @Override
        public void operate(WordContainer destination) {
            destination.increment();
        }

    }),
    DECREMENT(new UnaryWordOperator() {

        @Override
        public void operate(WordContainer destination) {
            destination.decrement();
        }

    }),
    COMPLEMENT(new UnaryWordOperator() {

        @Override
        public void operate(WordContainer destination) {
            destination.complement();
        }

    }),
    NEGATE(new UnaryWordOperator() {
        @Override
        public void operate(WordContainer destination) {
            destination.setWord(destination.getWord().getOpposite());
        }

    }),
    LEFT_SHIFT(new UnaryWordOperator() {

        @Override
        public void operate(WordContainer destination) {
            destination.leftShift();
        }

    }),
    RIGHT_SHIFT_UNSIGNED(new UnaryWordOperator() {

        @Override
        public void operate(WordContainer destination) {
            destination.rightShiftUnsigned();
        }

    }),
    RIGHT_SHIFT_SIGNED(new UnaryWordOperator() {

        @Override
        public void operate(WordContainer destination) {
            destination.rightShiftSigned();
        }

    });
    private final UnaryWordOperator unaryWordOperator;

    private UnaryWordOperation(UnaryWordOperator unaryWordOperator) {
        this.unaryWordOperator = unaryWordOperator;
    }

    public void operate(WordContainer destination) {
        unaryWordOperator.operate(destination);
    }

    private static interface UnaryWordOperator {

        void operate(WordContainer destination);

    }

}
