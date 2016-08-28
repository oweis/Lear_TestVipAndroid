package com.example.user.Design;

/**
 * Created by user on 23/08/2016.
 */
public class Color {

    public Color() {
    }
    //TODO create function getColorHex so we can use it to modify background to make testing easier

    //TODO update color name with the new ones
    public void getColorRGB(String colorName) {
        int colorRGB;
        switch (colorName) {
            case "NULL":
                colorRGB = 12303291;
                break;
            case "W":
                colorRGB = 16777215;
                break;
            case "Y":
                colorRGB = 5636095;
                break;
            case "O":
                colorRGB = 43775;
                break;
            case "R":
                colorRGB = 255;
                break;
            case "V":
                colorRGB = 11141205;
                break;
            case "L":
                colorRGB = 11141120;
                break;
            case "LG":
                colorRGB = 65280;
                break;
            case "GR":
                colorRGB = 11184895;
                break;
            case "BR":
                colorRGB = 21930;
                break;
            case "B":
                colorRGB = 0;
                break;
            case "P":
                colorRGB = 11184895;
                break;
            case "SB":
                colorRGB = 16755200;
                break;
            case "G":
                colorRGB = 43520;
                break;
            case "BK":
                colorRGB = 0;
                break;
            case "WH":
                colorRGB = 16777215;
                break;
            case "BN":
                colorRGB = 16512;
                break;
            case "GN":
                colorRGB = 32768;
                break;
            case "VT":
                colorRGB = 8388736;
                break;
            case "BU":
                colorRGB = 10485760;
                break;
            case "GY":
                colorRGB = 12632256;
                break;
            case "RD":
                colorRGB = 255;
                break;
            case "OG":
                colorRGB = 33023;
                break;
            case "YE":
                colorRGB = 65535;
                break;
            default:
                colorRGB = 0;
                break;
        }
    }

    public String getColorName(String colorName) {
        String colorCompleteName;
        switch (colorName) {
            case "NULL":
                colorCompleteName = "Null";
                break;
            case "W":
                colorCompleteName = "White";
                break;
            case "Y":
                colorCompleteName = "Yellow";
                break;
            case "O":
                colorCompleteName = "Orange";
                break;
            case "R":
                colorCompleteName = "Red";
                break;
            case "V":
                colorCompleteName = "Purple";
                break;
            case "L":
                colorCompleteName = "Blue";
                break;
            case "LG":
                colorCompleteName = "Light green";
                break;
            case "GR":
                colorCompleteName = "Grey";
                break;
            case "BR":
                colorCompleteName = "Brown";
                break;
            case "B":
                colorCompleteName = "Black";
                break;
            case "P":
                colorCompleteName = "Pink";
                break;
            case "SB":
                colorCompleteName = "Sky Blue";
                break;
            case "G":
                colorCompleteName = "Green";
                break;
            case "BK":
                colorCompleteName = "Black1";
                break;
            case "WH":
                colorCompleteName = "White1";
                break;
            case "BN":
                colorCompleteName = "Brown1";
                break;
            case "GN":
                colorCompleteName = "Green1";
                break;
            case "VT":
                colorCompleteName = "Violet1";
                break;
            case "BU":
                colorCompleteName = "Blue1";
                break;
            case "GY":
                colorCompleteName = "Grey1";
                break;
            case "RD":
                colorCompleteName = "Red1";
                break;
            case "OG":
                colorCompleteName = "Orange1";
                break;
            case "YE":
                colorCompleteName = "Yellow1";
                break;
            default:
                colorCompleteName = "Default";
                break;

        }
        return colorCompleteName;
    }


}
