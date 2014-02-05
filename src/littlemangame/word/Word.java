/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.word;

/**
 *
 * @author brian
 */
public class Word implements Comparable<Word> {

    static private final int NUM_WORDS = 100;
    static private final int MIN_SIGNED = NUM_WORDS / 2;
    static private final int MAX_SIGNED = MIN_SIGNED - 1;
    static private final int MAX_WORD = NUM_WORDS - 1;
    static private final int MIN_WORD = 0;
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

    private final int value;

    private Word(int value) {
        value = value % NUM_WORDS;
        if (value < 0) {
            value += NUM_WORDS;
        }
        this.value = value;
    }

    public Word incrementedWord() {
        return new Word(getValue() + 1);
    }

    public int getValue() {
        return value;
    }

    public int getSignedValue() {
        if (value <= MAX_SIGNED) {
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
        if (this.value != other.value) {
            return false;
        }
        return true;
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
