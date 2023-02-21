import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Ievadiet komandu: ");
            String command = sc.nextLine().toLowerCase();
            switch (command) {
                case "comp" -> {
                    System.out.print("String plz: ");
                    String str = sc.nextLine().toUpperCase();
                    if (!str.matches("^[ACGT]*$")) {
                        System.out.println("wrong command format");
                        continue;
                    } else {
                        comp(str);
                    }
                }
                case "decomp" -> System.out.println("Coming Soon!");
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
                case 'A' -> b = 0;
                case 'C' -> b = 1;
                case 'G' -> b = 2;
                case 'T' -> b = 3;
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
        System.out.println(linkedArray);
    }
}