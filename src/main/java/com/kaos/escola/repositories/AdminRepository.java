package com.kaos.escola.repositories;

import com.kaos.escola.models.Administradores;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface AdminRepository extends CrudRepository<Administradores, Long> {

    @Query (value = "select * from tb_admin where nome = :nome and senha = :senha", nativeQuery = true)
    public Administradores login(String nome, String senha);

}
