package agh.edu.pl.commands;

public final class PanTiltRightCmd extends Cmd {
    private static final byte[] ptRightCommandData = new byte[]{1, 6, 1, 0, 0, 2, 3};

    public PanTiltRightCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(ptRightCommandData);
        cmdData[3] = 4;
        cmdData[4] = 1;
        return cmdData;
    }

    @Override
    public byte[] createCommandData(Integer additional) {
        if (additional != null) {
            byte[] cmdData = duplicatArray(ptRightCommandData);
            cmdData[3] = additional.byteValue();
            cmdData[4] = 1;

            return cmdData;
        } else {
            return createCommandData();
        }
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}

