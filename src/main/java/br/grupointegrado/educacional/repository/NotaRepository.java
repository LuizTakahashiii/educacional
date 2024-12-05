package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {

    //  boletim por aluno
    @Query("SELECT n FROM Nota n WHERE n.matricula.aluno.id = :alunoId")
    List<Nota> findNotasByAlunoId(@Param("alunoId") Integer alunoId);

    //  média das notas por turma
    @Query("SELECT AVG(n.nota) FROM Nota n WHERE n.matricula.turma.id = :turmaId")
    Double findMediaNotasByTurmaId(@Param("turmaId") Integer turmaId);

    //  notas por disciplina
    @Query("SELECT n FROM Nota n WHERE n.disciplina.id = :disciplinaId")
    List<Nota> findNotasByDisciplinaId(@Param("disciplinaId") Integer disciplinaId);
}
