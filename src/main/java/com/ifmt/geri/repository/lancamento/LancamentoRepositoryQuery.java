package com.ifmt.geri.repository.lancamento;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ifmt.geri.model.Lancamento;
import com.ifmt.geri.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
