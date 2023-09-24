package com.kaos.escola.repositories;

import com.kaos.escola.models.Alunos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface AlunosRepository extends CrudRepository<Alunos, Long> {

}
