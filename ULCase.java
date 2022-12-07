class ULCase {
    public static void main(String [] args)
    throws java.io.IOException {
        char ch;

        // taking input
        System.out.print("Name a character within the alphabet: ");
        ch = (char) System.in.read();
        if (ch >= 'a') {
            System.out.print("Upper case version is: " + uppercase(ch));
        }
        else {
            System.out.print("Lower case version is: " + lowercase(ch));
        }
    }

    private static char lowercase(char c) {
        // to convert into a lower, change the 6th bit into a 1
        return (char) ((int) c | 32);
    }
    private static char uppercase(char c) {
        return (char) ((int) c & 65503);
    }
}