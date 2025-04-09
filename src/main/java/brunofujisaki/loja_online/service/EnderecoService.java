package brunofujisaki.loja_online.service;

import brunofujisaki.loja_online.model.Endereco;
import brunofujisaki.loja_online.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    @Transactional
    public Endereco cadastrarEndereco(String cep) {
        return enderecoRepository.findByCep(cep)
                .orElseGet(() -> enderecoRepository.save(new Endereco(viaCepService.getEndereco(cep))));
    }
}
