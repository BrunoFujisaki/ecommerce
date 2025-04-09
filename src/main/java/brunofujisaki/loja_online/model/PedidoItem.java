package brunofujisaki.loja_online.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido_itens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoItem {
    @EmbeddedId
    private PedidoItemId id;

    @ManyToOne
    @JoinColumn(name = "pedidoId", insertable = false, updatable = false)
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produtoId", insertable = false, updatable = false)
    private Produto produto;

    private BigDecimal preco;

    private Integer quantidade;
}
