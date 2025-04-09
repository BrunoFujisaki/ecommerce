package brunofujisaki.loja_online.model;

import brunofujisaki.loja_online.model.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PedidoItem> pedidoItemList = new ArrayList<>();

    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Setter
    private StatusPedido statusPedido;

    public Pedido(Usuario usuario, Endereco endereco) {
        this.usuario = usuario;
        this.endereco = endereco;
        this.statusPedido = StatusPedido.PENDENTE;
    }

    public void calcValorTotal(BigDecimal preco, Integer quantidade) {
        this.valorTotal = this.valorTotal.add(preco.multiply(BigDecimal.valueOf(quantidade)));
    }
}
