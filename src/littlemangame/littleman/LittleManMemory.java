/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman;

import littlemangame.word.Word;

/**
 *
 * @author brian
 */
public class LittleManMemory {

    private Word rememberedAddress;
    private boolean isRememberingAddress = false;
    private Word rememberedData;
    private boolean isRememberingData = false;

    public LittleManMemory() {
    }

    void memorizeData(Word data) {
        this.rememberedData = data;
        isRememberingData = true;
    }

    void memorizeAddress(Word address) {
        rememberedAddress = address;
        isRememberingAddress = true;
    }

    void clearDataMemory() {
        isRememberingData = false;
    }

    void clearAddressMemory() {
        isRememberingAddress = false;
    }

    void clearMemory() {
        clearAddressMemory();
        clearDataMemory();
    }

    boolean isRememberingData() {
        return isRememberingData;
    }

    boolean isRememberingAddress() {
        return isRememberingAddress;

    }

    public Word getRememberedAddress() {
        return rememberedAddress;
    }

    public Word getRememberedData() {
        return rememberedData;
    }

}
