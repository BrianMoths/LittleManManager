/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import java.util.HashMap;
import java.util.Map;
import static littlemangame.instructions.InstructionFromSet.NO_OPERATION;
import littlemangame.littleman.LittleManAction;
import static littlemangame.littleman.LittleManCommander.*;
import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public enum InstructionFromSet {

    NO_OPERATION(Word.ZERO_WORD, false, NoOperation),
    HALT(Word.valueOfLastDigitsOfInteger(9), false, halt),
    PRINT_UNSIGNED(Word.valueOfLastDigitsOfInteger(20), false, memorizeRegister, printRememberedWordToOutputPanel),
    LOAD_DIRECT(Word.valueOfLastDigitsOfInteger(30), true, memorizeOperand, setRegisterToRememberedWord),
    LOAD_INDIRECT(Word.valueOfLastDigitsOfInteger(31), true, memorizeMemoryAtOperandAddress, setRegisterToRememberedWord),
    STORE(Word.valueOfLastDigitsOfInteger(33), true, memorizeRegister, setMemoryAtOperandToRememberedWord);
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

    private InstructionFromSet(Word opcode, Instruction instruction) {
        this.opcode = opcode;
        this.instruction = instruction;
    }

    private InstructionFromSet(Word opcode, boolean hasOperand, LittleManAction littleManAction) {
        this.opcode = opcode;
        if (hasOperand) {
            this.instruction = new InstructionWithOperand(littleManAction);
        } else {
            this.instruction = new NoOperandInstruction(littleManAction);
        }
    }

    private InstructionFromSet(Word opcode, boolean hasOperand, LittleManAction... littleManAction) {
        this.opcode = opcode;
        if (hasOperand) {
            this.instruction = new InstructionWithOperand(littleManAction);
        } else {
            this.instruction = new NoOperandInstruction(littleManAction);
        }
    }

    public Word getOpcode() {
        return opcode;
    }

    public Instruction getInstruction() {
        return instruction;
    }

}
