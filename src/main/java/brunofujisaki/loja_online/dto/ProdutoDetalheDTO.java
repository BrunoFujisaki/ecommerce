package brunofujisaki.loja_online.dto;

import brunofujisaki.loja_online.model.Produto;

import java.math.BigDecimal;

public record ProdutoDetalheDTO(
        String nome,
        BigDecimal preco,
        Integer quantidade
) {
    public ProdutoDetalheDTO(Produto produto) {
        this(produto.getNome(), produto.getPreco(), produto.getQuantidade());
    }
}
