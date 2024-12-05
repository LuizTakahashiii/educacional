package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.model.Nota;
import br.grupointegrado.educacional.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaRepository repository;

    // **CRUD EXISTENTE**

    @GetMapping
    public List<Nota> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Nota findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
    }

    @PostMapping
    public Nota save(@RequestBody Nota nota) {
        return repository.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable Integer id, @RequestBody Nota nota) {
        Nota existingNota = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        existingNota.setNota(nota.getNota());
        existingNota.setDataLancamento(nota.getDataLancamento());
        existingNota.setDisciplina(nota.getDisciplina());
        existingNota.setMatricula(nota.getMatricula());

        return repository.save(existingNota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Nota nota = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        repository.delete(nota);
    }


    //  boletim por aluno
    @GetMapping("/boletim/{alunoId}")
    public List<Nota> getBoletimPorAluno(@PathVariable Integer alunoId) {
        return repository.findNotasByAlunoId(alunoId);
    }

    // média das notas por turma
    @GetMapping("/desempenho/turma/{turmaId}")
    public Double getMediaPorTurma(@PathVariable Integer turmaId) {
        return repository.findMediaNotasByTurmaId(turmaId);
    }

    // notas por disciplina
    @GetMapping("/desempenho/disciplina/{disciplinaId}")
    public List<Nota> getNotasPorDisciplina(@PathVariable Integer disciplinaId) {
        return repository.findNotasByDisciplinaId(disciplinaId);
    }
}
