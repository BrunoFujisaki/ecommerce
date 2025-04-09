package brunofujisaki.loja_online.repository;

import brunofujisaki.loja_online.model.PedidoItem;
import brunofujisaki.loja_online.model.PedidoItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, PedidoItemId> {
}
