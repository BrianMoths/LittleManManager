/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import java.util.HashMap;
import java.util.Map;
import static littlemangame.instructions.InstructionOperandTypes.*;
import littlemangame.littlemancommands.littleman.DestinationOperand;
import littlemangame.littlemancommands.littleman.littlemanutilities.LittleManAction;
import littlemangame.littlemancommands.littleman.LittleManCommander;
import static littlemangame.littlemancommands.littleman.LittleManCommander.*;
import littlemangame.littlemancommands.littleman.SourceOperand;
import static littlemangame.littlemancommands.littleman.SourceOperand.*;
import littlemangame.word.BinaryWordOperation;
import static littlemangame.word.BinaryWordOperation.*;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public enum InstructionFromSet {

    NO_OPERATION(Word.ZERO_WORD, NEITHER, NoOperation),
    HALT(Word.valueOfLastDigitsOfInteger(9), NEITHER, halt),
    UNCONDITIONAL_JUMP(Word.valueOfLastDigitsOfInteger(10), DATA_ONLY, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    PRINT_UNSIGNED(Word.valueOfLastDigitsOfInteger(20), NEITHER, memorizeAddressAtRegister, printRememberedWordToOutputPanel),
    LOAD_DIRECT(Word.valueOfLastDigitsOfInteger(30), DATA_ONLY, SET, IMMEDIATE, DestinationOperand.REGISTER),
    LOAD_INDIRECT_IMMEDIATE(Word.valueOfLastDigitsOfInteger(31), ADDRESS_ONLY, SET, MEMORY, DestinationOperand.REGISTER),
    LOAD_INDIRECT_REGISTER(Word.valueOfLastDigitsOfInteger(32), NEITHER, memorizeAddressAtRegister, memorizeDataAtRememberedAddress, setRegisterToRememberedData),
    STORE_IMMEDIATE_DATA(Word.valueOfLastDigitsOfInteger(34), DATA_ONLY, memorizeAddressAtRegister, setMemoryAtRememberedAddressToRememberedData),
    STORE_IMMEDIATE_ADDRESS(Word.valueOfLastDigitsOfInteger(35), ADDRESS_ONLY, memorizeDataAtRegister, setMemoryAtRememberedAddressToRememberedData),
    STORE_IMMEDIATE_IMMEDIATE(Word.valueOfLastDigitsOfInteger(36), BOTH, setMemoryAtRememberedAddressToRememberedData);
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
        this.instruction = instructionOperandTypes.makeInstruction(littleManAction);
    }

    private InstructionFromSet(Word opcode, InstructionOperandTypes instructionOperandTypes, LittleManAction... littleManAction) {
        this.opcode = opcode;
        this.instruction = instructionOperandTypes.makeInstruction(littleManAction);
    }

    private InstructionFromSet(Word opcode, InstructionOperandTypes instructionOperandTypes, BinaryWordOperation wordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
        this.opcode = opcode;
        this.instruction = instructionOperandTypes.makeInstruction(LittleManCommander.doOperationOnOperands(wordOperation, sourceOperand, destinationOperand));
    }

    public Word getOpcode() {
        return opcode;
    }

    public Instruction getInstruction() {
        return instruction;
    }

}
