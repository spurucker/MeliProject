package org.meli.exercise.Meli.mutant;

import org.meli.exercise.Meli.validators.ServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutants")
public class MutantService {
    @Autowired
    private MutantController controller;
    @Autowired
    private ServiceValidator validators;

    @RequestMapping("/mutant")
    public boolean isMutant(@RequestBody Dna dna) throws Exception {
        validators.isDnaValid(dna);
        return controller.isMutant(dna.getLines());
    }

    @RequestMapping("/stats")
    public Stat stats() throws Exception {
        return controller.getStats();
    }
}
