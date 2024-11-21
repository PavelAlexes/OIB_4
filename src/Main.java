import java.util.Scanner;

public class Main {

    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя !.,?=";
    private static final int ALPHABET_SIZE = ALPHABET.length();

    private static void printVigenereTable() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            for (int j = 0; j < ALPHABET_SIZE; j++) {
                char shiftedChar = ALPHABET.charAt((i + j) % ALPHABET_SIZE);
                System.out.print(shiftedChar + "");
            }
            System.out.println();
        }
    }

    private static String generateKey(String text, String key) {
        StringBuilder keyBuilder = new StringBuilder(key);
        while (keyBuilder.length() < text.length()) {
            keyBuilder.append(key);
        }
        return keyBuilder.substring(0, text.length());
    }

    private static String encrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i);

            if (ALPHABET.indexOf(textChar) != -1) { // Только если символ в алфавите
                int textIndex = ALPHABET.indexOf(textChar);
                int keyIndex = ALPHABET.indexOf(keyChar);
                int encryptedIndex = (textIndex + keyIndex) % ALPHABET_SIZE;
                encryptedText.append(ALPHABET.charAt(encryptedIndex));
            } else {
                encryptedText.append(textChar); // Символы вне алфавита остаются неизменными
            }
        }
        return encryptedText.toString();
    }

    private static String decrypt(String text, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i);

            if (ALPHABET.indexOf(textChar) != -1) { // Только если символ в алфавите
                int textIndex = ALPHABET.indexOf(textChar);
                int keyIndex = ALPHABET.indexOf(keyChar);
                int decryptedIndex = (textIndex - keyIndex + ALPHABET_SIZE) % ALPHABET_SIZE;
                decryptedText.append(ALPHABET.charAt(decryptedIndex));
            } else {
                decryptedText.append(textChar); // Символы вне алфавита остаются неизменными
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Печать таблицы Виженера
        printVigenereTable();

        boolean flag = false;
        while (!flag) {

            System.out.print("\nВыберите действие (1 - шифрование, 2 - расшифровка): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Поглощение символа новой строки

            if (choice != 1 && choice != 2) {
                System.out.println("Неверный выбор действия.");
                return;
            }

            System.out.print("Введите текст: ");
            String text = scanner.nextLine();

            System.out.print("Введите ключ: ");
            String key = scanner.nextLine();

            // Генерация ключа той же длины, что и текст
            String fullKey = generateKey(text, key);

            if (choice == 1) {
                // Шифрование текста
                String encryptedText = encrypt(text, fullKey);
                System.out.println("\nЗашифрованный текст: " + encryptedText);
            } else {
                // Дешифрование текста
                String decryptedText = decrypt(text, fullKey);
                System.out.println("\nРасшифрованный текст: " + decryptedText);
            }
        }
    }
}
