/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.word;

import java.util.Iterator;

/**
 *
 * @author brian
 */
public class Word implements Comparable<Word> {

    static private final int NUM_WORDS = 100;
    static private final int NUM_DIGITS = 2;
    static private final int BASE = 10;
    static private final Word MIN_SIGNED = new Word(NUM_WORDS / 2);
    static private final Word MAX_SIGNED = MIN_SIGNED.decrementedWord();
    static private final Word MAX_WORD = new Word(NUM_WORDS - 1);
    static public final Word ZERO_WORD = new Word(0);

    static public Word valueOfLastDigitsOfInteger(int value) {
        return new Word(value);
    }

    static public Word zeroWord() {
        return new Word(0);
    }

    static private String getDoubleDigitString(int number) {
        return String.format("%02d", number);
    }

    static public Iterator<Word> getIterator() {
        return new Iterator<Word>() {

            private Word word = MAX_WORD;
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public Word next() {
                word = word.incrementedWord();
                hasNext = !word.equals(Word.MAX_WORD);
                return word;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
    }

    private final int value;

    private Word(int value) {
        value = value % NUM_WORDS;
        if (value < 0) {
            value += NUM_WORDS;
        }
        this.value = value;
    }

    public Word(Word word) {
        value = word.getValue();
    }

    //<editor-fold defaultstate="collapsed" desc="arithmetic and logic">
    public Word incrementedWord() {
        return new Word(getValue() + 1);
    }

    public Word decrementedWord() {
        return new Word(getValue() - 1);
    }

    public Word plus(Word summand) {
        return new Word(getValue() + summand.getValue());
    }

    public Word minus(Word subtrahend) {
        return new Word(getValue() - subtrahend.getValue());
    }

    public Word getComplement() {
        return MAX_WORD.minus(this);
    }

    public Word getOpposite() {
        return getComplement().incrementedWord();
    }

    public Word leftShift() {
        return new Word(getValue() * 10);
    }

    public Word leftShift(Word word) {
        Word shiftedWord = new Word(this);
        while (word.compareTo(ZERO_WORD) > 0 && !shiftedWord.equals(ZERO_WORD)) {
            word = word.decrementedWord();
            shiftedWord = shiftedWord.leftShift();
        }
        return shiftedWord;
    }

    public Word rightShiftUnsigned() {
        return new Word(getValue() / 10);
    }

    public Word rightShiftUnsigned(Word word) {
        Word shiftedWord = new Word(this);
        while (word.compareTo(ZERO_WORD) > 0 && !shiftedWord.equals(ZERO_WORD)) {
            word = word.decrementedWord();
            shiftedWord = shiftedWord.rightShiftUnsigned();
        }
        return shiftedWord;
    }

    public Word rightShiftSigned() {
        Word returnValue = rightShiftUnsigned();
        if (isNegative()) {
            returnValue = returnValue.plus(new Word(90));
        }
        return returnValue;
    }

    public Word rightShiftSigned(Word word) {
        Word shiftedWord = new Word(this);
        while (word.compareTo(ZERO_WORD) > 0 && !shiftedWord.equals(ZERO_WORD) && !shiftedWord.equals(MAX_WORD)) {
            word = word.decrementedWord();
            shiftedWord = shiftedWord.rightShiftSigned();
        }
        return shiftedWord;
    }

    public Word digitwiseMax(Word word) {
        Word returnWord = ZERO_WORD;
        for (int i = 0; i < NUM_DIGITS; i++) {
            returnWord = returnWord.plus(maskExceptDigit(i).unsignedMax(maskExceptDigit(i)));
        }
        return returnWord;
    }

    public Word digitwiseMin(Word word) {
        Word returnWord = ZERO_WORD;
        for (int i = 0; i < NUM_DIGITS; i++) {
            returnWord = returnWord.plus(maskExceptDigit(i).unsignedMin(maskExceptDigit(i)));
        }
        return returnWord;
    }

    private Word maskExceptDigit(int i) {
        Word digitWord = new Word(i);
        return rightShiftUnsigned(digitWord).maskExceptZerothDigit().leftShift(digitWord);
    }

    private Word maskExceptZerothDigit() {
        return new Word(getValue() % BASE);
    }

    public Word unsignedMax(Word word) {
        return compareTo(word) > -1 ? this : word;
    }

    public Word unsignedMin(Word word) {
        return compareTo(word) > -1 ? word : this;
    }

    //</editor-fold>
    public boolean isNegative() {
        return compareToSigned(ZERO_WORD) == -1;
    }

    public int getValue() {
        return value;
    }

    public int getSignedValue() {
        if (compareTo(MAX_SIGNED) <= 0) {
            return value;
        } else {
            return value - NUM_WORDS;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="compare equals hashcode">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        return this.value == other.value;
    }

    @Override
    public int compareTo(Word t) {
        return Integer.compare(value, t.value);
    }

    public int compareToUnsigned(Word t) {
        return compareTo(t);
    }

    public int compareToSigned(Word t) {
        return Integer.compare(getSignedValue(), t.getSignedValue());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toStrings">
    @Override
    public String toString() {
        return getDoubleDigitString(getValue());
    }

    public String toStringUnsigned() {
        return toString();
    }

    public String toStringSigned() {
        return getDoubleDigitString(getSignedValue());
    }
    //</editor-fold>

}
