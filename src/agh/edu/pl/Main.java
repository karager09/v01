package agh.edu.pl;

import jssc.SerialPort;
import jssc.SerialPortException;
import pl.edu.agh.kis.visca.ViscaResponseReader;

import java.util.Scanner;

public class Main {

    SerialPort serialPort;

    public static void main(String[] args) {


        Main main= new Main("COM5");

        String cmd;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Give your command: ");
            cmd = in.nextLine();
            System.out.println("Cmd: "+cmd); //wyświetlamy powitanie
        }
    }

    public Main(String commName) {
        serialPort = new SerialPort(commName);
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            System.out.println("Address");
            sendAddress(serialPort);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }


    }

    private String getResponse() {
        try {
            byte [] response = ViscaResponseReader.readResponse(serialPort);
            System.out.println("> " + byteArrayToString(response));
            return  byteArrayToString(response);

        } catch (ViscaResponseReader.TimeoutException var16) {
            System.out.println("! TIMEOUT exception");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }


    private static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] var5 = bytes;
        int var4 = bytes.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            byte b = var5[var3];
            sb.append(String.format("%02X ", b));
        }

        return sb.toString();
    }
}
