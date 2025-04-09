package brunofujisaki.loja_online.service;

import brunofujisaki.loja_online.dto.*;
import brunofujisaki.loja_online.infra.exception.UsuarioException;
import brunofujisaki.loja_online.infra.exception.UsuarioNotFoundException;
import brunofujisaki.loja_online.infra.security.TokenService;
import brunofujisaki.loja_online.model.Usuario;
import brunofujisaki.loja_online.repository.CarrinhoRepository;
import brunofujisaki.loja_online.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//REGRAS DE NEGÓCIO:

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @Transactional
    public UsuarioDetalheDTO cadastrarUsuario(UsuarioCadastroDTO dto) {
        var senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        return new UsuarioDetalheDTO(usuarioRepository.save(new Usuario(dto, senhaCriptografada)));
    }

    public TokenJwtDTO logarUsuario(UsuarioLoginDTO dto) {
        var userToken = new UsernamePasswordAuthenticationToken(dto.nome(), dto.senha());
        var auth = manager.authenticate(userToken);
        return tokenService.gerarToken((Usuario) auth.getPrincipal());
    }

    public List<UsuarioDetalheDTO> findAllUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioDetalheDTO::new).toList();
    }

    @Transactional
    public UsuarioDetalheDTO atualizarUsuario(UUID id, UsuarioAtualizacaoDTO dto) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));
        usuario.atualizar(dto);
        return new UsuarioDetalheDTO(usuario.getNome(), usuario.getCpf());
    }

    @Transactional
    public void deletarUsuario(UUID id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));
        var possuiCarrinho = carrinhoRepository.existsByUsuarioId(usuario.getId());

        if (possuiCarrinho) throw new UsuarioException("Exclusão não permitida: o usuário ainda possui um carrinho associado.");

        usuarioRepository.delete(usuario);
    }


}
