package com.criss.event.utility;

public class RomanianConverter {
    public static String convertRomanianChars(String input) {
        if (input == null) {
            return null;
        }
        
        // Replace Romanian characters with their English equivalents
        input = input.replace("ă", "a")
                     .replace("â", "a")
                     .replace("î", "i")
                     .replace("ș", "s")
                     .replace("ț", "t")
                     .replace("Ă", "A")
                     .replace("Â", "A")
                     .replace("Î", "I")
                     .replace("Ș", "S")
                     .replace("Ț", "T");

        return input;
    }
}
