package brunofujisaki.loja_online.dto;

import java.util.UUID;

public record PedidoCadastroDTO(
        UUID usuarioId,
        String cep
) {
}
