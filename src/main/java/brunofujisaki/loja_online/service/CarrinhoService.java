package brunofujisaki.loja_online.service;

import brunofujisaki.loja_online.dto.CarrinhoItemDTO;
import brunofujisaki.loja_online.dto.CarrinhoItemDetalheDTO;
import brunofujisaki.loja_online.dto.CarrinhoItemResumoDTO;
import brunofujisaki.loja_online.infra.exception.CarrinhoNotFoundException;
import brunofujisaki.loja_online.infra.exception.UsuarioNotFoundException;
import brunofujisaki.loja_online.model.Carrinho;
import brunofujisaki.loja_online.model.CarrinhoItem;
import brunofujisaki.loja_online.model.CarrinhoItemId;
import brunofujisaki.loja_online.repository.CarrinhoItemRepository;
import brunofujisaki.loja_online.repository.CarrinhoRepository;
import brunofujisaki.loja_online.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

//REGRAS DE NEGÓCIO:

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CarrinhoItemRepository carrinhoItemRepository;
    private final ProdutoService produtoService;

    @Transactional
    public CarrinhoItemResumoDTO adicionarItemAoCarrinho(CarrinhoItemDTO dto) {

        //Busca usuario no banco
        var usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        //Busca carrinho no banco, senao encontrar cria um
        var carrinho = carrinhoRepository.findByUsuarioId(usuario.getId())
                .orElseGet(() -> {
                    var newCarrinho = new Carrinho();
                    newCarrinho.setUsuario(usuario);
                    return carrinhoRepository.save(newCarrinho);
                });

        //Busca produto no banco e atualiza seu estoque
        var produto = produtoService.atualizarEstoque(dto.produtoId(), dto.quantidade(), false);

        //Cria uma chave composta pra CarrinhoItem
        var carrinhoItemId = new CarrinhoItemId(carrinho.getId(), produto.getId());

        //Busca carrinho no banco pra verificar se o produto ja esta la
        var carrinhoItemOpt = carrinhoItemRepository.findById(carrinhoItemId);

        //Se estiver presente atualiza a quantidade e o valor total do carrinho
        if (carrinhoItemOpt.isPresent()) {
            var carrinhoItemExistente = carrinhoItemOpt.get();
            carrinhoItemExistente.setQuantidade(carrinhoItemExistente.getQuantidade() + dto.quantidade());
            return new CarrinhoItemResumoDTO(carrinho.getId(), produto.getNome(), carrinhoItemExistente.getQuantidade(), produto.getPreco());
        }

        //Senao salva no repositorio
        var newCarrinhoitem = new CarrinhoItem(carrinhoItemId, carrinho, produto, produto.getPreco(), dto.quantidade());
        carrinho.getCarrinhoItemList().add(newCarrinhoitem);
        return new CarrinhoItemResumoDTO(carrinho.getId(), produto.getNome(), newCarrinhoitem.getQuantidade(), produto.getPreco());
    }

    //VERIFICAR LOGICA MELHORAR CODIGO
    @Transactional
    public void removerQuantidadeDoItem(UUID carrinhoId, Long produtoId) {

        var carrinhoItemId = new CarrinhoItemId(carrinhoId, produtoId);
        var carrinhoItem = carrinhoItemRepository.findById(carrinhoItemId).orElseThrow(() -> new CarrinhoNotFoundException("Item do carrinho não encontrado"));

        if (carrinhoItem.getQuantidade() <= 0) {
            carrinhoItemRepository.delete(carrinhoItem);
        } else {
            carrinhoItem.setQuantidade(carrinhoItem.getQuantidade() - 1);
            produtoService.atualizarEstoque(produtoId, 1, true);
        }

    }

    public Page<CarrinhoItemDetalheDTO> findAllItensCarrinho(Pageable pageable) {
        return carrinhoItemRepository.findAll(pageable).map(CarrinhoItemDetalheDTO::new);
    }
}
