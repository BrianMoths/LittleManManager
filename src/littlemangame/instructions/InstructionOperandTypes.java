/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import littlemangame.littlemancommands.littleman.littlemanutilities.LittleManAction;

/**
 *
 * @author brian
 */
enum InstructionOperandTypes {

    NEITHER(false, false, new InstructionFactory() {
        @Override
        public Instruction makeInstruction(LittleManAction littleManAction) {
            return new NoOperandInstruction(littleManAction);
        }

        @Override
        public Instruction makeInstruction(LittleManAction... littleManAction) {
            return new NoOperandInstruction(littleManAction);
        }

    }),
    DATA_ONLY(true, false, new InstructionFactory() {
        @Override
        public Instruction makeInstruction(LittleManAction littleManAction) {
            return new InstructionWithOnlyDataOperand(littleManAction);
        }

        @Override
        public Instruction makeInstruction(LittleManAction... littleManAction) {
            return new InstructionWithOnlyDataOperand(littleManAction);
        }

    }),
    ADDRESS_ONLY(false, true, new InstructionFactory() {
        @Override
        public Instruction makeInstruction(LittleManAction littleManAction) {
            return new InstructionWithOnlyAddressOperand(littleManAction);
        }

        @Override
        public Instruction makeInstruction(LittleManAction... littleManAction) {
            return new InstructionWithOnlyAddressOperand(littleManAction);
        }

    }),
    BOTH(true, true, new InstructionFactory() {
        @Override
        public Instruction makeInstruction(LittleManAction littleManAction) {
            return new InstructionWithBothOperands(littleManAction);
        }

        @Override
        public Instruction makeInstruction(LittleManAction... littleManAction) {
            return new InstructionWithBothOperands(littleManAction);
        }

    });

    static private interface InstructionFactory {

        public Instruction makeInstruction(LittleManAction littleManAction);

        public Instruction makeInstruction(LittleManAction... littleManAction);

    }

    private final boolean isDataOperandNeeded, isAddressOperandNeeded;
    private final InstructionFactory instructionFactory;

    private InstructionOperandTypes(boolean isDataOperandNeeded, boolean isAddressOperandNeeded, InstructionFactory instructionFactory) {
        this.isDataOperandNeeded = isDataOperandNeeded;
        this.isAddressOperandNeeded = isAddressOperandNeeded;
        this.instructionFactory = instructionFactory;
    }

    public boolean isDataOperandNeeded() {
        return isDataOperandNeeded;
    }

    public boolean isAddressOperandNeeded() {
        return isAddressOperandNeeded;
    }

    public Instruction makeInstruction(LittleManAction littleManAction) {
        return instructionFactory.makeInstruction(littleManAction);
    }

    public Instruction makeInstruction(LittleManAction... littleManAction) {
        return instructionFactory.makeInstruction(littleManAction);
    }

}
