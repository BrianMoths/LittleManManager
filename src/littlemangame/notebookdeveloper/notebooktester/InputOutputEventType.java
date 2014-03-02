/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.notebookdeveloper.notebooktester;

/**
 *
 * @author brian
 */
public enum InputOutputEventType {

    INPUT(new ActualAndExcpectedActionPerformedProducer() {

        @Override
        public String produceActualActionPerformed(InstanceNotebookTester instanceNotebookTester) {
            return "asked for input";
        }

        @Override
        public String produceExpectedActionPerformed(InstanceNotebookTester instanceNotebookTester) {
            return "ask for input";
        }

    }),
    OUTPUT(new ActualAndExcpectedActionPerformedProducer() {

        @Override
        public String produceActualActionPerformed(InstanceNotebookTester instanceNotebookTester) {
            return "output " + instanceNotebookTester.peekAtOutput().toString();
        }

        @Override
        public String produceExpectedActionPerformed(InstanceNotebookTester instanceNotebookTester) {
            return "output " + instanceNotebookTester.peekAtOutput().toString();
        }

    }),
    HALT(new ActualAndExcpectedActionPerformedProducer() {

        @Override
        public String produceActualActionPerformed(InstanceNotebookTester instanceNotebookTester) {
            return "halted";
        }

        @Override
        public String produceExpectedActionPerformed(InstanceNotebookTester instanceNotebookTester) {
            return "halt";
        }

    });

    private final ActualAndExcpectedActionPerformedProducer actualAndExcpectedActionPerformedProducer;

    private InputOutputEventType(ActualAndExcpectedActionPerformedProducer actualAndExcpectedActionPerformedProducer) {
        this.actualAndExcpectedActionPerformedProducer = actualAndExcpectedActionPerformedProducer;
    }

    String getActualActionString(InstanceNotebookTester instanceNotebookTester) {
        return "The little man " + produceActualActionPerformed(instanceNotebookTester);
    }

    String getExpectedActionString(InstanceNotebookTester instanceNotebookTester) {
        return "I expected him to " + produceExpectedActionPerformed(instanceNotebookTester);
    }

    private String produceActualActionPerformed(InstanceNotebookTester instanceNotebookTester) {
        return actualAndExcpectedActionPerformedProducer.produceActualActionPerformed(instanceNotebookTester);
    }

    private String produceExpectedActionPerformed(InstanceNotebookTester instanceNotebookTester) {
        return actualAndExcpectedActionPerformedProducer.produceExpectedActionPerformed(instanceNotebookTester);
    }

    static private interface ActualAndExcpectedActionPerformedProducer {

        public String produceActualActionPerformed(InstanceNotebookTester instanceNotebookTester);

        public String produceExpectedActionPerformed(InstanceNotebookTester instanceNotebookTester);

    }
}
