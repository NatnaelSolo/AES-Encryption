package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class AES{

    // Method to generate a new AES key
    public static SecretKey generateKey() throws Exception {
        return KeyGenerator.getInstance("AES").generateKey();
    }


    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedText = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedText);
    }


    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedText);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // User input
            System.out.print("Enter a message to encrypt: ");
            String message = scanner.nextLine();

            // Generate AES key
            SecretKey secretKey = generateKey();

            // Encrypt the message
            String encryptedMessage = encrypt(message, secretKey);
            System.out.println("Encrypted Message: " + encryptedMessage);

            // Decrypt the message
            String decryptedMessage = decrypt(encryptedMessage, secretKey);
            System.out.println("Decrypted Message: " + decryptedMessage);

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
