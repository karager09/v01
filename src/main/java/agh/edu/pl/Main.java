package agh.edu.pl;

import agh.edu.pl.commands.*;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class Main {

    SerialPort serialPort;

    public static void main(String[] args) {
        Main main = new Main("COM5");

        String cmd;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Give your command: ");
            cmd = in.nextLine();
            System.out.println("Cmd: " + cmd); //wyświetlamy powitanie
            main.executeCommand(cmd);
        }
//        main.executeCommand("address", (byte) 8);
//        try {
//            sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        main.executeCommand("up", (byte) 1);

    }

    public Main(String commName) {
        serialPort = new SerialPort(commName);
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public String executeCommand(String commandLine) {
        try {
            String[] command = commandLine.trim().replaceAll(" +", " ").split(" ");
            byte address = Byte.parseByte(command[1].trim());
            Integer additionalParameter = null;
            if (command.length > 2) {
                additionalParameter = Integer.parseInt(command[2].trim());
            }

            byte[] cmdData = new byte[0];
            switch (command[0].trim()) {
                case "address":
                    cmdData = (new AddressCmd()).createCommandData();
                    break;
                case "down":
                    cmdData = (new PanTiltDownCmd()).createCommandData();
                    break;
                case "up":
                    cmdData = (new PanTiltUpCmd()).createCommandData();
                    break;
                case "home":
                    cmdData = (new PanTiltHomeCmd()).createCommandData();
                    break;
                case "right":
                    cmdData = (new PanTiltRightCmd()).createCommandData(additionalParameter);
                    break;
                case "left":
                    cmdData = (new PanTiltLeftCmd()).createCommandData(additionalParameter);
                    break;
                case "zoomin":
                    cmdData = (new ZoomTeleStdCmd()).createCommandData();
                    break;
                case "zoomout":
                    cmdData = (new ZoomWideStdCmd()).createCommandData();
                    break;
                default:
                    System.out.println("Wrong command!");
            }


            ViscaCommand vCmd = new ViscaCommand();
            vCmd.commandData = cmdData;
            vCmd.sourceAdr = 0;
            vCmd.destinationAdr = address;
            cmdData = vCmd.getCommandData();
            System.out.println("@ " + byteArrayToString(cmdData));
            try {
                serialPort.writeBytes(cmdData);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }

            return getResponse();
        } catch (Exception e) {
            System.out.println("Error in command!");
        }

        return "Unknown error";
    }

    private String getResponse() {
        try {
            byte[] response = ViscaResponseReader.readResponse(serialPort);
            System.out.println("> " + byteArrayToString(response));
            return byteArrayToString(response);

        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (ViscaResponseReader.TimeoutException e) {
            e.printStackTrace();
        }
        return "Error";
    }


    private static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] tab = bytes;
        int length = bytes.length;

        for (int i = 0; i < length; ++i) {
            byte b = tab[i];
            sb.append(String.format("%02X ", b));
        }

        String response = sb.toString();
        if (Pattern.matches(".. 4. FF.*", response)) {
            return "ACK";
        } else if (Pattern.matches(".. 5. FF.*", response)) {
            return "Command completion";
        } else if (Pattern.matches(".. 50 .* FF.*", response)) {
            return "Information return";
        } else if (Pattern.matches(".0 60 02 FF.*", response)) {
            return "Syntax error";
        } else if (Pattern.matches(".0 60 03 FF.*", response)) {
            return "Command buffer full";
        } else if (Pattern.matches(".0 60 04 FF.*", response)) {
            return "Command cancel";
        } else if (Pattern.matches(".0 60 05 FF.*", response)) {
            return "No sockets";
        } else if (Pattern.matches(".0 60 41 FF.*", response)) {
            return "Command not executable";
        } else if (Pattern.matches(".. 30 0. FF.*", response)) {
            return "Address assigned: " + response;
        }

        return response;
    }

}
