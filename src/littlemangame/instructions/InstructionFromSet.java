/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.instructions;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import littlemangame.instructions.interfaceandimplementations.Instruction;
import littlemangame.instructions.interfaceandimplementations.InstructionOperandTypes;
import littlemangame.instructions.interfaceandimplementations.ParselessInstruction;
import littlemangame.littleman.littlemanutilities.littlemandata.LittleManTest;
import littlemangame.littlemancommands.LittleManCommands.LittleManAction;
import littlemangame.littlemancommands.LittleManCommands.LittleManActionSequence;
import littlemangame.littlemancommands.LittleManCommands.LittleManCommands;
import littlemangame.littlemancommands.LittleManCommands.LittleManConditionalAction;
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
    JUMP_IF_GREATER_THAN_OR_EQUAL_TO_ZERO(14, DATA_ONLY, LittleManTest.GREATER_OR_EQUAL_TO_ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    JUMP_IF_LESS_THAN_OR_EQUAL_TO_ZERO(15, DATA_ONLY, LittleManTest.LESS_OR_EQUAL_ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    JUMP_IF_LESS_THAN_ZERO(16, DATA_ONLY, LittleManTest.LESS_THAN_ZERO, SET, IMMEDIATE, DestinationOperand.INSTRUCTION_POINTER),
    //Input/Output
    PRINT_UNSIGNED(20, NEITHER, LittleManCommands.memorizeDataAtContainerAction(littlemangame.littleman.littlemanutilities.littlemandata.LittleManWordContainer.REGISTER), LittleManCommands.getPrintUnsignedToOutputPanelAction()),
    INPUT(25, NEITHER, LittleManCommands.getGetDataFromInputPanelAction(), LittleManCommands.doBinaryOperationOnContainerAction(littlemangame.littleman.littlemanutilities.littlemandata.LittleManWordContainer.REGISTER, SET)),
    //data movement
    LOAD_IMMEDIATE(30, DATA_ONLY, SET, IMMEDIATE, DestinationOperand.REGISTER),
    LOAD_MEMORY(31, ADDRESS_ONLY, SET, MEMORY, DestinationOperand.REGISTER),
    LOAD_INDIRECT(32, NEITHER, SET, REGISTER_INDIRECT, DestinationOperand.REGISTER),
    STORE_IMMEDIATE_INDIRECT(35, DATA_ONLY, SET, REGISTER_INDIRECT, DestinationOperand.MEMORY),
    STORE_REGISTER_MEMORY(36, ADDRESS_ONLY, SET, REGISTER, DestinationOperand.MEMORY),
    STORE_IMMEDIATE_MEMORY(37, BOTH, SET, IMMEDIATE, DestinationOperand.MEMORY),
    //LOGIC
    COMPLEMENT_WORKSHEET(40, NEITHER, UnaryWordOperation.COMPLEMENT, DestinationOperand.REGISTER),
    COMPLEMENT_MEMORY(41, ADDRESS_ONLY, UnaryWordOperation.COMPLEMENT, DestinationOperand.MEMORY),
    COMPLEMENT_INDIRECT(42, NEITHER, UnaryWordOperation.COMPLEMENT, DestinationOperand.REGISER_INDIRECT),
    DIGITWISE_MAX_IMMEDIATE_REGISTER(50, DATA_ONLY, BinaryWordOperation.DIGITWISE_MAX, IMMEDIATE, DestinationOperand.REGISTER),
    DIGITWISE_MAX_MEMORY_REGISTER(51, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MAX, MEMORY, DestinationOperand.REGISTER),
    DIGITWISE_MAX_IMMEDIATE_MEMORY(52, BOTH, BinaryWordOperation.DIGITWISE_MAX, IMMEDIATE, DestinationOperand.MEMORY),
    DIGITWISE_MAX_REGISTER_MEMORY(53, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MAX, REGISTER, DestinationOperand.MEMORY),
    DIGITWISE_MIN_IMMEDIATE_REGISTER(55, DATA_ONLY, BinaryWordOperation.DIGITWISE_MIN, IMMEDIATE, DestinationOperand.REGISTER),
    DIGITWISE_MIN_MEMORY_REGISTER(56, ADDRESS_ONLY, BinaryWordOperation.DIGITWISE_MIN, MEMORY, DestinationOperand.REGISTER),
    DIGITWISE_MIN_IMMEDIATE_MEMORY(57, BOTH, BinaryWordOperation.DIGITWISE_MIN, IMMEDIATE, DestinationOperand.MEMORY),
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
    ADD_IMMEDIATE_TO_REGISTER(80, NEITHER, BinaryWordOperation.ADD, IMMEDIATE, DestinationOperand.REGISTER),
    ADD_MEMORY_TO_REGISTER(81, ADDRESS_ONLY, BinaryWordOperation.ADD, MEMORY, DestinationOperand.REGISTER),
    ADD_IMMEDIATE_TO_MEMORY(82, ADDRESS_ONLY, BinaryWordOperation.ADD, IMMEDIATE, DestinationOperand.MEMORY),
    ADD_REGISTER_TO_MEMORY(83, ADDRESS_ONLY, BinaryWordOperation.ADD, REGISTER, DestinationOperand.MEMORY),
    SUBTRACT_IMMEDIATE_FROM_REGISTER(85, NEITHER, BinaryWordOperation.SUBTRACT, IMMEDIATE, DestinationOperand.REGISTER),
    SUBTRACT_MEMORY_FROM_REGISTER(86, ADDRESS_ONLY, BinaryWordOperation.SUBTRACT, MEMORY, DestinationOperand.REGISTER),
    SUBTRACT_IMMEDIATE_FROM_MEMORY(87, ADDRESS_ONLY, BinaryWordOperation.SUBTRACT, IMMEDIATE, DestinationOperand.MEMORY),
    SUBTRACT_REGISTER_FROM_MEMORY(88, ADDRESS_ONLY, BinaryWordOperation.SUBTRACT, REGISTER, DestinationOperand.MEMORY);
    static private final Map<Word, InstructionFromSet> instructionMap = new HashMap<>();
    private static EnumMap<InstructionFromSet, String> instructionDescriptions;

    static {
        for (InstructionFromSet instructionFromSet : InstructionFromSet.values()) {
            instructionMap.put(instructionFromSet.getOpcode(), instructionFromSet);
        }
        makeInstructionDescriptions();
    }

    static void makeInstructionDescriptions() {
        instructionDescriptions = new EnumMap<>(InstructionFromSet.class);
        putDescriptionString(NO_OPERATION, "The little man does nothing to complete this instruction. He immediately begins the next instruction cycle.");
        putDescriptionString(HALT, "The little man stops working on the task completely. No new instructions will be done.");
        putDescriptionString(UNCONDITIONAL_JUMP, "The little man sets his page number equal to the data operand.");
        putDescriptionString(JUMP_IF_NOT_ZERO, "If the worksheet does not does not have 00 on it, the little man sets his page number equal to the data operand.");
        putDescriptionString(JUMP_IF_GREATER_THAN_ZERO, "If the worksheet has a word between 01 and 49 inclusive on it, the little man sets his page number equal to the data operand.");
        putDescriptionString(JUMP_IF_GREATER_THAN_OR_EQUAL_TO_ZERO, "If the worksheet has a word between 00 and 49 inclusive on it, the little man sets his page number equal to the data operand.");
        putDescriptionString(JUMP_IF_LESS_THAN_OR_EQUAL_TO_ZERO, "If the worksheet has a word between 50 and 99 inclusive or 00 on it, the little man sets his page number equal to the data operand.");
        putDescriptionString(JUMP_IF_LESS_THAN_ZERO, "If the worksheet has a word between 50 and 99 inclusive on it, the little man sets his page number equal to the data operand.");
        putDescriptionString(PRINT_UNSIGNED, "The little man enters the word written on the worksheet into the output. The word written on the worksheet is unchanged.");
        putDescriptionString(INPUT, "The little man goes to the input device and waits for input. Once input is given he copies the word onto the worksheet. Any word previously written on the worksheet is lost.");
        putDescriptionString(LOAD_IMMEDIATE, "The little man copies the data operand onto the worksheet. Any word previously written on the worksheet is lost.");
        putDescriptionString(LOAD_MEMORY, "The little man reads the word written in the notebook on the page specified by the page number operand. He then copies this word onto the worksheet. Any word previously written on the worksheet is lost.");
        putDescriptionString(LOAD_INDIRECT, "The little man reads the word written in on the worksheet. The little man then read the word at the notebook on the page specified by this word. He then copies this word from the notebook onto the worksheet. Any word previously written on the worksheet is lost.");
        putDescriptionString(STORE_IMMEDIATE_INDIRECT, "The little man reads the word on the worksheet and writes the data operand into the notebook on the page given by the the word from the worksheet.");
        putDescriptionString(STORE_REGISTER_MEMORY, "The little man reads the word on the worksheet and copies this word into the notebook on the page given by the page number operand.");
        putDescriptionString(STORE_IMMEDIATE_MEMORY, "The little man writes the data operand in the notebook on the page given by the page number operand.");
        putDescriptionString(COMPLEMENT_WORKSHEET, "The little man replaces each digit on the worksheet with its complement. 0 becomes 9, 3 becomes 6, 5 becomes 4, etc.");
        putDescriptionString(COMPLEMENT_MEMORY, "The little man replaces with its complement each digit on the notebook page given by the page number operand. 0 becomes 9, 3 becomes 6, 5 becomes 4, etc.");
        putDescriptionString(COMPLEMENT_INDIRECT, "The little man replaces with its complement each digit on the notebook page written on the worksheet. 0 becomes 9, 3 becomes 6, 5 becomes 4, etc.");
        putDescriptionString(DIGITWISE_MAX_IMMEDIATE_REGISTER, "The little man replaces each digit on the worksheet with the maximum of that digit with the corresponding digit of the data operand.");
        putDescriptionString(DIGITWISE_MAX_MEMORY_REGISTER, "The little man replaces each digit on the worksheet with the maximum of that digit with the corresponding digit on the page given by the page number operand.");
        putDescriptionString(DIGITWISE_MAX_IMMEDIATE_MEMORY, "The little man replaces each digit on the notebook page given by the page number operand with the maximum of that digit with the corresponding digit of the data operand.");
        putDescriptionString(DIGITWISE_MAX_REGISTER_MEMORY, "The little man replaces each digit on the notebook page given by the page number operand with the maximum of that digit with the corresponding digit of the number on the worksheet.");
        putDescriptionString(DIGITWISE_MIN_IMMEDIATE_REGISTER, "The little man replaces each digit on the worksheet with the minimum of that digit with the corresponding digit of the data operand.");
        putDescriptionString(DIGITWISE_MIN_MEMORY_REGISTER, "The little man replaces each digit on the worksheet with the minimum of that digit with the corresponding digit on the page given by the page number operand.");
        putDescriptionString(DIGITWISE_MIN_IMMEDIATE_MEMORY, "The little man replaces each digit on the notebook page given by the page number operand with the minimum of that digit with the corresponding digit of the data operand.");
        putDescriptionString(DIGITWISE_MIN_REGISTER_MEMORY, "The little man replaces each digit on the notebook page given by the page number operand with the minimum of that digit with the corresponding digit of the number on the worksheet.");
        putDescriptionString(LEFT_SHIFT_REGISTER, "The little man moves each digit on the worksheet to the left. The rightmost digit becomes zero. For example 12 becomes 20.");
        putDescriptionString(LEFT_SHIFT_MEMORY, "The little man moves each digit on the notebook page given by the notebook page operand to the left. The rightmost digit becomes zero. For example 12 becomes 20.");
        putDescriptionString(LEFT_SHIFT_INDIRECT, "The little man moves each digit on the notebook page given by the register to the left. The rightmost digit becomes zero. For example 12 becomes 20.");
        putDescriptionString(RIGHT_SHIFT_UNSIGNED_REGISTER, "The little man moves each digit on the worksheet to the right. The leftmost digit becomes zero. For example 12 becomes 01.");
        putDescriptionString(RIGHT_SHIFT_UNSIGNED_MEMORY, "The little man moves each digit on the notebook page given by the notebook page operand to the right. The leftmost digit becomes zero. For example 12 becomes 01.");
        putDescriptionString(RIGHT_SHIFT_UNSIGNED_INDIRECT, "The little man moves each digit on the notebook page given by the register to the right. The leftmost digit becomes zero. For example 12 becomes 01.");
        putDescriptionString(RIGHT_SHIFT_SIGNED_REGISTER, "The little man moves each digit on the worksheet to the right. The leftmost digit becomes 0 or 9 depending on if the leftmost digit was original four or less. For example 12 becomes 01, but 53 becomes 95.");
        putDescriptionString(RIGHT_SHIFT_SIGNED_MEMORY, "The little man moves each digit on the notebook page given by the notebook page operand to the right. The leftmost digit becomes 0 or 9 depending on if the leftmost digit was original four or less. For example 12 becomes 01, but 53 becomes 95.");
        putDescriptionString(RIGHT_SHIFT_SIGNED_INDIRECT, "The little man moves each digit on the notebook page given by the register to the right. The leftmost digit becomes 0 or 9 depending on if the leftmost digit was original four or less. For example 12 becomes 01, but 53 becomes 95.");
        putDescriptionString(INCREMENT_REGISTER, "The little man increases the number written on the worksheet by one. If the number was originally the maximum number, it goes back to zero.");
        putDescriptionString(INCREMENT_MEMORY, "The little man increases by one the number written on the notebook page given by the notebook page operand. If the number was originally the maximum number, it goes back to zero.");
        putDescriptionString(INCREMENT_INDIRECT, "The little man increases by one the number written on the notebook page given by the worksheet. If the number was originally the maximum number, it goes back to zero.");
        putDescriptionString(DECREMENT_REGISTER, "The little man decreases the number written on the worksheet by one. If the number was originally zero, it becomes the maximum number.");
        putDescriptionString(DECREMENT_MEMORY, "The little man decreases by one the number written on the notebook page given by the notebook page operand. If the number was originally zero, it becomes the maximum number.");
        putDescriptionString(DECREMENT_INDIRECT, "The little man decreases by one the number written on the notebook page given by the worksheet. If the number was originally zero, it becomes the maximum number.");
        putDescriptionString(NEGATE_REGISTER, "The little man replaces the number on the worksheet with its opposite. This has the same effect as taking the digitwise complement of the number and adding one.");
        putDescriptionString(NEGATE_MEMORY, "The little man replaces with its opposite the number written on the notebook page given by the notebook page operand. This has the same effect as taking the digitwise complement of the number and adding one.");
        putDescriptionString(NEGATE_INDIRECT, "The little man replaces with its opposite  the number written on the notebook page given by the worksheet. This has the same effect as taking the digitwise complement of the number and adding one.");
        putDescriptionString(ADD_IMMEDIATE_TO_REGISTER, "The little man adds the data operand to the number written on the worksheet. Only the last two digits are kept.");//get rid of magic number "two"
        putDescriptionString(ADD_MEMORY_TO_REGISTER, "The little man adds the number on the notebook page given by the notebook page operand to the number written on the worksheet. Only the last two digits are kept.");//get rid of magic number "two"
        putDescriptionString(ADD_IMMEDIATE_TO_MEMORY, "The little man adds the data operand to the number on the notebook page given by the notebook page operand. Only the last two digits are kept.");//get rid of magic number "two"
        putDescriptionString(ADD_REGISTER_TO_MEMORY, "The little man adds the number written on the worksheet to the number on the notebook page given by the notebook page operand. Only the last two digits are kept.");//get rid of magic number "two"
        putDescriptionString(SUBTRACT_IMMEDIATE_FROM_REGISTER, "The little man subtracts the data operand from the number written on the worksheet. Only the last two digits are kept.");//get rid of magic number "two"
        putDescriptionString(SUBTRACT_MEMORY_FROM_REGISTER, "The little man subtracts the number on the notebook page given by the notebook page operand from the number written on the worksheet. Only the last two digits are kept.");//get rid of magic number "two"
        putDescriptionString(SUBTRACT_IMMEDIATE_FROM_MEMORY, "The little man subtracts the data operand from the number on the notebook page given by the notebook page operand. Only the last two digits are kept.");//get rid of magic number "two"
        putDescriptionString(SUBTRACT_REGISTER_FROM_MEMORY, "The little man subtracts the number written on the worksheet from the number on the notebook page given by the notebook page operand. Only the last two digits are kept.");//get rid of magic number "two"

    }

    private static void putDescriptionString(InstructionFromSet instructionFromSet, String description) {
        instructionDescriptions.put(instructionFromSet, makeDescriptionString(instructionFromSet, description));
    }

    static String makeDescriptionString(InstructionFromSet instructionFromSet, String instructionDescription) {
        StringBuilder descriptionBuilder = new StringBuilder();
        descriptionBuilder.append(instructionFromSet.toString()).append("\n")
                .append("     Code: ").append(instructionFromSet.getOpcode().toString()).append("\n")
                .append("     Takes data operand: ").append(instructionFromSet.instruction.isDataOperandNeeded() ? "yes" : "no").append("\n")
                .append("     Takes address operand: ").append(instructionFromSet.instruction.isAddressOperandNeeded() ? "yes" : "no").append("\n")
                .append("     Description: ").append(instructionDescription).append("\n");
        return descriptionBuilder.toString();
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

    public String getDescription() {
        return instructionDescriptions.get(this);
    }

    @Override
    public String toString() {
        final String lowerCaseString = name().replace("_", " ").toLowerCase();
        return lowerCaseString.substring(0, 1).toUpperCase() + lowerCaseString.substring(1);
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
