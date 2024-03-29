package com.ifmt.geri.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ifmt.geri.event.RecursoCriadoEvent;
import com.ifmt.geri.model.Pessoa;
import com.ifmt.geri.repository.PessoaRepository;
import com.ifmt.geri.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;
    
    @Autowired
    private PessoaService pessoaService;
    
    @GetMapping
    public List<Pessoa>listar(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa>criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){

        Pessoa pessoaSalvar = pessoaRepository.save(pessoa);        
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalvar.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalvar);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa>buscaPeloCodigo(@PathVariable Long codigo){
        Pessoa pessoa = pessoaRepository.findOne(codigo);
        return pessoa !=null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
    	pessoaRepository.delete(codigo);
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
    	Pessoa pessoaSalva = pessoaService.autualizar(codigo, pessoa);
    	return ResponseEntity.ok(pessoaSalva);
    }
    
    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
    	pessoaService.autualizarPropriedadeAtivo(codigo, ativo);
    }
    
}
