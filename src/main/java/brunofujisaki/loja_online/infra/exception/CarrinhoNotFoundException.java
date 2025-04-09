package brunofujisaki.loja_online.infra.exception;

public class CarrinhoNotFoundException extends RuntimeException {
    public CarrinhoNotFoundException(String message) {
        super(message);
    }
}
