package brunofujisaki.loja_online.repository;

import brunofujisaki.loja_online.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
