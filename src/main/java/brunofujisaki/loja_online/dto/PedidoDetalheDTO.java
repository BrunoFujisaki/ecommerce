package brunofujisaki.loja_online.dto;

import brunofujisaki.loja_online.model.Endereco;
import brunofujisaki.loja_online.model.Pedido;
import brunofujisaki.loja_online.model.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.UUID;

public record PedidoDetalheDTO(
        UUID usuarioId,
        Endereco endereco,
        BigDecimal valorTotal,
        StatusPedido status

) {
    public PedidoDetalheDTO(Pedido pedido) {
        this(pedido.getUsuario().getId(), pedido.getEndereco(), pedido.getValorTotal(), pedido.getStatusPedido());
    }
}
