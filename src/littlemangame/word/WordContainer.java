/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.word;

/**
 *
 * @author brian
 */
public class WordContainer {

    private Word word;

    public WordContainer(Word word) {
        this.word = word;
    }

    public void increment() {
        word = word.incrementedWord();
    }

    public void decrement() {
        word = word.decrementedWord();
    }

    public void incrementBy(Word summand) {
        word = word.plus(summand);
    }

    public void decrementBy(Word subtrahend) {
        word = word.minus(subtrahend);
    }

    public void complement() {
        word = word.getComplement();
    }

    public void invertSign() {
        word = word.getOpposite();
    }

    public void leftShift() {
        word = word.leftShift();
    }

    public void leftShift(Word word) {
        this.word = this.word.leftShift(word);
    }

    public void rightShiftUnsigned() {
        word = word.rightShiftUnsigned();
    }

    public void rightShiftUnsigned(Word word) {
        this.word = this.word.rightShiftUnsigned(word);
    }

    public void rightShiftSigned() {
        word = word.rightShiftSigned();
    }

    public void rightShiftSigned(Word word) {
        this.word = this.word.rightShiftSigned(word);
    }

    public Word digitwiseMax(Word word) {
        return this.word.digitwiseMax(word);
    }

    public Word digitwiseMin(Word word) {
        return this.word.digitwiseMin(word);
    }

    public Word unsignedMax(Word word) {
        return this.word.unsignedMax(word);
    }

    public Word unsignedMin(Word word) {
        return this.word.unsignedMin(word);
    }

    public boolean isNegative() {
        return word.isNegative();
    }

    public int getValue() {
        return word.getValue();
    }

    public int getSignedValue() {
        return word.getSignedValue();
    }

    public int compareTo(Word t) {
        return word.compareTo(t);
    }

    public int compareToUnsigned(Word t) {
        return word.compareToUnsigned(t);
    }

    public int compareToSigned(Word t) {
        return word.compareToSigned(t);
    }

    @Override
    public String toString() {
        return word.toString();
    }

    public String toStringUnsigned() {
        return word.toStringUnsigned();
    }

    public String toStringSigned() {
        return word.toStringSigned();
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

}
