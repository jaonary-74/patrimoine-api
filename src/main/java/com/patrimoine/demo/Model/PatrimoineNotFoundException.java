package com.patrimoine.demo.Model;

public class PatrimoineNotFoundException extends RuntimeException {
    public PatrimoineNotFoundException(String id) {
        super("Patrimoine not found with ID: " + id);
    }
}