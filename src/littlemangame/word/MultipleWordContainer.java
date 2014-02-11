/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.word;

/**
 *
 * @author brian
 */
public abstract class MultipleWordContainer<T> {

    public void increment(T t) {
        setWord(t, getWord(t).incrementedWord());
    }

    public void decrement(T t) {
        setWord(t, getWord(t).decrementedWord());
    }

    public void incrementBy(T t, Word summand) {
        setWord(t, getWord(t).plus(summand));
    }

    public void decrementBy(T t, Word subtrahend) {
        setWord(t, getWord(t).minus(subtrahend));
    }

    public void complement(T t) {
        setWord(t, getWord(t).getComplement());
    }

    public void invertSign(T t) {
        setWord(t, getWord(t).getOpposite());
    }

    public void leftShift(T t) {
        setWord(t, getWord(t).leftShift());
    }

    public void leftShift(T t, Word word) {
        setWord(t, getWord(t).leftShift(word));
    }

    public void rightShiftUnsigned(T t) {
        setWord(t, getWord(t).rightShiftUnsigned());
    }

    public void rightShiftUnsigned(T t, Word word) {
        setWord(t, getWord(t).rightShiftUnsigned(word));
    }

    public void rightShiftSigned(T t) {
        setWord(t, getWord(t).rightShiftSigned());
    }

    public void rightShiftSigned(T t, Word word) {
        setWord(t, getWord(t).rightShiftSigned(word));
    }

    public Word digitwiseMax(T t, Word word) {
        return getWord(t).digitwiseMax(word);
    }

    public Word digitwiseMin(T t, Word word) {
        return getWord(t).digitwiseMin(word);
    }

    public Word unsignedMax(T t, Word word) {
        return getWord(t).digitwiseMin(word);
    }

    public Word unsignedMin(T t, Word word) {
        return getWord(t).unsignedMin(word);
    }

    public boolean isNegative(T t) {
        return getWord(t).isNegative();
    }

    public int getValue(T t) {
        return getWord(t).getValue();
    }

    public int getSignedValue(T t) {
        return getWord(t).getSignedValue();
    }

    public int compareTo(T t, Word word) {
        return getWord(t).compareTo(word);
    }

    public int compareToUnsigned(T t, Word word) {
        return getWord(t).compareToUnsigned(word);
    }

    public int compareToSigned(T t, Word word) {
        return getWord(t).compareToSigned(word);
    }

    public String toString(T t) {
        return getWord(t).toString();
    }

    public String toStringUnsigned(T t) {
        return getWord(t).toStringUnsigned();
    }

    public String toStringSigned(T t) {
        return getWord(t).toStringSigned();
    }

    public abstract Word getWord(T t);

    public abstract void setWord(T t, Word word);

}
