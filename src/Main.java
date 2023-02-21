import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean a = true;
        while (a) {
            System.out.print("Ievadiet komandu: ");
            String command = sc.nextLine().toLowerCase();
            String[] dns = command.split(" ");
            switch (dns[0]) {
                case "comp": {
                    String str = dns[1].toUpperCase();
                    if (!str.matches("^[ACGT]*$")) {
                        System.out.println("wrong command format");
                        continue;
                    } else {
                        comp(str);
                        break;
                    }
                }
                case "decomp": {
                    decomp(dns);
                    break;
                }
                case "exit": {
                    System.out.println("Goodbye!");
                    a = false;
                    break;
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
            byte b = 0;
            switch (str.charAt(i)) {
                case 'A':
                    b = 0;
                    break;
                case 'C':
                    b = 1;
                    break;
                case 'G':
                    b = 2;
                    break;
                case 'T':
                    b = 3;
                    break;
            }
            bytes[ByteIndex] |= (b << (6 - 2 * BitIndex));
            BitIndex++;
            if (BitIndex == 4){
                BitIndex = 0;
                ByteIndex++;
            }
        }
        if (BitIndex != 0) { // add remaining bits to linkedArray
            bytes[ByteIndex] |= (0 << (6 - 2 * BitIndex));
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
        LinkedList<String> test_hex = new LinkedList<>();
        LinkedList<String> test_binary = new LinkedList<>();
        byte[] byteArr = new byte[str.length - 3];
        String binary = "";
        for (int i = 2; i < str.length; i++) {
            int str_int = Integer.parseInt(str[i]);
            String str_hex = Integer.toHexString(str_int & 0xFF).toUpperCase();
            String str_binary = Integer.toBinaryString(str_int);
            test_hex.add(str_hex);
            test_binary.add(str_binary);
        }
        System.out.println(test_hex);
        System.out.println("-------------");
        System.out.println(test_binary);
    }
}