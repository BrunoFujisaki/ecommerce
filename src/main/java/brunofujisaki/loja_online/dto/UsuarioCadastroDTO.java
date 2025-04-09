package brunofujisaki.loja_online.dto;

import brunofujisaki.loja_online.model.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioCadastroDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(
                regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$",
                message = "Formato do cpf inválido"
        )
        String cpf,

        String senha,

        UserRole role

) {
}
