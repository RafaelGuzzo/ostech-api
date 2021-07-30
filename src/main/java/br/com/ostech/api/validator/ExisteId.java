package br.com.ostech.api.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { ExisteIdValidator.class })
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface ExisteId {

	String message() default "O id informado não existe no banco de dados";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String atributo();

	Class<?> entidade();

}
