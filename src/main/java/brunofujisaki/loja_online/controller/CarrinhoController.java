package brunofujisaki.loja_online.controller;

import brunofujisaki.loja_online.dto.CarrinhoItemDTO;
import brunofujisaki.loja_online.dto.CarrinhoItemDetalheDTO;
import brunofujisaki.loja_online.dto.CarrinhoItemResumoDTO;
import brunofujisaki.loja_online.service.CarrinhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping
    public ResponseEntity<CarrinhoItemResumoDTO> adicionarItemAoCarrinho(@RequestBody @Valid CarrinhoItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoService.adicionarItemAoCarrinho(dto));
    }

    @GetMapping
    public ResponseEntity<Page<CarrinhoItemDetalheDTO>> listarItensCarrinho(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findAllItensCarrinho(pageable));
    }

    @DeleteMapping("/{carrinhoId}/produto/{produtoId}")
    public ResponseEntity<Void> removerQuantidadeDoItem(@PathVariable UUID carrinhoId, @PathVariable Long produtoId) {
        carrinhoService.removerQuantidadeDoItem(carrinhoId, produtoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
