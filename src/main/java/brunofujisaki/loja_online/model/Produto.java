package brunofujisaki.loja_online.model;

import brunofujisaki.loja_online.dto.ProdutoCadastroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Getter
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal preco;

    @Setter
    private Integer quantidade;

    public Produto(ProdutoCadastroDTO dto) {
        this.nome = dto.nome();
        this.preco = dto.preco();
        this.quantidade = dto.quantidade();
    }
}
