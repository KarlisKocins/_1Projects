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
    StringBuilder test = new StringBuilder();
    linkedArray.add(0, String.valueOf(str.length()));
    int BitIndex = 0, ByteIndex = 1;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 'A' -> test.append("00");
                case 'C' -> test.append("01");
                case 'G' -> test.append("10");
                case 'T' -> test.append("11");
            }
            BitIndex++;
            if (BitIndex == 4){
                linkedArray.add(ByteIndex, String.valueOf(test));
                test.setLength(0); // reset StringBuilder
                BitIndex = 0;
                ByteIndex++;
            }
        }
        if (BitIndex != 0) { // add remaining bits to linkedArray
            test.append("00".repeat(4 - BitIndex));
            linkedArray.add(ByteIndex, String.valueOf(test));
        }
        // Convert binary strings to hexadecimal and put them back into linkedArray
        for (int i = 1; i < linkedArray.size(); i++) {
            String binary = linkedArray.get(i);
            int decimal = Integer.parseInt(binary, 2);
            String hexStr = Integer.toString(decimal, 16);
            linkedArray.set(i, hexStr.toUpperCase());
        }
        System.out.println(linkedArray);
    }
}