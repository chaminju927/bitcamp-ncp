package bitcamp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)  //파라미터에만 붙일 수 있는 애노테이션, 다수 지정도 가능
public @interface RequestParam {
  String value() default "";
}
