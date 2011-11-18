package org.framework42.utils.services.impl;

import org.framework42.utils.services.ASCII7Util;

public class ASCII7UtilImpl implements ASCII7Util {

    @Override
    public byte[] characterNormalisation(String data) {

        byte[] byteArray = new byte[data.length()];

        int i = 0;

        for(char c: data.toCharArray()) {

            if(c == ' ') {
                byteArray[i] = X_20;
            } else if(c == '!') {
                byteArray[i] = X_21;
            } else if(c == '"') {
                byteArray[i] = X_22;
            } else if(c == '#') {
                byteArray[i] = X_23;
            } else if(c == '$') {
                byteArray[i] = X_24;
            } else if(c == '%') {
                byteArray[i] = X_25;
            } else if(c == '&') {
                byteArray[i] = X_26;
            } else if(c == '\'') {
                byteArray[i] = X_27;
            } else if(c == '(') {
                byteArray[i] = X_28;
            } else if(c == ')') {
                byteArray[i] = X_29;
            } else if(c == '*') {
                byteArray[i] = X_2A;
            } else if(c == '+') {
                byteArray[i] = X_2B;
            } else if(c == ',') {
                byteArray[i] = X_2C;
            } else if(c == '-') {
                byteArray[i] = X_2D;
            } else if(c == '.') {
                byteArray[i] = X_2E;
            } else if(c == '/') {
                byteArray[i] = X_2F;
            } else if(c == '0') {
                byteArray[i] = X_30;
            } else if(c == '1') {
                byteArray[i] = X_31;
            } else if(c == '2') {
                byteArray[i] = X_32;
            } else if(c == '3') {
                byteArray[i] = X_33;
            } else if(c == '4') {
                byteArray[i] = X_34;
            } else if(c == '5') {
                byteArray[i] = X_35;
            } else if(c == '6') {
                byteArray[i] = X_36;
            } else if(c == '7') {
                byteArray[i] = X_37;
            } else if(c == '8') {
                byteArray[i] = X_38;
            } else if(c == '9') {
                byteArray[i] = X_39;
            } else if(c == ':') {
                byteArray[i] = X_3A;
            } else if(c == ';') {
                byteArray[i] = X_3B;
            } else if(c == '<') {
                byteArray[i] = X_3C;
            } else if(c == '=') {
                byteArray[i] = X_3D;
            } else if(c == '>') {
                byteArray[i] = X_3E;
            } else if(c == '?') {
                byteArray[i] = X_3F;
            } else if(c == 'É') {
                byteArray[i] = X_40;
            } else if(c == '@') {
                byteArray[i] = X_40;
            } else if(c == 'A') {
                byteArray[i] = X_41;
            } else if(c == 'B') {
                byteArray[i] = X_42;
            } else if(c == 'C') {
                byteArray[i] = X_43;
            } else if(c == 'D') {
                byteArray[i] = X_44;
            } else if(c == 'E') {
                byteArray[i] = X_45;
            } else if(c == 'F') {
                byteArray[i] = X_46;
            } else if(c == 'G') {
                byteArray[i] = X_47;
            } else if(c == 'H') {
                byteArray[i] = X_48;
            } else if(c == 'I') {
                byteArray[i] = X_49;
            } else if(c == 'J') {
                byteArray[i] = X_4A;
            } else if(c == 'K') {
                byteArray[i] = X_4B;
            } else if(c == 'L') {
                byteArray[i] = X_4C;
            } else if(c == 'M') {
                byteArray[i] = X_4D;
            } else if(c == 'N') {
                byteArray[i] = X_4E;
            } else if(c == 'O') {
                byteArray[i] = X_4F;
            } else if(c == 'P') {
                byteArray[i] = X_50;
            } else if(c == 'Q') {
                byteArray[i] = X_51;
            } else if(c == 'R') {
                byteArray[i] = X_52;
            } else if(c == 'S') {
                byteArray[i] = X_53;
            } else if(c == 'T') {
                byteArray[i] = X_54;
            } else if(c == 'U') {
                byteArray[i] = X_55;
            } else if(c == 'V') {
                byteArray[i] = X_56;
            } else if(c == 'W') {
                byteArray[i] = X_57;
            } else if(c == 'X') {
                byteArray[i] = X_58;
            } else if(c == 'Y') {
                byteArray[i] = X_59;
            } else if(c == 'Z') {
                byteArray[i] = X_5A;
            } else if(c == 'Ä') {
                byteArray[i] = X_5B;
            } else if(c == '[') {
                byteArray[i] = X_5B;
            } else if(c == 'Ö') {
                byteArray[i] = X_5C;
            } else if(c == '\\') {
                byteArray[i] = X_5C;
            } else if(c == 'Å') {
                byteArray[i] = X_5D;
            } else if(c == ']') {
                byteArray[i] = X_5D;
            } else if(c == 'Ü') {
                byteArray[i] = X_5E;
            } else if(c == '^') {
                byteArray[i] = X_5E;
            } else if(c == '_') {
                byteArray[i] = X_5F;
            } else if(c == 'é') {
                byteArray[i] = X_60;
            } else if(c == '`') {
                byteArray[i] = X_60;
            } else if(c == 'a') {
                byteArray[i] = X_61;
            } else if(c == 'b') {
                byteArray[i] = X_62;
            } else if(c == 'c') {
                byteArray[i] = X_63;
            } else if(c == 'd') {
                byteArray[i] = X_64;
            } else if(c == 'e') {
                byteArray[i] = X_65;
            } else if(c == 'f') {
                byteArray[i] = X_66;
            } else if(c == 'g') {
                byteArray[i] = X_67;
            } else if(c == 'h') {
                byteArray[i] = X_68;
            } else if(c == 'i') {
                byteArray[i] = X_69;
            } else if(c == 'j') {
                byteArray[i] = X_6A;
            } else if(c == 'k') {
                byteArray[i] = X_6B;
            } else if(c == 'l') {
                byteArray[i] = X_6C;
            } else if(c == 'm') {
                byteArray[i] = X_6D;
            } else if(c == 'n') {
                byteArray[i] = X_6E;
            } else if(c == 'o') {
                byteArray[i] = X_6F;
            } else if(c == 'p') {
                byteArray[i] = X_70;
            } else if(c == 'q') {
                byteArray[i] = X_71;
            } else if(c == 'r') {
                byteArray[i] = X_72;
            } else if(c == 's') {
                byteArray[i] = X_73;
            } else if(c == 't') {
                byteArray[i] = X_74;
            } else if(c == 'u') {
                byteArray[i] = X_75;
            } else if(c == 'v') {
                byteArray[i] = X_76;
            } else if(c == 'w') {
                byteArray[i] = X_77;
            } else if(c == 'x') {
                byteArray[i] = X_78;
            } else if(c == 'y') {
                byteArray[i] = X_79;
            } else if(c == 'z') {
                byteArray[i] = X_7A;
            } else if(c == 'ä') {
                byteArray[i] = X_7B;
            } else if(c == '{') {
                byteArray[i] = X_7B;
            } else if(c == 'ö') {
                byteArray[i] = X_7C;
            } else if(c == '|') {
                byteArray[i] = X_7C;
            } else if(c == 'å') {
                byteArray[i] = X_7D;
            } else if(c == '}') {
                byteArray[i] = X_7D;
            } else if(c == 'ü') {
                byteArray[i] = X_7E;
            } else if(c == '~') {
                byteArray[i] = X_7E;
            } else {
                byteArray[i] = X_C3;
            }

            i++;
        }

        return byteArray;
    }

    @Override
    public byte[] hexNormalisation(String hexData) {

        int length = hexData.length()/2;
        if(hexData.length()%2 != 0) {
            length++;
        }

        byte[] bytes = new byte[length];

        int counter = 0;
        for(int i = 0; i<hexData.length(); i+=2) {

            if(i+2<hexData.length()) {

                bytes[counter] = getHexAsByte(hexData.substring(i, i+2));
            } else {
                bytes[counter] = getHexAsByte(hexData.substring(i));
            }

            counter++;
        }

        return bytes;
    }

    @Override
    public byte getHexAsByte(String hexValue) {

        if(hexValue.equalsIgnoreCase("00")) {
            return X_00;
        } else if(hexValue.equalsIgnoreCase("01")) {
            return X_01;
        } else if(hexValue.equalsIgnoreCase("02")) {
            return X_02;
        } else if(hexValue.equalsIgnoreCase("03")) {
            return X_03;
        } else if(hexValue.equalsIgnoreCase("04")) {
            return X_04;
        } else if(hexValue.equalsIgnoreCase("05")) {
            return X_05;
        } else if(hexValue.equalsIgnoreCase("06")) {
            return X_06;
        } else if(hexValue.equalsIgnoreCase("07")) {
            return X_07;
        } else if(hexValue.equalsIgnoreCase("08")) {
            return X_08;
        } else if(hexValue.equalsIgnoreCase("09")) {
            return X_09;
        } else if(hexValue.equalsIgnoreCase("0a")) {
            return X_0A;
        } else if(hexValue.equalsIgnoreCase("0b")) {
            return X_0B;
        } else if(hexValue.equalsIgnoreCase("0c")) {
            return X_0C;
        } else if(hexValue.equalsIgnoreCase("0d")) {
            return X_0D;
        } else if(hexValue.equalsIgnoreCase("0e")) {
            return X_0E;
        } else if(hexValue.equalsIgnoreCase("0f")) {
            return X_0F;
        } else if(hexValue.equalsIgnoreCase("10")) {
            return X_10;
        } else if(hexValue.equalsIgnoreCase("11")) {
            return X_11;
        } else if(hexValue.equalsIgnoreCase("12")) {
            return X_12;
        } else if(hexValue.equalsIgnoreCase("13")) {
            return X_13;
        } else if(hexValue.equalsIgnoreCase("14")) {
            return X_14;
        } else if(hexValue.equalsIgnoreCase("15")) {
            return X_15;
        } else if(hexValue.equalsIgnoreCase("16")) {
            return X_16;
        } else if(hexValue.equalsIgnoreCase("17")) {
            return X_17;
        } else if(hexValue.equalsIgnoreCase("18")) {
            return X_18;
        } else if(hexValue.equalsIgnoreCase("19")) {
            return X_19;
        } else if(hexValue.equalsIgnoreCase("1a")) {
            return X_1A;
        } else if(hexValue.equalsIgnoreCase("1b")) {
            return X_1B;
        } else if(hexValue.equalsIgnoreCase("1c")) {
            return X_1C;
        } else if(hexValue.equalsIgnoreCase("1d")) {
            return X_1D;
        } else if(hexValue.equalsIgnoreCase("1e")) {
            return X_1E;
        } else if(hexValue.equalsIgnoreCase("1f")) {
            return X_1F;
        } else if(hexValue.equalsIgnoreCase("20")) {
            return X_20;
        } else if(hexValue.equalsIgnoreCase("21")) {
            return X_21;
        } else if(hexValue.equalsIgnoreCase("22")) {
            return X_22;
        } else if(hexValue.equalsIgnoreCase("23")) {
            return X_23;
        } else if(hexValue.equalsIgnoreCase("24")) {
            return X_24;
        } else if(hexValue.equalsIgnoreCase("25")) {
            return X_25;
        } else if(hexValue.equalsIgnoreCase("26")) {
            return X_26;
        } else if(hexValue.equalsIgnoreCase("27")) {
            return X_27;
        } else if(hexValue.equalsIgnoreCase("28")) {
            return X_28;
        } else if(hexValue.equalsIgnoreCase("29")) {
            return X_29;
        } else if(hexValue.equalsIgnoreCase("2a")) {
            return X_2A;
        } else if(hexValue.equalsIgnoreCase("2b")) {
            return X_2B;
        } else if(hexValue.equalsIgnoreCase("2c")) {
            return X_2C;
        } else if(hexValue.equalsIgnoreCase("2d")) {
            return X_2D;
        } else if(hexValue.equalsIgnoreCase("2e")) {
            return X_2E;
        } else if(hexValue.equalsIgnoreCase("2f")) {
            return X_2F;
        } else if(hexValue.equalsIgnoreCase("30")) {
            return X_30;
        } else if(hexValue.equalsIgnoreCase("31")) {
            return X_31;
        } else if(hexValue.equalsIgnoreCase("32")) {
            return X_32;
        } else if(hexValue.equalsIgnoreCase("33")) {
            return X_33;
        } else if(hexValue.equalsIgnoreCase("34")) {
            return X_34;
        } else if(hexValue.equalsIgnoreCase("35")) {
            return X_35;
        } else if(hexValue.equalsIgnoreCase("36")) {
            return X_36;
        } else if(hexValue.equalsIgnoreCase("37")) {
            return X_37;
        } else if(hexValue.equalsIgnoreCase("38")) {
            return X_38;
        } else if(hexValue.equalsIgnoreCase("39")) {
            return X_39;
        } else if(hexValue.equalsIgnoreCase("3a")) {
            return X_3A;
        } else if(hexValue.equalsIgnoreCase("3b")) {
            return X_3B;
        } else if(hexValue.equalsIgnoreCase("3c")) {
            return X_3C;
        } else if(hexValue.equalsIgnoreCase("3d")) {
            return X_3D;
        } else if(hexValue.equalsIgnoreCase("3e")) {
            return X_3E;
        } else if(hexValue.equalsIgnoreCase("3f")) {
            return X_3F;
        } else if(hexValue.equalsIgnoreCase("40")) {
            return X_40;
        } else if(hexValue.equalsIgnoreCase("41")) {
            return X_41;
        } else if(hexValue.equalsIgnoreCase("42")) {
            return X_42;
        } else if(hexValue.equalsIgnoreCase("43")) {
            return X_43;
        } else if(hexValue.equalsIgnoreCase("44")) {
            return X_44;
        } else if(hexValue.equalsIgnoreCase("45")) {
            return X_45;
        } else if(hexValue.equalsIgnoreCase("46")) {
            return X_46;
        } else if(hexValue.equalsIgnoreCase("47")) {
            return X_47;
        } else if(hexValue.equalsIgnoreCase("48")) {
            return X_48;
        } else if(hexValue.equalsIgnoreCase("49")) {
            return X_49;
        } else if(hexValue.equalsIgnoreCase("4a")) {
            return X_4A;
        } else if(hexValue.equalsIgnoreCase("4b")) {
            return X_4B;
        } else if(hexValue.equalsIgnoreCase("4c")) {
            return X_4C;
        } else if(hexValue.equalsIgnoreCase("4d")) {
            return X_4D;
        } else if(hexValue.equalsIgnoreCase("4e")) {
            return X_4E;
        } else if(hexValue.equalsIgnoreCase("4f")) {
            return X_4F;
        } else if(hexValue.equalsIgnoreCase("50")) {
            return X_50;
        } else if(hexValue.equalsIgnoreCase("51")) {
            return X_51;
        } else if(hexValue.equalsIgnoreCase("52")) {
            return X_52;
        } else if(hexValue.equalsIgnoreCase("53")) {
            return X_53;
        } else if(hexValue.equalsIgnoreCase("54")) {
            return X_54;
        } else if(hexValue.equalsIgnoreCase("55")) {
            return X_55;
        } else if(hexValue.equalsIgnoreCase("56")) {
            return X_56;
        } else if(hexValue.equalsIgnoreCase("57")) {
            return X_57;
        } else if(hexValue.equalsIgnoreCase("58")) {
            return X_58;
        } else if(hexValue.equalsIgnoreCase("59")) {
            return X_59;
        } else if(hexValue.equalsIgnoreCase("5a")) {
            return X_5A;
        } else if(hexValue.equalsIgnoreCase("5b")) {
            return X_5B;
        } else if(hexValue.equalsIgnoreCase("5c")) {
            return X_5C;
        } else if(hexValue.equalsIgnoreCase("5d")) {
            return X_5D;
        } else if(hexValue.equalsIgnoreCase("5e")) {
            return X_5E;
        } else if(hexValue.equalsIgnoreCase("5f")) {
            return X_5F;
        } else if(hexValue.equalsIgnoreCase("60")) {
            return X_60;
        } else if(hexValue.equalsIgnoreCase("61")) {
            return X_61;
        } else if(hexValue.equalsIgnoreCase("62")) {
            return X_62;
        } else if(hexValue.equalsIgnoreCase("63")) {
            return X_63;
        } else if(hexValue.equalsIgnoreCase("64")) {
            return X_64;
        } else if(hexValue.equalsIgnoreCase("65")) {
            return X_65;
        } else if(hexValue.equalsIgnoreCase("66")) {
            return X_66;
        } else if(hexValue.equalsIgnoreCase("67")) {
            return X_67;
        } else if(hexValue.equalsIgnoreCase("68")) {
            return X_68;
        } else if(hexValue.equalsIgnoreCase("69")) {
            return X_69;
        } else if(hexValue.equalsIgnoreCase("6a")) {
            return X_6A;
        } else if(hexValue.equalsIgnoreCase("6b")) {
            return X_6B;
        } else if(hexValue.equalsIgnoreCase("6c")) {
            return X_6C;
        } else if(hexValue.equalsIgnoreCase("6d")) {
            return X_6D;
        } else if(hexValue.equalsIgnoreCase("6e")) {
            return X_6E;
        } else if(hexValue.equalsIgnoreCase("6f")) {
            return X_6F;
        } else if(hexValue.equalsIgnoreCase("70")) {
            return X_70;
        } else if(hexValue.equalsIgnoreCase("71")) {
            return X_71;
        } else if(hexValue.equalsIgnoreCase("72")) {
            return X_72;
        } else if(hexValue.equalsIgnoreCase("73")) {
            return X_73;
        } else if(hexValue.equalsIgnoreCase("74")) {
            return X_74;
        } else if(hexValue.equalsIgnoreCase("75")) {
            return X_75;
        } else if(hexValue.equalsIgnoreCase("76")) {
            return X_76;
        } else if(hexValue.equalsIgnoreCase("77")) {
            return X_77;
        } else if(hexValue.equalsIgnoreCase("78")) {
            return X_78;
        } else if(hexValue.equalsIgnoreCase("79")) {
            return X_79;
        } else if(hexValue.equalsIgnoreCase("7a")) {
            return X_7A;
        } else if(hexValue.equalsIgnoreCase("7b")) {
            return X_7B;
        } else if(hexValue.equalsIgnoreCase("7c")) {
            return X_7C;
        } else if(hexValue.equalsIgnoreCase("7d")) {
            return X_7D;
        } else if(hexValue.equalsIgnoreCase("7e")) {
            return X_7E;
        } else if(hexValue.equalsIgnoreCase("7f")) {
            return X_7F;
        } else if(hexValue.equalsIgnoreCase("80")) {
            return X_80;
        } else if(hexValue.equalsIgnoreCase("81")) {
            return X_81;
        } else if(hexValue.equalsIgnoreCase("82")) {
            return X_82;
        } else if(hexValue.equalsIgnoreCase("83")) {
            return X_83;
        } else if(hexValue.equalsIgnoreCase("84")) {
            return X_84;
        } else if(hexValue.equalsIgnoreCase("85")) {
            return X_85;
        } else if(hexValue.equalsIgnoreCase("86")) {
            return X_86;
        } else if(hexValue.equalsIgnoreCase("87")) {
            return X_87;
        } else if(hexValue.equalsIgnoreCase("88")) {
            return X_88;
        } else if(hexValue.equalsIgnoreCase("89")) {
            return X_89;
        } else if(hexValue.equalsIgnoreCase("8a")) {
            return X_8A;
        } else if(hexValue.equalsIgnoreCase("8b")) {
            return X_8B;
        } else if(hexValue.equalsIgnoreCase("8c")) {
            return X_8C;
        } else if(hexValue.equalsIgnoreCase("8d")) {
            return X_8D;
        } else if(hexValue.equalsIgnoreCase("8e")) {
            return X_8E;
        } else if(hexValue.equalsIgnoreCase("8f")) {
            return X_8F;
        } else if(hexValue.equalsIgnoreCase("90")) {
            return X_90;
        } else if(hexValue.equalsIgnoreCase("91")) {
            return X_91;
        } else if(hexValue.equalsIgnoreCase("92")) {
            return X_92;
        } else if(hexValue.equalsIgnoreCase("93")) {
            return X_93;
        } else if(hexValue.equalsIgnoreCase("94")) {
            return X_94;
        } else if(hexValue.equalsIgnoreCase("95")) {
            return X_95;
        } else if(hexValue.equalsIgnoreCase("96")) {
            return X_96;
        } else if(hexValue.equalsIgnoreCase("97")) {
            return X_97;
        } else if(hexValue.equalsIgnoreCase("98")) {
            return X_98;
        } else if(hexValue.equalsIgnoreCase("99")) {
            return X_99;
        } else if(hexValue.equalsIgnoreCase("9a")) {
            return X_9A;
        } else if(hexValue.equalsIgnoreCase("9b")) {
            return X_9B;
        } else if(hexValue.equalsIgnoreCase("9c")) {
            return X_9C;
        } else if(hexValue.equalsIgnoreCase("9d")) {
            return X_9D;
        } else if(hexValue.equalsIgnoreCase("9e")) {
            return X_9E;
        } else if(hexValue.equalsIgnoreCase("9f")) {
            return X_9F;
        } else if(hexValue.equalsIgnoreCase("a0")) {
            return X_A0;
        } else if(hexValue.equalsIgnoreCase("a1")) {
            return X_A1;
        } else if(hexValue.equalsIgnoreCase("a2")) {
            return X_A2;
        } else if(hexValue.equalsIgnoreCase("a3")) {
            return X_A3;
        } else if(hexValue.equalsIgnoreCase("a4")) {
            return X_A4;
        } else if(hexValue.equalsIgnoreCase("a5")) {
            return X_A5;
        } else if(hexValue.equalsIgnoreCase("a6")) {
            return X_A6;
        } else if(hexValue.equalsIgnoreCase("a7")) {
            return X_A7;
        } else if(hexValue.equalsIgnoreCase("a8")) {
            return X_A8;
        } else if(hexValue.equalsIgnoreCase("a9")) {
            return X_A9;
        } else if(hexValue.equalsIgnoreCase("aa")) {
            return X_AA;
        } else if(hexValue.equalsIgnoreCase("ab")) {
            return X_AB;
        } else if(hexValue.equalsIgnoreCase("ac")) {
            return X_AC;
        } else if(hexValue.equalsIgnoreCase("ad")) {
            return X_AD;
        } else if(hexValue.equalsIgnoreCase("ae")) {
            return X_AE;
        } else if(hexValue.equalsIgnoreCase("af")) {
            return X_AF;
        } else if(hexValue.equalsIgnoreCase("b0")) {
            return X_B0;
        } else if(hexValue.equalsIgnoreCase("b1")) {
            return X_B1;
        } else if(hexValue.equalsIgnoreCase("b2")) {
            return X_B2;
        } else if(hexValue.equalsIgnoreCase("b3")) {
            return X_B3;
        } else if(hexValue.equalsIgnoreCase("b4")) {
            return X_B4;
        } else if(hexValue.equalsIgnoreCase("b5")) {
            return X_B5;
        } else if(hexValue.equalsIgnoreCase("b6")) {
            return X_B6;
        } else if(hexValue.equalsIgnoreCase("b7")) {
            return X_B7;
        } else if(hexValue.equalsIgnoreCase("b8")) {
            return X_B8;
        } else if(hexValue.equalsIgnoreCase("b9")) {
            return X_B9;
        } else if(hexValue.equalsIgnoreCase("ba")) {
            return X_BA;
        } else if(hexValue.equalsIgnoreCase("bb")) {
            return X_BB;
        } else if(hexValue.equalsIgnoreCase("bc")) {
            return X_BC;
        } else if(hexValue.equalsIgnoreCase("bd")) {
            return X_BD;
        } else if(hexValue.equalsIgnoreCase("be")) {
            return X_BE;
        } else if(hexValue.equalsIgnoreCase("bf")) {
            return X_BF;
        } else if(hexValue.equalsIgnoreCase("c0")) {
            return X_C0;
        } else if(hexValue.equalsIgnoreCase("c1")) {
            return X_C1;
        } else if(hexValue.equalsIgnoreCase("c2")) {
            return X_C2;
        } else if(hexValue.equalsIgnoreCase("c3")) {
            return X_C3;
        } else if(hexValue.equalsIgnoreCase("c4")) {
            return X_C4;
        } else if(hexValue.equalsIgnoreCase("c5")) {
            return X_C5;
        } else if(hexValue.equalsIgnoreCase("c6")) {
            return X_C6;
        } else if(hexValue.equalsIgnoreCase("c7")) {
            return X_C7;
        } else if(hexValue.equalsIgnoreCase("c8")) {
            return X_C8;
        } else if(hexValue.equalsIgnoreCase("c9")) {
            return X_C9;
        } else if(hexValue.equalsIgnoreCase("ca")) {
            return X_CA;
        } else if(hexValue.equalsIgnoreCase("cb")) {
            return X_CB;
        } else if(hexValue.equalsIgnoreCase("cc")) {
            return X_CC;
        } else if(hexValue.equalsIgnoreCase("cd")) {
            return X_CD;
        } else if(hexValue.equalsIgnoreCase("ce")) {
            return X_CE;
        } else if(hexValue.equalsIgnoreCase("cf")) {
            return X_CF;
        } else if(hexValue.equalsIgnoreCase("d0")) {
            return X_D0;
        } else if(hexValue.equalsIgnoreCase("d1")) {
            return X_D1;
        } else if(hexValue.equalsIgnoreCase("d2")) {
            return X_D2;
        } else if(hexValue.equalsIgnoreCase("d3")) {
            return X_D3;
        } else if(hexValue.equalsIgnoreCase("d4")) {
            return X_D4;
        } else if(hexValue.equalsIgnoreCase("d5")) {
            return X_D5;
        } else if(hexValue.equalsIgnoreCase("d6")) {
            return X_D6;
        } else if(hexValue.equalsIgnoreCase("d7")) {
            return X_D7;
        } else if(hexValue.equalsIgnoreCase("d8")) {
            return X_D8;
        } else if(hexValue.equalsIgnoreCase("d9")) {
            return X_D9;
        } else if(hexValue.equalsIgnoreCase("da")) {
            return X_DA;
        } else if(hexValue.equalsIgnoreCase("db")) {
            return X_DB;
        } else if(hexValue.equalsIgnoreCase("dc")) {
            return X_DC;
        } else if(hexValue.equalsIgnoreCase("dd")) {
            return X_DD;
        } else if(hexValue.equalsIgnoreCase("de")) {
            return X_DE;
        } else if(hexValue.equalsIgnoreCase("df")) {
            return X_DF;
        } else if(hexValue.equalsIgnoreCase("e0")) {
            return X_E0;
        } else if(hexValue.equalsIgnoreCase("e1")) {
            return X_E1;
        } else if(hexValue.equalsIgnoreCase("e2")) {
            return X_E2;
        } else if(hexValue.equalsIgnoreCase("e3")) {
            return X_E3;
        } else if(hexValue.equalsIgnoreCase("e4")) {
            return X_E4;
        } else if(hexValue.equalsIgnoreCase("e5")) {
            return X_E5;
        } else if(hexValue.equalsIgnoreCase("e6")) {
            return X_E6;
        } else if(hexValue.equalsIgnoreCase("e7")) {
            return X_E7;
        } else if(hexValue.equalsIgnoreCase("e8")) {
            return X_E8;
        } else if(hexValue.equalsIgnoreCase("e9")) {
            return X_E9;
        } else if(hexValue.equalsIgnoreCase("ea")) {
            return X_EA;
        } else if(hexValue.equalsIgnoreCase("eb")) {
            return X_EB;
        } else if(hexValue.equalsIgnoreCase("ec")) {
            return X_EC;
        } else if(hexValue.equalsIgnoreCase("ed")) {
            return X_ED;
        } else if(hexValue.equalsIgnoreCase("ee")) {
            return X_EE;
        } else if(hexValue.equalsIgnoreCase("ef")) {
            return X_EF;
        } else if(hexValue.equalsIgnoreCase("f0")) {
            return X_F0;
        } else if(hexValue.equalsIgnoreCase("f1")) {
            return X_F1;
        } else if(hexValue.equalsIgnoreCase("f2")) {
            return X_F2;
        } else if(hexValue.equalsIgnoreCase("f3")) {
            return X_F3;
        } else if(hexValue.equalsIgnoreCase("f4")) {
            return X_F4;
        } else if(hexValue.equalsIgnoreCase("f5")) {
            return X_F5;
        } else if(hexValue.equalsIgnoreCase("f6")) {
            return X_F6;
        } else if(hexValue.equalsIgnoreCase("f7")) {
            return X_F7;
        } else if(hexValue.equalsIgnoreCase("f8")) {
            return X_F8;
        } else if(hexValue.equalsIgnoreCase("f9")) {
            return X_F9;
        } else if(hexValue.equalsIgnoreCase("fa")) {
            return X_FA;
        } else if(hexValue.equalsIgnoreCase("fb")) {
            return X_FB;
        } else if(hexValue.equalsIgnoreCase("fc")) {
            return X_FC;
        } else if(hexValue.equalsIgnoreCase("fd")) {
            return X_FD;
        } else if(hexValue.equalsIgnoreCase("fe")) {
            return X_FE;
        } else if(hexValue.equalsIgnoreCase("ff")) {
            return X_FF;
        }

        throw new IllegalArgumentException("No byte value matching hex value");
    }

    @Override
    public String getValueAsHex(byte byteValue) {

        if(X_20 == byteValue) {
            return "20";
        } else if(X_21 == byteValue) {
            return "21";
        } else if(X_22 == byteValue) {
            return "22";
        } else if(X_23 == byteValue) {
            return "23";
        } else if(X_24 == byteValue) {
            return "24";
        } else if(X_25 == byteValue) {
            return "26";
        } else if(X_26 == byteValue) {
            return "26";
        } else if(X_27 == byteValue) {
            return "27";
        } else if(X_28 == byteValue) {
            return "28";
        } else if(X_2A == byteValue) {
            return "2a";
        } else if(X_2B == byteValue) {
            return "2b";
        } else if(X_2C == byteValue) {
            return "2c";
        } else if(X_2D == byteValue) {
            return "2d";
        } else if(X_2E == byteValue) {
            return "2e";
        } else if(X_2F == byteValue) {
            return "2f";
        } else if(X_30 == byteValue) {
            return "30";
        } else if(X_31 == byteValue) {
            return "31";
        } else if(X_32 == byteValue) {
            return "32";
        } else if(X_33 == byteValue) {
            return "33";
        } else if(X_34 == byteValue) {
            return "34";
        } else if(X_35 == byteValue) {
            return "35";
        } else if(X_36 == byteValue) {
            return "36";
        } else if(X_37 == byteValue) {
            return "37";
        } else if(X_38 == byteValue) {
            return "38";
        } else if(X_39 == byteValue) {
            return "39";
        } else if(X_3A == byteValue) {
            return "3a";
        } else if(X_3B == byteValue) {
            return "3b";
        } else if(X_3C == byteValue) {
            return "3c";
        } else if(X_3D == byteValue) {
            return "3d";
        } else if(X_3E == byteValue) {
            return "3e";
        } else if(X_3F == byteValue) {
            return "3f";
        } else if(X_40 == byteValue) {
            return "40";
        } else if(X_41 == byteValue) {
            return "41";
        } else if(X_42 == byteValue) {
            return "42";
        } else if(X_43 == byteValue) {
            return "43";
        } else if(X_44 == byteValue) {
            return "44";
        } else if(X_45 == byteValue) {
            return "45";
        } else if(X_46 == byteValue) {
            return "46";
        } else if(X_47 == byteValue) {
            return "47";
        } else if(X_48 == byteValue) {
            return "48";
        } else if(X_49 == byteValue) {
            return "49";
        } else if(X_4A == byteValue) {
            return "4a";
        } else if(X_4B == byteValue) {
            return "4b";
        } else if(X_4C == byteValue) {
            return "4c";
        } else if(X_4D == byteValue) {
            return "4c";
        } else if(X_4E == byteValue) {
            return "4e";
        } else if(X_4F == byteValue) {
            return "4F";
        } else if(X_50 == byteValue) {
            return "50";
        } else if(X_51 == byteValue) {
            return "51";
        } else if(X_52 == byteValue) {
            return "52";
        } else if(X_53 == byteValue) {
            return "53";
        } else if(X_54 == byteValue) {
            return "54";
        } else if(X_55 == byteValue) {
            return "55";
        } else if(X_56 == byteValue) {
            return "56";
        } else if(X_57 == byteValue) {
            return "57";
        } else if(X_58 == byteValue) {
            return "58";
        } else if(X_59 == byteValue) {
            return "59";
        } else if(X_5A == byteValue) {
            return "5a";
        } else if(X_5B == byteValue) {
            return "5b";
        } else if(X_5C == byteValue) {
            return "5c";
        } else if(X_5D == byteValue) {
            return "5d";
        } else if(X_5E == byteValue) {
            return "5e";
        } else if(X_5F == byteValue) {
            return "5f";
        } else if(X_60 == byteValue) {
            return "60";
        } else if(X_61 == byteValue) {
            return "61";
        } else if(X_62 == byteValue) {
            return "62";
        } else if(X_63 == byteValue) {
            return "63";
        } else if(X_64 == byteValue) {
            return "64";
        } else if(X_65 == byteValue) {
            return "65";
        } else if(X_66 == byteValue) {
            return "66";
        } else if(X_67 == byteValue) {
            return "67";
        } else if(X_68 == byteValue) {
            return "68";
        } else if(X_69 == byteValue) {
            return "69";
        } else if(X_6A == byteValue) {
            return "6a";
        } else if(X_6B == byteValue) {
            return "6b";
        } else if(X_6C == byteValue) {
            return "6c";
        } else if(X_6D == byteValue) {
            return "6d";
        } else if(X_6E == byteValue) {
            return "6e";
        } else if(X_6F == byteValue) {
            return "6F";
        } else if(X_70 == byteValue) {
            return "70";
        } else if(X_71 == byteValue) {
            return "71";
        } else if(X_72 == byteValue) {
            return "72";
        } else if(X_73 == byteValue) {
            return "73";
        } else if(X_74 == byteValue) {
            return "74";
        } else if(X_75 == byteValue) {
            return "75";
        } else if(X_76 == byteValue) {
            return "76";
        } else if(X_77 == byteValue) {
            return "77";
        } else if(X_78 == byteValue) {
            return "78";
        } else if(X_79 == byteValue) {
            return "79";
        } else if(X_7A == byteValue) {
            return "7a";
        } else if(X_7B == byteValue) {
            return "7b";
        } else if(X_7C == byteValue) {
            return "7c";
        } else if(X_7D == byteValue) {
            return "7D";
        } else if(X_7E == byteValue) {
            return "7e";
        } else {
            return "c3";
        }
    }
}
