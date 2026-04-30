package fiap.com.br.graus.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoMovimentacaoValidator.class)
public @interface TipoMovimentacaoValida {

    String message() default "Tipo de movimentação deve ser ENTRADA ou SAIDA";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}