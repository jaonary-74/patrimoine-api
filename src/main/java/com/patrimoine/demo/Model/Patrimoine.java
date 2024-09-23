package com.patrimoine.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Patrimoine {
    private String owner;
    private LocalDateTime lastModified;

    public Patrimoine(String owner, LocalDateTime lastModified) {
        this.owner = owner;
        this.lastModified = lastModified;
    }
}
