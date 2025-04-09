package brunofujisaki.loja_online.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationDataErrors>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.stream().map(ValidationDataErrors::new).toList());
    }

    @ExceptionHandler(ViaCepClientException.class)
    public ResponseEntity<RestErrorMessage> handleViaCepClientException(ViaCepClientException ex) {
        var restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler({
            UsuarioNotFoundException.class,
            ProdutoNotFoundException.class,
            CarrinhoNotFoundException.class
    })
    public ResponseEntity<RestErrorMessage> handleNotFoundException(RuntimeException ex) {
        var restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler({
            UsuarioException.class,
            ProdutoException.class,
            CarrinhoException.class,
            PedidoException.class
    })
    public ResponseEntity<RestErrorMessage> handleUnprocessableException(RuntimeException ex) {
        var restErrorMessage = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(restErrorMessage);
    }

    private record RestErrorMessage(HttpStatus status, String message){}

    private record ValidationDataErrors(
            String field,
            String message
    ) { private ValidationDataErrors(FieldError fe) {this(fe.getField(), fe.getDefaultMessage());}}
}
