package brunofujisaki.loja_online.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoItemId implements Serializable {

    private UUID pedidoId;

    private Long produtoId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PedidoItemId that = (PedidoItemId) o;
        return Objects.equals(pedidoId, that.pedidoId) && Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, produtoId);
    }
}
