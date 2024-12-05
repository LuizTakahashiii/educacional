package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    @PostMapping
    public Aluno save(@RequestBody Aluno aluno) {
        return repository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id, @RequestBody Aluno aluno) {
        Aluno existingAluno = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        existingAluno.setNome(aluno.getNome());
        existingAluno.setEmail(aluno.getEmail());
        existingAluno.setMatricula(aluno.getMatricula());
        existingAluno.setDataNascimento(aluno.getDataNascimento());

        return repository.save(existingAluno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        repository.delete(aluno);
    }
}
