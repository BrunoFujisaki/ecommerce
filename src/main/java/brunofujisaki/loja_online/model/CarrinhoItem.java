package brunofujisaki.loja_online.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "carrinho_itens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CarrinhoItem {

    @EmbeddedId
    private CarrinhoItemId id;

    @ManyToOne
    @JoinColumn(name = "carrinhoId", insertable=false, updatable=false)
    @JsonBackReference
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "produtoId", insertable=false, updatable=false)
    private Produto produto;

    private BigDecimal preco;

    @Setter
    private Integer quantidade;

}
