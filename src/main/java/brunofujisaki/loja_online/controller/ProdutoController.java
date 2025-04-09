package brunofujisaki.loja_online.controller;

import brunofujisaki.loja_online.dto.ProdutoCadastroDTO;
import brunofujisaki.loja_online.dto.ProdutoDetalheDTO;
import brunofujisaki.loja_online.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDetalheDTO> cadastrarProduto(@RequestBody @Valid ProdutoCadastroDTO dto) {
        var produtoDetalheDTO = service.cadastrarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDetalheDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDetalheDTO>> listarProdutos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllProdutos(pageable));
    }
}
