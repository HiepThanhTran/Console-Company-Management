public class TestConsole {

    public static void main(String[] args) {
        int width = 20;
        String s = "level"; // length = 5

        int padSize = width - s.length(); // 20 - 5 = 15
        int padStart = s.length() + padSize / 2; // 5 + 7 = 12

        s = String.format("%" + padStart + "s", s); // %12s -> .......hello
        s = String.format("%-" + width + "s", s); // %-20 -> .......hello........

        System.out.println(s);
    }
}
