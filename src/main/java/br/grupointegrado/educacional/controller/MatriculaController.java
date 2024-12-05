package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping
    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Matricula findById(@PathVariable Integer id) {
        return matriculaRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Matrícula não encontrada"));
    }

    @PostMapping
    public Matricula save(@RequestBody Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @PutMapping("/{id}")
    public Matricula update(@PathVariable Integer id, @RequestBody Matricula matricula) {
        Matricula existingMatricula = matriculaRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Matrícula não encontrada"));

        existingMatricula.setAluno(matricula.getAluno());
        existingMatricula.setTurma(matricula.getTurma());
        return matriculaRepository.save(existingMatricula);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        matriculaRepository.deleteById(id);
    }
}
