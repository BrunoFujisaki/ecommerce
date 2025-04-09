package brunofujisaki.loja_online.infra.exception;

public class TokenJwtException extends RuntimeException {
    public TokenJwtException(String message) {
        super(message);
    }
}
