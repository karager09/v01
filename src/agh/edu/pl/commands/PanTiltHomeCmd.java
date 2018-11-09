package agh.edu.pl.commands;


public final class PanTiltHomeCmd extends Cmd {
    private static final byte[] ptHomeCommandData = new byte[]{1, 6, 4};

    public PanTiltHomeCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(ptHomeCommandData);
        return cmdData;
    }

    @Override
    public byte[] createCommandData(Integer additional) {
        return new byte[0];
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}

