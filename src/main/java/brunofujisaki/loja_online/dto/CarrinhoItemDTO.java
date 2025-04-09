package brunofujisaki.loja_online.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CarrinhoItemDTO(
    @NotNull
    UUID usuarioId,

    @NotNull
    Long produtoId,

    @Positive
    Integer quantidade
) {
}
