package brunofujisaki.loja_online.infra.security;

import brunofujisaki.loja_online.dto.TokenJwtDTO;
import brunofujisaki.loja_online.infra.exception.TokenJwtException;
import brunofujisaki.loja_online.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.token.secret}")
    private String secret;

    public TokenJwtDTO gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return new TokenJwtDTO(JWT.create()
                    .withIssuer("lojaonline")
                    .withSubject(usuario.getNome())
                    .withExpiresAt(genExpirateDateTime())
                    .sign(algorithm));
        } catch (JWTCreationException exception){
            throw new TokenJwtException("Falha ao gerar token");
        }
    }

    private Instant genExpirateDateTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("lojaonline")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenJwtException("Token inválido ou expirado");
        }
    }
}
