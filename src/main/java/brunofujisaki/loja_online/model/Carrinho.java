package brunofujisaki.loja_online.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carrinhos")
@Getter
@NoArgsConstructor
public class Carrinho {
    @Id
    @UuidGenerator
    private UUID id;

    @Setter
    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CarrinhoItem> carrinhoItemList = new ArrayList<>();
}
