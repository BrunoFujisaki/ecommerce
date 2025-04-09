package brunofujisaki.loja_online.repository;

import brunofujisaki.loja_online.model.CarrinhoItem;
import brunofujisaki.loja_online.model.CarrinhoItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, CarrinhoItemId> {
}
