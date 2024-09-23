package com.patrimoine.demo.Repository;

import com.patrimoine.demo.Model.Patrimoine;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class PatrimoineRepository {
    private final String directory = "patrimoines/";

    public PatrimoineRepository() {
        try {
            Files.createDirectories(Paths.get(directory));
        } catch (IOException e) {
            throw new RuntimeException("Could not create directory", e);
        }
    }

    public void save(String id, Patrimoine patrimoine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + id + ".txt"))) {
            writer.write(patrimoineToString(patrimoine));
        } catch (IOException e) {
            throw new RuntimeException("Error saving patrimoine", e);
        }
    }

    public void saveWithPrintWriter(String id, Patrimoine patrimoine) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(directory + id + ".txt"))) {
            printWriter.print(patrimoineToString(patrimoine));
        } catch (IOException e) {
            throw new RuntimeException("Error saving patrimoine", e);
        }
    }

    public Patrimoine findById(String id) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(directory + id + ".txt"));
            return stringToPatrimoine(lines.get(0));
        } catch (IOException e) {
            throw new RuntimeException("Error reading patrimoine", e);
        }
    }

    private String patrimoineToString(Patrimoine patrimoine) {
        return patrimoine.getOwner() + "," + patrimoine.getLastModified();
    }

    private Patrimoine stringToPatrimoine(String data) {
        String[] parts = data.split(",");
        return new Patrimoine(parts[0], LocalDateTime.parse(parts[1]));
    }
}