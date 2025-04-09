package brunofujisaki.loja_online.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CarrinhoItemResumoDTO(
        UUID carrinhoId,
        String produtoNome,
        Integer quantidade,
        BigDecimal precoUnitario
) {
}
