package brunofujisaki.loja_online.repository;

import brunofujisaki.loja_online.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
