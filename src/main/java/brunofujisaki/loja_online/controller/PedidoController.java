package brunofujisaki.loja_online.controller;

import brunofujisaki.loja_online.dto.PedidoCadastroDTO;
import brunofujisaki.loja_online.dto.PedidoDetalheDTO;
import brunofujisaki.loja_online.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDetalheDTO> fazerPedido(@RequestBody @Valid PedidoCadastroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.fazerPedido(dto));
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<PedidoDetalheDTO> pagarPedido(@PathVariable UUID pedidoId) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.pagarPedido(pedidoId));
    }
}
