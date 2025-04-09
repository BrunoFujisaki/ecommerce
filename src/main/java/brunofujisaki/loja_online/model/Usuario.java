package brunofujisaki.loja_online.model;

import brunofujisaki.loja_online.dto.UsuarioAtualizacaoDTO;
import brunofujisaki.loja_online.dto.UsuarioCadastroDTO;
import brunofujisaki.loja_online.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @UuidGenerator
    private UUID id;

    private String nome;

    private String cpf;

    private String senha;

    private UserRole role;

    public Usuario(UsuarioCadastroDTO dto, String senhaCriptografada) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.senha = senhaCriptografada;
        this.role = dto.role();
    }

    public void atualizar(UsuarioAtualizacaoDTO dto) {
        if (dto.nome() != null && !dto.nome().isBlank()) {
            this.nome = dto.nome();
        }

        if (dto.cpf() != null && !dto.cpf().isBlank()) {
            this.cpf = dto.cpf();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER"));
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
