package brunofujisaki.loja_online.dto;

import brunofujisaki.loja_online.model.Usuario;

public record UsuarioDetalheDTO(
        String nome,
        String cpf
) {
    public UsuarioDetalheDTO(Usuario usuario) {
        this(usuario.getNome(), usuario.getCpf());
    }
}
