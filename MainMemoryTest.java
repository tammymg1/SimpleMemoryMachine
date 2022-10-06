package arch.sm213.machine.student;


import machine.AbstractMainMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MainMemoryTest {

    //don't need before each constructor
    @Test
    public void testIsAligned() {
        MainMemory mem = new MainMemory(0);
        assertEquals(true, mem.isAccessAligned(0, 5));
        assertEquals(false, mem.isAccessAligned(1, 5));
        assertEquals(true, mem.isAccessAligned(5, 5));
        assertEquals(true, mem.isAccessAligned(20, 4));
        assertEquals(true, mem.isAccessAligned(10, 2));
        assertEquals(false, mem.isAccessAligned(1, 6));
    }

    @Test
    public void testBytesToInteger() {
        MainMemory m = new MainMemory(4);
        assertEquals(0x01020304, m.bytesToInteger((byte)01, (byte)02, (byte)03, (byte)04));

        MainMemory testMemory = new MainMemory(0);
        assertEquals(Integer.MIN_VALUE, testMemory.bytesToInteger((byte)0x80,(byte)0,(byte)0,(byte)0));
        assertEquals(0, testMemory.bytesToInteger((byte)0,(byte)0,(byte)0,(byte)0));
        assertEquals(2, testMemory.bytesToInteger((byte)0,(byte)0,(byte)0,(byte)2));
        assertEquals(144, testMemory.bytesToInteger((byte)0,(byte)0,(byte)0,(byte)0x90));
        assertEquals(238, testMemory.bytesToInteger((byte)0,(byte)0,(byte)0,(byte)0xee));
        assertEquals(-286331154, m.bytesToInteger((byte)0xee, (byte)0xee,(byte)0xee,(byte)0xee));
        assertEquals(Integer.MAX_VALUE, testMemory.bytesToInteger((byte)0x7f,(byte)0xff,(byte)0xff,(byte)0xff));
    }


    @Test
    public void TestIntegerToBytes() {
        MainMemory testMemory = new MainMemory(0);
        checkSolution(0xff, 0xff, 0xff, 0xff, testMemory.integerToBytes(-1));
        checkSolution(0x80, 0, 0, 0, testMemory.integerToBytes(Integer.MIN_VALUE));
        checkSolution(0, 0, 0, 0, testMemory.integerToBytes(0));
        checkSolution(0, 0, 0, 0x90, testMemory.integerToBytes(144));
        checkSolution(0, 0, 0, 0xee, testMemory.integerToBytes(238));
        checkSolution(0x7f, 0xff, 0xff, 0xff, testMemory.integerToBytes(Integer.MAX_VALUE));

    }

    private void checkSolution(int a, int b, int c, int d, byte[] list) {
        assertEquals((byte)a,list[0]);
        assertEquals((byte)b,list[1]);
        assertEquals((byte)c,list[2]);
        assertEquals((byte)d,list[3]);
    }


    @Test
    public void testSetterGetter() throws AbstractMainMemory.InvalidAddressException {

        MainMemory testMemory = new MainMemory(128);
        byte[] oneCase = {9};
        byte[] fourCase = {1,2,3,4};
        byte[] eightCase = {1,2,3,4,5,6,7,8};



        testMemory.set(0, oneCase);
        checkSolution2(oneCase, testMemory.get(0, 1));
        testMemory.set(4, eightCase);
        checkSolution2(eightCase, testMemory.get(4, 8));
        testMemory.set(8, fourCase);
        checkSolution2(fourCase, testMemory.get(8, 4));
        testMemory.set(127, oneCase);
        checkSolution2(oneCase, testMemory.get(127, 1));

        try {testMemory.set(-1, eightCase);
            fail();} catch (Exception e) {}
        try {testMemory.set(121, eightCase);
            fail();} catch (Exception e) {}
        try {testMemory.get(121, 8);
            fail();} catch (Exception e) {}
        try {testMemory.get(-1, 1);
            fail();} catch (Exception e) {}
    }


    private void checkSolution2(byte[] a, byte[] b) {
        assertEquals(a.length, b.length);
        for(int i=0; i < a.length; i++) {
            assertEquals(a[i],b[i]);
        }
    }
}

