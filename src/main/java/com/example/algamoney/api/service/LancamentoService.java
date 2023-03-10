package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired 
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		validarPessoa(lancamento);
		return lancamentoRepository.save(lancamento);
	}
	
	private void validarPessoa(Lancamento lancamento) {
	    Optional<Pessoa> pessoaOpt = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

	    if (!pessoaOpt.isPresent() || pessoaOpt.get().isInativo()) {
	        throw new PessoaInexistenteOuInativaException();
	    }
	}

}
