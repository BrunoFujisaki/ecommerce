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
public class CarrinhoItemId implements Serializable {

    private UUID carrinhoId;

    private Long produtoId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoItemId that = (CarrinhoItemId) o;
        return Objects.equals(carrinhoId, that.carrinhoId) && Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrinhoId, produtoId);
    }
}
