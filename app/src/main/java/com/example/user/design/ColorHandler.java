package com.example.user.design;

/**
 * Created by user on 23/08/2016.
 */
public class ColorHandler {

    public ColorHandler(){}

    public void getColor(String colorName){
        int colorRGB;
        switch (colorName){
            //find hexa equiv and change values then show every wire part with it's color
            case "NULL" : colorRGB = 12303291;
                break;
            case "W" : colorRGB = 16777215;
                break;
            case "Y" : colorRGB = 5636095;
                break;
            case "O" : colorRGB = 43775;
                break;
            case "R" : colorRGB = 255;
                break;
            case "V" : colorRGB = 11141205;
                break;
            case "L" : colorRGB = 11141120;
                break;
            case "LG" : colorRGB = 65280;
                break;
            case "GR" : colorRGB = 11184895;
                break;
            case "BR" : colorRGB = 21930;
                break;
            case "B" : colorRGB = 0;
                break;
            case "P" : colorRGB = 11184895;
                break;
            case "SB" : colorRGB = 16755200;
                break;
            case "G" : colorRGB = 43520;
                break;
            case "BK" : colorRGB = 0;
                break;
            case "WH" : colorRGB = 16777215;
                break;
            case "BN" : colorRGB = 16512;
                break;
            case "GN" : colorRGB = 32768;
                break;
            case "VT" : colorRGB = 8388736;
                break;
            case "BU" : colorRGB = 10485760;
                break;
            case "GY" : colorRGB = 12632256;
                break;
            case "RD" : colorRGB = 255;
                break;
            case "OG" : colorRGB = 33023;
                break;
            case "YE" : colorRGB = 65535;
                break;
            default: colorRGB = 0; break;
        }
    }


}
