/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import java.util.HashMap;
import java.util.Map;
import littlemangame.instructions.interfaceandimplementations.Instruction;
import littlemangame.instructions.interfaceandimplementations.InstructionOperandTypes;
import littlemangame.instructions.interfaceandimplementations.ParselessInstruction;
import littlemangame.littlemancommands.LittleManAction;
import littlemangame.littlemancommands.LittleManActionSequence;
import littlemangame.littlemancommands.LittleManCommands;
import littlemangame.littlemancommands.LittleManConditionalAction;
import littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManTest;
import littlemangame.word.BinaryWordOperation;
import littlemangame.word.UnaryWordOperation;
import littlemangame.word.Word;

import static littlemangame.instructions.SourceOperand.IMMEDIATE;
import static littlemangame.instructions.SourceOperand.MEMORY;
import static littlemangame.instructions.SourceOperand.REGISTER;
import static littlemangame.instructions.SourceOperand.REGISTER_INDIRECT;
import static littlemangame.instructions.interfaceandimplementations.InstructionOperandTypes.ADDRESS_ONLY;
import static littlemangame.instructions.interfaceandimplementations.InstructionOperandTypes.BOTH;
import static littlemangame.instructions.interfaceandimplementations.InstructionOperandTypes.DATA_ONLY;
import static littlemangame.instructions.interfaceandimplementations.InstructionOperandTypes.NEITHER;
import static littlemangame.word.BinaryWordOperation.SET;

/**
 *
 * @author brian
 */
public enum InstructionFromSet {

    //system, miscellaneous
    NO_OPERATION(0, NEITHER, LittleManCommands.nullAction),
    HALT(9, NEITHER, LittleManCommands.haltAction),
    //control
    UNCONDITIONAL_JUMP(10, DATA_ONLY, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    JUMP_IF_ZERO(11, DATA_ONLY, LittleManTest.ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    JUMP_IF_NOT_ZERO(12, DATA_ONLY, LittleManTest.NOT_ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    JUMP_IF_GREATER_THAN_ZERO(13, DATA_ONLY, LittleManTest.GREATER_THAN_ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    JUMP_IF_GREATER_THAN_OR_EQUAL_ZERO(14, DATA_ONLY, LittleManTest.GREATER_OR_EQUAL_TO_ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    JUMP_IF_LESS_THAN_OR_EQUAL_ZERO(15, DATA_ONLY, LittleManTest.LESS_OR_EQUAL_ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    JUMP_IF_LESS_THAN_ZERO(16, DATA_ONLY, LittleManTest.LESS_THAN_ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    //Input/Output
    PRINT_UNSIGNED(20, NEITHER, LittleManCommands.memorizeDataAtContainerAction(littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManWordContainer.REGISTER), LittleManCommands.getPrintUnsignedToOutputPanelAction()),
    INPUT(25, NEITHER, LittleManCommands.getGetDataFromInputPanelAction(), LittleManCommands.doBinaryOperationOnContainerAction(littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata.LittleManWordContainer.REGISTER, SET)),
    //data movement
    LOAD_IMMEDIATE(30, DATA_ONLY, SET, IMMEDIATE, DestinationOperand.REGISTER),
    LOAD_MEMORY(31, ADDRESS_ONLY, SET, MEMORY, DestinationOperand.REGISTER),
    LOAD_INDIRECT(32, NEITHER, SET, REGISTER_INDIRECT, DestinationOperand.REGISTER),
    STORE_IMMEDIATE_INDIRECT(35, DATA_ONLY, SET, REGISTER_INDIRECT, DestinationOperand.MEMORY),
    STORE_REGISTER_MEMORY(36, ADDRESS_ONLY, SET, REGISTER, DestinationOperand.MEMORY),
    STORE_IMMEDIATE_MEMORY(37, BOTH, SET, IMMEDIATE, DestinationOperand.MEMORY),
    //LOGIC
    COMPLEMENT_REGISTER(40, NEITHER, UnaryWordOperation.COMPLEMENT, DestinationOperand.REGISTER),
    COMPLEMENT_MEMORY(41, ADDRESS_ONLY, UnaryWordOperation.COMPLEMENT, DestinationOperand.MEMORY),
    COMPLEMENT_INDIRECT(42, NEITHER, UnaryWordOperation.COMPLEMENT, DestinationOperand.REGISER_INDIRECT),
    DIGITWISE_MAX_IMMEDIATE_REGISTER(50, NEITHER, BinaryWordOperation.DIGITWISE_MAX, IMMEDIATE, DestinationOperand.REGISTER),
    DIGITWISE_MAX_MEMORY_REGISTER(51, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MAX, MEMORY, DestinationOperand.REGISTER),
    DIGITWISE_MAX_IMMEDIATE_MEMORY(52, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MAX, IMMEDIATE, DestinationOperand.MEMORY),
    DIGITWISE_MAX_REGISTER_MEMORY(53, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MAX, REGISTER, DestinationOperand.MEMORY),
    DIGITWISE_MIN_IMMEDIATE_REGISTER(55, NEITHER, BinaryWordOperation.DIGITWISE_MIN, IMMEDIATE, DestinationOperand.REGISTER),
    DIGITWISE_MIN_MEMORY_REGISTER(56, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MIN, MEMORY, DestinationOperand.REGISTER),
    DIGITWISE_MIN_IMMEDIATE_MEMORY(57, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MIN, IMMEDIATE, DestinationOperand.MEMORY),
    DIGITWISE_MIN_REGISTER_MEMORY(58, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MIN, REGISTER, DestinationOperand.MEMORY),
    //digit twiddling
    LEFT_SHIFT_REGISTER(60, NEITHER, UnaryWordOperation.LEFT_SHIFT, DestinationOperand.REGISTER),
    LEFT_SHIFT_MEMORY(61, ADDRESS_ONLY, UnaryWordOperation.LEFT_SHIFT, DestinationOperand.MEMORY),
    LEFT_SHIFT_INDIRECT(62, NEITHER, UnaryWordOperation.LEFT_SHIFT, DestinationOperand.REGISER_INDIRECT),
    RIGHT_SHIFT_UNSIGNED_REGISTER(63, NEITHER, UnaryWordOperation.RIGHT_SHIFT_UNSIGNED, DestinationOperand.REGISTER),
    RIGHT_SHIFT_UNSIGNED_MEMORY(64, ADDRESS_ONLY, UnaryWordOperation.RIGHT_SHIFT_UNSIGNED, DestinationOperand.MEMORY),
    RIGHT_SHIFT_UNSIGNED_INDIRECT(65, NEITHER, UnaryWordOperation.RIGHT_SHIFT_UNSIGNED, DestinationOperand.REGISER_INDIRECT),
    RIGHT_SHIFT_SIGNED_REGISTER(66, NEITHER, UnaryWordOperation.RIGHT_SHIFT_SIGNED, DestinationOperand.REGISTER),
    RIGHT_SHIFT_SIGNED_MEMORY(67, ADDRESS_ONLY, UnaryWordOperation.RIGHT_SHIFT_SIGNED, DestinationOperand.MEMORY),
    RIGHT_SHIFT_SIGNED_INDIRECT(68, NEITHER, UnaryWordOperation.RIGHT_SHIFT_SIGNED, DestinationOperand.REGISER_INDIRECT),
    //arithmetic
    INCREMENT_REGISTER(70, NEITHER, UnaryWordOperation.INCREMENT, DestinationOperand.REGISTER),
    INCREMENT_MEMORY(71, ADDRESS_ONLY, UnaryWordOperation.INCREMENT, DestinationOperand.MEMORY),
    INCREMENT_INDIRECT(72, NEITHER, UnaryWordOperation.INCREMENT, DestinationOperand.REGISER_INDIRECT),
    DECREMENT_REGISTER(73, NEITHER, UnaryWordOperation.DECREMENT, DestinationOperand.REGISTER),
    DECREMENT_MEMORY(74, ADDRESS_ONLY, UnaryWordOperation.DECREMENT, DestinationOperand.MEMORY),
    DECREMENT_INDIRECT(75, NEITHER, UnaryWordOperation.DECREMENT, DestinationOperand.REGISER_INDIRECT),
    NEGATE_REGISTER(76, NEITHER, UnaryWordOperation.NEGATE, DestinationOperand.REGISTER),
    NEGATE_MEMORY(77, ADDRESS_ONLY, UnaryWordOperation.NEGATE, DestinationOperand.MEMORY),
    NEGATE_INDIRECT(78, NEITHER, UnaryWordOperation.NEGATE, DestinationOperand.REGISER_INDIRECT),
    ADD_IMMEDIATE_REGISTER(80, NEITHER, BinaryWordOperation.ADD, IMMEDIATE, DestinationOperand.REGISTER),
    ADD_MEMORY_REGISTER(81, ADDRESS_ONLY, BinaryWordOperation.ADD, MEMORY, DestinationOperand.REGISTER),
    ADD_IMMEDIATE_MEMORY(82, ADDRESS_ONLY, BinaryWordOperation.ADD, IMMEDIATE, DestinationOperand.MEMORY),
    ADD_REGISTER_MEMORY(83, ADDRESS_ONLY, BinaryWordOperation.ADD, REGISTER, DestinationOperand.MEMORY),
    SUBTRACT_IMMEDIATE_REGISTER(85, NEITHER, BinaryWordOperation.SUBTRACT, IMMEDIATE, DestinationOperand.REGISTER),
    SUBTRACT_MEMORY_REGISTER(86, ADDRESS_ONLY, BinaryWordOperation.SUBTRACT, MEMORY, DestinationOperand.REGISTER),
    SUBTRACT_IMMEDIATE_MEMORY(87, ADDRESS_ONLY, BinaryWordOperation.SUBTRACT, IMMEDIATE, DestinationOperand.MEMORY),
    SUBTRACT_REGISTER_MEMORY(88, ADDRESS_ONLY, BinaryWordOperation.SUBTRACT, REGISTER, DestinationOperand.MEMORY);
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

    //simple action
    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, LittleManAction... littleManAction) {
        this.opcode = Word.valueOfLastDigitsOfInteger(opcode);
        this.instruction = new ParselessInstruction(instructionOperandTypes, littleManAction);
    }

    //<editor-fold defaultstate="collapsed" desc="operation constructors">
    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, UnaryWordOperation wordOperation, DestinationOperand destinationOperand) {
        this(opcode, instructionOperandTypes, getLittleManAction(wordOperation, destinationOperand));
    }

    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, BinaryWordOperation wordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
        this(opcode, instructionOperandTypes, getLittleManAction(wordOperation, sourceOperand, destinationOperand));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="test constructors (with operations)">
    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, LittleManTest littleManTest, LittleManAction... littleManAction) {
        this(opcode, instructionOperandTypes, new LittleManConditionalAction(littleManTest, littleManAction));
    }

    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, LittleManTest littleManTest, UnaryWordOperation wordOperation, DestinationOperand destinationOperand) {
        this(opcode, instructionOperandTypes, littleManTest, getLittleManAction(wordOperation, destinationOperand));
    }

    private InstructionFromSet(int opcode, InstructionOperandTypes instructionOperandTypes, LittleManTest littleManTest, BinaryWordOperation wordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
        this(opcode, instructionOperandTypes, littleManTest, getLittleManAction(wordOperation, sourceOperand, destinationOperand));
    }
    //</editor-fold>

    public Word getOpcode() {
        return opcode;
    }

    public Instruction getInstruction() {
        return instruction.getResetCopy();
    }

    static private LittleManAction getLittleManAction(UnaryWordOperation unaryWordOperation, DestinationOperand destinationOperand) {
        return new LittleManActionSequence(destinationOperand.getPreparationAction(), LittleManCommands.doUnaryOperationOnContainerAction(destinationOperand.getDestinationContainer(), unaryWordOperation));
    }

    static private LittleManAction getLittleManAction(BinaryWordOperation binaryWordOperation, SourceOperand sourceOperand, DestinationOperand destinationOperand) {
        return new LittleManActionSequence(sourceOperand.getOperandMemorizer(), destinationOperand.getPreparationAction(), LittleManCommands.doBinaryOperationOnContainerAction(destinationOperand.getDestinationContainer(), binaryWordOperation));
    }

}
