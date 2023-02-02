package project;

    public class Cipher {
        // tablice odpowiedzialne za określenie znaków - samogłosek oraz alfabetu.
        private static final char[] VOWELS = {'a', 'ą', 'e', 'ę', 'i', 'o', 'ó', 'u'};
        private static final char[] ALPHABET = {'a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'ł', 'm', 'n', 'ń', 'o', 'ó', 'p', 'q', 'r', 's', 'ś', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ź', 'ż'};

        public static String encode(String text) {
            StringBuilder encoded = new StringBuilder();
            int indexVow, indexAlph;
            for (int i = 0; i < text.length(); i++) {
                // znalezienie indeksu litery w tablicy ALPHABET
                indexAlph = Helpers.indexOf(ALPHABET, Character.toLowerCase(text.charAt(i)));
                // jeżeli taki znak nie istnieje to znaczy, że jest to inny typ znaku - niepodlegający szyfrowaniu
                if (indexAlph == -1) {
                    encoded.append(text.charAt(i));
                    continue;
                }
                // do sprawdzenia, czy jest to samogłoska
                indexVow = Helpers.indexOf(VOWELS, Character.toLowerCase(text.charAt(i)));
                // zarówno dla spółgłosek, jak i samogłosek przesunięcie wynosi 3
                encoded.append(ALPHABET[(indexAlph + 3) % ALPHABET.length]);
                // jeśli oryginalny znak był dużą literą, to zamień znak zwracany przez metodę na dużą literę
                if (Character.isUpperCase(text.charAt(i)))
                    encoded.setCharAt(encoded.length() - 1, Character.toUpperCase(encoded.charAt(encoded.length() - 1)));
                // dla spółgosek dodatkowo dodać należy 'o'
                if (indexVow < 0)
                    encoded.append('o');
            }
            return encoded.toString();
        }

        public static String decode(String text) {
            StringBuilder decoded = new StringBuilder();
            int indexAlph, orindex;
            for (int i = 0; i < text.length(); i++) {
                // znalezienie indeksu litery w tablicy ALPHABET
                indexAlph = Helpers.indexOf(ALPHABET, Character.toLowerCase(text.charAt(i)));
                // jeżeli nie istnieje to nie podlega szyfrowaniu
                if (indexAlph == -1) {
                    decoded.append(text.charAt(i));
                    continue;
                }
                orindex = (indexAlph - 3 + ALPHABET.length) % ALPHABET.length;
                // dodanie znaku do decoded
                decoded.append(ALPHABET[orindex]);
                // jeśli oryginalny znak był dużą literą,
                // to zamień znak zwracany przez metodę na dużą literę
                if (Character.isUpperCase(text.charAt(i)))
                    decoded.setCharAt(decoded.length() - 1, Character.toUpperCase(decoded.charAt(decoded.length() - 1)));
                // określenie oryginalnej litery i sprawdzenie czy jest to spółgłoska
                if (Helpers.indexOf(VOWELS, ALPHABET[orindex]) < 0){
                    // jeżeli kolejny znak to nie jest 'o' to znaczy, że w zaszyfrowanym komunikacie jest błąd!
                    if (i + 1 >= text.length() || text.charAt(i + 1) != 'o')
                        return "Błędny szyfr!";
                    // jeżeli jest to spółgłoska to trzeba pominąć kolejną literę, ponieważ jest ona 'o'
                    i++;
                }
            }
            return decoded.toString();
        }
    }
