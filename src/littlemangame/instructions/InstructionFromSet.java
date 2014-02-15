/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import java.util.HashMap;
import java.util.Map;
import static littlemangame.instructions.SourceOperand.*;
import littlemangame.instructions.interfaceandimplementations.Instruction;
import littlemangame.instructions.interfaceandimplementations.InstructionOperandTypes;
import static littlemangame.instructions.interfaceandimplementations.InstructionOperandTypes.*;
import littlemangame.instructions.interfaceandimplementations.ParselessInstruction;
import littlemangame.littlemancommands.LittleManAction;
import littlemangame.littlemancommands.LittleManCommander;
import static littlemangame.littlemancommands.LittleManCommander.*;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManWordContainer;
import littlemangame.word.BinaryWordOperation;
import static littlemangame.word.BinaryWordOperation.*;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public enum InstructionFromSet {

    NO_OPERATION(Word.ZERO_WORD, NEITHER, nullAction),
    HALT(9, NEITHER, haltAction),
    UNCONDITIONAL_JUMP(10, DATA_ONLY, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    PRINT_UNSIGNED(20, NEITHER, memorizeDataAtContainerAction(LittleManWordContainer.REGISTER), printUnsignedToOutputPanelAction),
    LOAD_DIRECT(30, DATA_ONLY, SET, IMMEDIATE, DestinationOperand.REGISTER),
    LOAD_INDIRECT_IMMEDIATE(31, ADDRESS_ONLY, SET, MEMORY, DestinationOperand.REGISTER),
    LOAD_INDIRECT_REGISTER(32, NEITHER, memorizeAddressAtContainerAction(LittleManWordContainer.REGISTER), memorizeDataAtContainerAction(LittleManWordContainer.REMEMBERED_MEMORY), doBinaryOperationOnContainerAction(LittleManWordContainer.REGISTER, SET)),
    STORE_IMMEDIATE_DATA(34, DATA_ONLY, memorizeAddressAtContainerAction(LittleManWordContainer.REGISTER), doBinaryOperationOnContainerAction(LittleManWordContainer.REMEMBERED_MEMORY, SET)),
    STORE_IMMEDIATE_ADDRESS(35, ADDRESS_ONLY, memorizeDataAtContainerAction(LittleManWordContainer.REGISTER), doBinaryOperationOnContainerAction(LittleManWordContainer.REMEMBERED_MEMORY, SET)),
    STORE_IMMEDIATE_IMMEDIATE(36, BOTH, doBinaryOperationOnContainerAction(LittleManWordContainer.REMEMBERED_MEMORY, SET));
    static private final Map<Word, InstructionFromSet> instructionMap = new HashMap<>();

    static {
        for (InstructionFromSet instructionFromSet : InstructionFromSet.values()) {
            instructionMap.put(instructionFromSet.getOpcode(), instructionFromSet);
        }
    }

    static public Instruction decodeInstruction(Word word) {
        final InstructionFromSet instructionFromSet = instructionMap.get(word);
        if (instructionFromSet == null) {
            return NO_OPERATION.getInstruction();
        } else {
            return instructionFromSet.getInstruction();
        }
    }

    private final Instruction instruction;
    private final Word opcode;

    private InstructionFromSet(Word opcode, InstructionOperandTypes instructionOperandTypes, LittleManAction littleManAction) {
        this.opcode = opcode;
        this.instruction = new ParselessInstruction(instructionOperandTypes, littleManAction);
    }

    private InstructionFromSet(Word opcode, InstructionOperandTypes instructionOperandTypes, LittleManAction... littleManAction) {
        this.opcode = opcode;
        this.instruction = new ParselessInstruction(instructionOperandTypes, littleManAction);
    }

    private InstructionFromSet(Word opcode, InstructionOperandTypes instructionOperandTypes, BinaryWordOperation wordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
        this(opcode, instructionOperandTypes, sourceOperand.getOperandMemorizer(), destinationOperand.getPreparationAction(), LittleManCommander.doBinaryOperationOnContainerAction(destinationOperand.getDestinationContainer(), wordOperation));
    }

    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, LittleManAction littleManAction) {
        this(Word.valueOfLastDigitsOfInteger(opcode), instructionOperandTypes, littleManAction);
    }

    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, LittleManAction... littleManAction) {
        this(Word.valueOfLastDigitsOfInteger(opcode), instructionOperandTypes, littleManAction);
    }

    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, BinaryWordOperation wordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
        this(Word.valueOfLastDigitsOfInteger(opcode), instructionOperandTypes, wordOperation, sourceOperand, destinationOperand);
    }

    public Word getOpcode() {
        return opcode;
    }

    public Instruction getInstruction() {
        return instruction;
    }

}
