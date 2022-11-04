package com.claudio.padroes.service.impl;

import com.claudio.padroes.model.Cliente;
import com.claudio.padroes.model.ClienteRepository;
import com.claudio.padroes.model.Endereco;
import com.claudio.padroes.model.EnderecoRepository;
import com.claudio.padroes.service.ClienteService;
import com.claudio.padroes.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link  com.claudio.padroes.service.ClienteService} aqual
 * pode ser injetada pelo Spring (Via {@link org.springframework.beans.factory.annotation.Autowire})
 * Com isso, como essa classe é um {@link org.hibernate.service.Service}, ela
 * será tratada como um <b>Singleton</b>
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteDb = clienteRepository.findById(id);
        if(clienteDb.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }
    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
