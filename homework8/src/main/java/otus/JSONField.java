package otus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Светлана on 13.06.2017.
 */


@Retention(RetentionPolicy.RUNTIME)
public @interface JSONField {
    boolean optional() default false;
}