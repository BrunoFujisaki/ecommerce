package brunofujisaki.loja_online.dto;

import brunofujisaki.loja_online.model.CarrinhoItem;

import java.math.BigDecimal;
import java.util.UUID;

public record CarrinhoItemDetalheDTO(
        UUID carrinhoId,
        String produtoNome,
        BigDecimal preco,
        Integer quantidade
) {
    public CarrinhoItemDetalheDTO(CarrinhoItem carrinhoItem) {
        this(carrinhoItem.getCarrinho().getId(), carrinhoItem.getProduto().getNome(),
                carrinhoItem.getPreco(), carrinhoItem.getQuantidade());
    }
}
