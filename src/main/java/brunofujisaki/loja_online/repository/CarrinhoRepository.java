package brunofujisaki.loja_online.repository;

import brunofujisaki.loja_online.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {
    boolean existsByUsuarioId(UUID id);

    Optional<Carrinho> findByUsuarioId(UUID id);
}
