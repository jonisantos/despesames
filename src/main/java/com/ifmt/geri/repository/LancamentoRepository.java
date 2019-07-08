package com.ifmt.geri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifmt.geri.model.Lancamento;
import com.ifmt.geri.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> , LancamentoRepositoryQuery{



}
