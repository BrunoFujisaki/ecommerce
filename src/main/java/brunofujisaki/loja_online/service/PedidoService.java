package brunofujisaki.loja_online.service;

import brunofujisaki.loja_online.dto.PedidoCadastroDTO;
import brunofujisaki.loja_online.dto.PedidoDetalheDTO;
import brunofujisaki.loja_online.infra.exception.CarrinhoException;
import brunofujisaki.loja_online.infra.exception.CarrinhoNotFoundException;
import brunofujisaki.loja_online.infra.exception.PedidoException;
import brunofujisaki.loja_online.infra.exception.UsuarioNotFoundException;
import brunofujisaki.loja_online.model.Pedido;
import brunofujisaki.loja_online.model.PedidoItem;
import brunofujisaki.loja_online.model.PedidoItemId;
import brunofujisaki.loja_online.model.enums.StatusPedido;
import brunofujisaki.loja_online.repository.CarrinhoRepository;
import brunofujisaki.loja_online.repository.PedidoRepository;
import brunofujisaki.loja_online.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

//REGRAS DE NEGÓCIO:

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoService enderecoService;

    @Transactional
    public PedidoDetalheDTO fazerPedido(PedidoCadastroDTO dto) {

        //Buscar usuario no repositorio
        var usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        //Buscar carrinho no repositorio
        var carrinho = carrinhoRepository.findByUsuarioId(usuario.getId()).orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado"));

        if (carrinho.getCarrinhoItemList().isEmpty()) throw new CarrinhoException("Carrinho vazio");

        //Cadastrar endereco atraves da api via cep
        var endereco = enderecoService.cadastrarEndereco(dto.cep());

        //Pedido
        var pedido = new Pedido(usuario, endereco);
        pedidoRepository.save(pedido);

        //Salvar os itens do carrinho para os itens do pedido
        carrinho.getCarrinhoItemList().forEach(carrinhoItem -> {
            var pedidoItemId = new PedidoItemId(pedido.getId(), carrinhoItem.getProduto().getId());
            pedido.getPedidoItemList().add(new PedidoItem(pedidoItemId, pedido, carrinhoItem.getProduto(), carrinhoItem.getPreco(), carrinhoItem.getQuantidade()));
        });

        //Calcular o valor total do pedido
        pedido.getPedidoItemList().forEach(pedidoItem -> pedido.calcValorTotal(pedidoItem.getPreco(), pedidoItem.getQuantidade()));

        //Limpa os itens do carrinho
        carrinho.getCarrinhoItemList().clear();
        carrinhoRepository.save(carrinho);

        return new PedidoDetalheDTO(pedido);
    }

    @Transactional
    public PedidoDetalheDTO pagarPedido(UUID pedidoId) {
        //IMPLEMENTAR UMA CLASSE PAGAMENTO Q TEM SALDO DO USUARIO PRA PAGAR
        var pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE){
            throw new PedidoException("Não é possível pagar pedidos que não estejam pendentes");
        }
        pedido.setStatusPedido(StatusPedido.PAGO);

        return new PedidoDetalheDTO(pedidoRepository.save(pedido));
    }
}
