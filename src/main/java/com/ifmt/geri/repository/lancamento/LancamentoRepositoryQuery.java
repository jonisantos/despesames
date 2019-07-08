package com.ifmt.geri.repository.lancamento;

import java.util.List;

import com.ifmt.geri.model.Lancamento;
import com.ifmt.geri.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
