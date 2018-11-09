package agh.edu.pl.commands;

public final class ZoomWideStdCmd extends Cmd {
    private static final byte[] ptWideStdCommandData = new byte[]{1, 4, 7, 3};

    public ZoomWideStdCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(ptWideStdCommandData);
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