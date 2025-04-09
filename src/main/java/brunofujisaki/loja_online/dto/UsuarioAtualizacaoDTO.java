package brunofujisaki.loja_online.dto;

import jakarta.validation.constraints.Pattern;

public record UsuarioAtualizacaoDTO(
        String nome,
        @Pattern(
                regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}",
                message = "Formato cpf inválido"
        )
        String cpf
) {
}
