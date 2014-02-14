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

    NEGATE(new UnaryWordOperator() {
        @Override
        public void operate(WordContainer destination) {
            destination.setWord(destination.getWord().getOpposite());
        }

    }),
    INCREMENT(new UnaryWordOperator() {
        @Override
        public void operate(WordContainer destination) {
            destination.increment();
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
