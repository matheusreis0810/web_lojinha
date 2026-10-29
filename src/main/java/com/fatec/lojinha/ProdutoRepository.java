package com.fatec.lojinha;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository 
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Query(value = "select * from produto where destaque>0 and estoque>0" + 
    " order by destaque", nativeQuery = true)
    public List<Produto> carregarVitrine();

    @Query(value ="select * from produto where nome like :termo " + 
    " or nome like :termo order by destaque ", nativeQuery = true)
    public List<Produto> fazerBuscar(String termo);

    @Query(value = "select * from produto where quantidade<=0 order by nome", 
    nativeQuery = true)
    public List<Produto> carregarSemEstoque();
}
