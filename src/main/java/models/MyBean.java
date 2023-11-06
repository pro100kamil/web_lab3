package models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})  // применяется только к классам, интерфейсам
public @interface MyBean {
}
