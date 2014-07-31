/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverConnection.utils;

/**
 *
 * @author JA
 */
public class ProtocolProperties {

    public static final byte POTWIERDZENIE_I_STEROWANIE_BYTE_1 = (byte) 0xff;
    public static final byte POTWIERDZENIE_I_STEROWANIE_BYTE_2 = (byte) 0xfe;
    public static final byte IDENTYFIKACJA_MYJNI = 0x01;
    public static final byte CYKLICZNE_DANE_FINANSOWE = 0x02;
    public static final int NULL = 0;

    public static final int FIVE_BYTES_MESSAGE = 5;
    public static final int SIX_BYTES_MESSAGE = 6;

    public static final int FIRST_GROUP_BYTE = 0;
    public static final int SECOND_GROUP_BYTE = 1;
    public static final int FIRST_MSG_BYTE = 2;
    public static final int SECOND_MSG_BYTE = 3;
    public static final int ADDITIONAL_BYTE = 4;

}
