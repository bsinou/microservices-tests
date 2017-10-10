package net.sinou.sandbox.sbgtuto.services;
public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}