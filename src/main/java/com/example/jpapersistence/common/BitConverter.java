package com.example.jpapersistence.common;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
public class BitConverter {
    public static byte[] toBytes(Integer value) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }

    public static Integer toInteger(byte[] data) {
        return ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static Integer toInteger(byte[] data, int start, int length) {
        if (length > 4 || start + length > data.length) {
            return null;
        }

        byte[] buffer = new byte[4];

        System.arraycopy(data, start, buffer, 0, length);

        if (length < 4) {
            for (int i = length; i < 4; i++) {
                buffer[i] = (byte) 0;
            }
        }

        return toInteger(buffer);
    }

    /**
     * Short
     */
    public static byte[] toBytes(Short value) {
        return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array();
    }

    /*
     * Float
     */
    public static byte[] toBytes(Float value) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array();
    }

    public static Float toFloat(byte[] data) {
        return ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    /*
     * String
     */
    public static String toHexString(byte[] data) {
        StringBuffer buffer = new StringBuffer();

        for (byte byt : data) {
            String hex = Integer.toHexString((int) byt);

            if (hex.length() == 1) {
                hex = "0" + hex;
            }

            buffer.append(" " + hex);
        }

        return buffer.toString().trim().toUpperCase();
    }

    public static String toString(byte[] data) {
        StringBuffer buffer = new StringBuffer();

        for (byte byt : data) {
            buffer.append(" " + Integer.toString((int) byt));
        }

        return buffer.toString().trim();
    }

    public static long toInt64(byte[] data, int offset) {
        return (((long) (data[offset + 7] & 0xff) << 56)
                | ((long) (data[offset + 6] & 0xff) << 48)
                | ((long) (data[offset + 5] & 0xff) << 40)
                | ((long) (data[offset + 4] & 0xff) << 32)
                | ((long) (data[offset + 3] & 0xff) << 24)
                | ((long) (data[offset + 2] & 0xff) << 16)
                | ((long) (data[offset + 1] & 0xff) << 8) | (data[offset] & 0xff));
    }



}
