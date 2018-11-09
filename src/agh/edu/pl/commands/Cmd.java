package agh.edu.pl.commands;

public abstract class Cmd {
    public Cmd() {
    }

    public abstract byte[] createCommandData();

    public abstract byte[] createCommandData(Integer additional);
}
