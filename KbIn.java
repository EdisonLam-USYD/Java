// This program is demonstrating masking and taking system inputs
// also displays how to initialise arrays
// 'throw'ing an exception will be explained later

class KbIn {
    public static void main(String [] args)
    throws java.io.IOException {
        char input;
        System.out.print("Input a character: ");
        input = (char) System.in.read();
        System.out.println("Your key input was: " + input);
        vowel_check(input);
    }

    public static void vowel_check(char in) {
        char [] vowels = {'a', 'e', 'i', 'o', 'u'}; // curly brackets is the same as new char[5] and loading in each character
        for (short i = 0; i < vowels.length; i++){
            if (in == vowels[i]) {
                System.out.println(in + " is a vowel... probably");
                return;
            }
        }
        System.out.println(in + " is not a vowel");
        return;

    }
}