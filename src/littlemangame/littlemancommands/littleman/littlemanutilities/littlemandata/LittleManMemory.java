/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littlemancommands.littleman.littlemanutilities.littlemandata;

import java.awt.Color;
import java.awt.Graphics;
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

    public Word useRememberedAddress() {
        final Word word = rememberedAddress;
        clearAddressMemory();
        return word;
    }

    public Word getRememberedAddress() {
        return rememberedAddress;
    }

    public Word useRememberedData() {
        final Word word = rememberedData;
        clearDataMemory();
        return word;
    }

    public void draw(Graphics graphics, int x, int y) {
        if (isRememberingData()) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.red);
            graphics.drawRect(x - 15, y - 22, 22, 20);
            graphics.drawString(rememberedData.toString(), x - 12, y - 5);
            graphics.setColor(color);
        }
        if (isRememberingAddress()) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.blue);
            graphics.drawRect(x + 15, y - 22, 22, 20);
            graphics.drawString(rememberedAddress.toString(), x + 18, y - 5);
            graphics.setColor(color);
        }
    }

}
