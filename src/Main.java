import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Ievadiet komandu: ");
            String command = sc.nextLine().toLowerCase();
            String[] dns = command.split(" ");
            switch (dns[0]) {
                case "comp" -> {
                    String str = dns[1].toUpperCase();
                    if (!str.matches("^[ACGT]*$")) {
                        System.out.println("wrong command format");
                    } else {
                        comp(str);
                    }
                }
                case "decomp" -> decomp(dns);
                case "about" -> System.out.println("221RDB429 Kārlis Kociņš 16.Grupa");
                case "exit" -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    private static void comp(String str) {
        LinkedList<String> linkedArray = new LinkedList<>();
        byte[] bytes = new byte[(str.length() / 4) + 1];
        linkedArray.add(0, String.valueOf(str.length()));
        int BitIndex = 0, ByteIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            byte b = switch (str.charAt(i)) {
                case 'A' -> 0;
                case 'C' -> 1;
                case 'G' -> 2;
                case 'T' -> 3;
                default -> throw new IllegalStateException("Unexpected value: " + str.charAt(i));
            };
            bytes[ByteIndex] |= (b << (6 - 2 * BitIndex));
            BitIndex++;
            if (BitIndex == 4){
                BitIndex = 0;
                ByteIndex++;
            }
        }
        if (BitIndex != 0) { // add remaining bits to linkedArray
            bytes[ByteIndex] |= (0);
        }
        // Convert binary strings to hexadecimal and put them back into linkedArray
        for (byte b : bytes) {
            String hexStr = String.format("%2X", b);
            linkedArray.add(hexStr);
        }
        String list = Arrays.toString(linkedArray.toArray()).replace("[", "").replace("]", "").replace(",","").replace("  ", " ");
        System.out.println(list);
    }

    private static void decomp(String[] str) {
        int daudzums = Integer.parseInt(str[1]);
        int daudzumi = Integer.parseInt(String.valueOf(str.length));
        if (daudzums != daudzumi - 2){
            System.out.println("wrong command format");
        }else {
            LinkedList<String> test_hex = new LinkedList<>();
            int burti = Integer.parseInt(str[2]);
            for (int i = 2; i < str.length; i++) {
                int str_int = Integer.parseInt(str[i]);
                String str_hex = Integer.toHexString(str_int & 0xFF).toUpperCase();
                test_hex.add(str_hex);
            }
            String list = Arrays.toString(test_hex.toArray()).replace("[", "").replace("]", "").replace(",", "").replace("  ", " ");
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < test_hex.size(); i++) {
                int dec = Integer.parseInt(test_hex.get(i), 16);
                sb.append(String.format("%8s", Integer.toBinaryString(dec)).replace(' ', '0'));
            }
            String binaryStr = sb.toString();
            int dauzi = binaryStr.length();
            if (dauzi <= burti * 2){
                System.out.println("wrong command format");
            }else {
                System.out.println(list);
                StringBuilder dnaStr = new StringBuilder();
                for (int i = 0; i < burti * 2; i += 2) {
                    String codon = binaryStr.substring(i, i + 2);
                    switch (codon) {
                        case "00" -> dnaStr.append("A");
                        case "01" -> dnaStr.append("C");
                        case "10" -> dnaStr.append("G");
                        case "11" -> dnaStr.append("T");
                    }
                }
                System.out.println(dnaStr);
            }
        }
    }
}