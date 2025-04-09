package brunofujisaki.loja_online.service;

import brunofujisaki.loja_online.client.ViaCepClient;
import brunofujisaki.loja_online.infra.exception.ViaCepClientException;
import brunofujisaki.loja_online.model.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public Endereco getEndereco(String cep) {
        var enderco = client.getEndereco(cep);
        if (enderco.getCep() == null) throw new ViaCepClientException("Cep inválido");
        return enderco;
    }
}
