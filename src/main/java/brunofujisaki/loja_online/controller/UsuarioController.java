package brunofujisaki.loja_online.controller;

import brunofujisaki.loja_online.dto.*;
import brunofujisaki.loja_online.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;


    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioDetalheDTO> cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDTO dto) {
        var usuarioDetalheDTO = service.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDetalheDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenJwtDTO> logarUsuario(@RequestBody @Valid UsuarioLoginDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.logarUsuario(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDetalheDTO>> listarUsuarios() {
        var usuariosDetalheDTO = service.findAllUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuariosDetalheDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDetalheDTO> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioAtualizacaoDTO dto) {
        var usuarioDetalheDTO = service.atualizarUsuario(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDetalheDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        service.deletarUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
