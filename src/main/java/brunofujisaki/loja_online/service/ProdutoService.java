package brunofujisaki.loja_online.service;

import brunofujisaki.loja_online.dto.ProdutoCadastroDTO;
import brunofujisaki.loja_online.dto.ProdutoDetalheDTO;
import brunofujisaki.loja_online.infra.exception.ProdutoException;
import brunofujisaki.loja_online.infra.exception.ProdutoNotFoundException;
import brunofujisaki.loja_online.model.Produto;
import brunofujisaki.loja_online.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//REGRAS DE NEGÓCIO:

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoDetalheDTO cadastrarProduto(ProdutoCadastroDTO dto) {
        return new ProdutoDetalheDTO(produtoRepository.save(new Produto(dto)));
    }

    @Transactional
    public Produto atualizarEstoque(Long id, Integer quantidade, boolean adicionar) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        if (!adicionar) {
            if (quantidade > produto.getQuantidade()) throw new ProdutoException("Estoque insuficiente: quantidade solicitada é maior do que a disponível");
            produto.setQuantidade(produto.getQuantidade() - quantidade);
        } else produto.setQuantidade(produto.getQuantidade() + quantidade);

        return produto;
    }

    public Page<ProdutoDetalheDTO> findAllProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(ProdutoDetalheDTO::new);
    }
}
