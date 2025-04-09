package brunofujisaki.loja_online.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoCadastroDTO(
        @NotBlank
        String nome,

        @NotNull
        @Positive
        BigDecimal preco,

        @NotNull
        @Positive
        Integer quantidade
) {
}
