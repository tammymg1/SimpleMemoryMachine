package arch.sm213.machine.student;

import machine.AbstractMainMemory;


/**
 * Main Memory of Simple CPU.
 * <p>
 * Provides an abstraction of main memory (DRAM).
 */

// Main memory has functions and can be coded
public class MainMemory extends AbstractMainMemory {
    private byte[] mem;

    /**
     * Allocate memory.
     *
     * @param byteCapacity size of memory in bytes.
     */
    public MainMemory(int byteCapacity) {
        mem = new byte[byteCapacity];
    }

    /**
     * Determine whether an address is aligned to specified length.
     *
     * @param address memory address.
     * @param length  byte length.
     * @return true iff address is aligned to length.
     */
    @Override
    protected boolean isAccessAligned(int address, int length) {
        // look at the parameters in the function
        if ((address % length) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Convert an sequence of four bytes into a Big Endian integer.
     *
     * @param byteAtAddrPlus0 value of byte with lowest memory address (base address).
     * @param byteAtAddrPlus1 value of byte at base address plus 1.
     * @param byteAtAddrPlus2 value of byte at base address plus 2.
     * @param byteAtAddrPlus3 value of byte at base address plus 3 (highest memory address).
     * @return Big Endian integer formed by these four bytes.
     */
    @Override
    public int bytesToInteger(byte byteAtAddrPlus0, byte byteAtAddrPlus1, byte byteAtAddrPlus2, byte byteAtAddrPlus3) {
        // (for loop to shift the bits and form an integer) - No for loop, 4 parameters given
        // (and then do a 16^x for loop) wrong analysis - Still a hex integer
        return (byteAtAddrPlus3 & 0xFF) | ((byteAtAddrPlus2 & 0xFF) << 8) |
                ((byteAtAddrPlus1 & 0xFF) << 16) | ((byteAtAddrPlus0 & 0xFF) << 24);
        // yeah bit O has to be moved 24 to the left (0, 1, 2, 3)
    }

    /**
     * Convert a Big Endian integer into an array of 4 bytes organized by memory address.
     *
     * @param i an Big Endian integer.
     * @return an array of byte where [0] is value of low-address byte of the number etc.
     */
    @Override
    public byte[] integerToBytes(int i) {
        list[0] = (byte) ((i >> 24) & 0xFF); //cast to a byte? - yeah list type, else error
        // list of bytes, simply return the first two, second two, third two, fourth two, 24 is 6 hexes
        // (for loop append in the list) - no need use in built

        byte[] list = new byte[4];
        list[1] = (byte) ((i >> 16) & 0xFF); // why shift now? - to get rid of waste/ same integer
        list[2] = (byte) ((i >> 8) & 0xFF);
        list[3] = (byte) (i & 0xFF);

        return list;
    }

    /**
     * Fetch a sequence of bytes from memory.
     *
     * @param address address of the first byte to fetch.
     * @param length  number of bytes to fetch.
     * @return an array of byte where [0] is memory value at address, [1] is memory value at address+1 etc.
     * @throws InvalidAddressException if any address in the range address to address+length-1 is invalid.
     */
    @Override
    protected byte[] get(int address, int length) throws InvalidAddressException {
        byte[] listGet = new byte   [length];
        if (address < 0 | address + length - 1 >= mem.length) {
            throw new InvalidAddressException();
        }

        // mem is memory
        // Like loading a sequence

        for (int i = 0; i < length; i++) {
            listGet[i] = mem[address + i]; // + i to keep moving
            // loading memory into a list
        }
        return listGet;
    }


    /**
     * Store a sequence of bytes into memory.
     *
     * @param address address of the first byte in memory to recieve the specified value.
     * @param value   an array of byte values to store in memory at the specified address.
     * @throws InvalidAddressException if any address in the range address to address+value.length-1 is invalid.
     */
    @Override
    protected void set(int address, byte[] value) throws InvalidAddressException {
        //address/bytes into the memory/array
        if (address < 0 | address + value.length - 1 >= mem.length) {
            throw new InvalidAddressException();
        }

        // mem is memory
        // Like storing a sequence

        for (int i = 0; i < value.length; i++) {
            //value [i] = mem[address + i]; // + i to keep moving
            mem[address + i] = value[i]; // byte value storing into the memory
        }

    }

    /**
     * Determine the size of memory.
     *
     * @return the number of bytes allocated to this memory.
     */
    @Override
    public int length() {
        return mem.length;
    }
}
