/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands;

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

    public void memorizeData(Word data) {
        this.rememberedData = data;
        isRememberingData = true;
    }

    public void memorizeAddress(Word address) {
        rememberedAddress = address;
        isRememberingAddress = true;
    }

    public void clearDataMemory() {
        isRememberingData = false;
    }

    public void clearAddressMemory() {
        isRememberingAddress = false;
    }

    public void clearMemory() {
        clearAddressMemory();
        clearDataMemory();
    }

    public boolean isRememberingData() {
        return isRememberingData;
    }

    public boolean isRememberingAddress() {
        return isRememberingAddress;

    }

    public Word getRememberedAddress() {
        return rememberedAddress;
    }

    public Word getRememberedData() {
        return rememberedData;
    }

}
