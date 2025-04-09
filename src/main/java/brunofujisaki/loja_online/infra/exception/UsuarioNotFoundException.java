package brunofujisaki.loja_online.infra.exception;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
