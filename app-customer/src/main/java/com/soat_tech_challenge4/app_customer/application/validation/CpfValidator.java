package com.soat_tech_challenge4.app_customer.application.validation;

public class CpfValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return false;
        }

        // Remove non-numeric characters
        cpf = cpf.replaceAll("[^0-9]", "");

        // Check if it has 11 digits
        if (cpf.length() != 11) {
            return false;
        }

        // Check if all digits are the same
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // First digit validation
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit > 9) {
            firstDigit = 0;
        }
        if (firstDigit != (cpf.charAt(9) - '0')) {
            return false;
        }

        // Second digit validation
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit > 9) {
            secondDigit = 0;
        }
        return secondDigit == (cpf.charAt(10) - '0');
    }
}