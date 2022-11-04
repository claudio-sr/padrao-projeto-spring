package com.claudio.padroes.service;

import com.claudio.padroes.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Cliente HTTP, criado via <b>OpenFeign</b>, para o comsumo da API do <b>ViaCEP</b>.
 */
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
