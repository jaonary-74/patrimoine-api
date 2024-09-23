package com.patrimoine.demo.Service;

import com.patrimoine.demo.Model.Patrimoine;
import com.patrimoine.demo.Model.PatrimoineNotFoundException;
import com.patrimoine.demo.Repository.PatrimoineRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@Service
public class PatrimoineService {
    private final PatrimoineRepository patrimoineRepository;

    public PatrimoineService() {
        this.patrimoineRepository = new PatrimoineRepository();
    }

    public Patrimoine saveOrUpdatePatrimoine(String id, Patrimoine patrimoine) {
        patrimoine.setLastModified(LocalDateTime.now());
        patrimoineRepository.save(id, patrimoine);
        return patrimoine;
    }

    public Patrimoine getPatrimoine(String id) {
        Patrimoine patrimoine = patrimoineRepository.findById(id);
        if (patrimoine == null) {
            throw new PatrimoineNotFoundException(id);
        }
        return patrimoine;
    }
}
