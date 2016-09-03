package com.example.user.Design;

/**
 * Created by user on 23/08/2016.
 */
public class Color {

    public Color() {
    }
    //TODO create function getColorHex so we can use it to modify background to make testing easier

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
                colorCompleteName = "Nothing";
                break;
            case "Nothing":
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
                colorCompleteName = "LIGHT GREEN";
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
                colorCompleteName = "SKY BLUE";
                break;
            case "G":
                colorCompleteName = "Green";
                break;
            case "BK":
                colorCompleteName = "BLACK";
                break;
            case "WH":
                colorCompleteName = "WHITE";
                break;
            case "VT":
                colorCompleteName = "PURPLE";
                break;
            case "BU":
                colorCompleteName = "BLUE";
                break;
            case "GY":
                colorCompleteName = "GREY";
                break;
            case "RD":
                colorCompleteName = "RED";
                break;
            case "OG":
                colorCompleteName = "ORANGE";
                break;
            case "YE":
                colorCompleteName = "YELLOW";
                break;

            case "White":
                colorCompleteName = "W";
                break;
            case "Yellow":
                colorCompleteName = "Y";
                break;
            case "Orange":
                colorCompleteName = "O";
                break;
            case "Red":
                colorCompleteName = "R";
                break;
            case "Purple":
                colorCompleteName = "V";
                break;
            case "Blue":
                colorCompleteName = "L";
                break;
            case "LIGHT GREEN":
                colorCompleteName = "LG";
                break;
            case "Grey":
                colorCompleteName = "GR";
                break;
            case "Brown":
                colorCompleteName = "BR";
                break;
            case "Black":
                colorCompleteName = "B";
                break;
            case "Pink":
                colorCompleteName = "P";
                break;
            case "SKY BLUE":
                colorCompleteName = "SB";
                break;
            case "Green":
                colorCompleteName = "G";
                break;
            case "BLACK":
                colorCompleteName = "BK";
                break;
            case "WHITE":
                colorCompleteName = "WH";


                break;
            case "PURPLE":
                colorCompleteName = "VT";
                break;
            case "BLUE":
                colorCompleteName = "BU";
                break;
            case "GREY":
                colorCompleteName = "GY";
                break;
            case "RED":
                colorCompleteName = "RD";
                break;
            case "ORANGE":
                colorCompleteName = "OG";
                break;
            case "YELLOW":
                colorCompleteName = "YE";
                break;
            case "PK":
                colorCompleteName = "PINK";
                break;
            case "PINK":
                colorCompleteName = "PK";
                break;
            default:
                colorCompleteName = colorName;
                break;

        }
        return colorCompleteName;
    }


}
