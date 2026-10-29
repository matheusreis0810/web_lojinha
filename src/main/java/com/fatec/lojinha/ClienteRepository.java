package com.fatec.lojinha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Modifying 
    @Transactional 
    @Query(value = "update cliente set senha=:senhaTemp, ativo=0 where " +
    " email=:email", nativeQuery=true)
    int resetarSenha(String senhaTemp, String email);
    @Query(value = "select * from cliente where email=:email and senha=:senha", 
    nativeQuery = true)
    Cliente login(String email, String senha);
    
}
