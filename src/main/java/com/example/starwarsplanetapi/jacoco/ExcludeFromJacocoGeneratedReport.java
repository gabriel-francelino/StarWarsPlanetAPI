package com.example.starwarsplanetapi.jacoco;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // será aplicada em tempo de execução
@Target(ElementType.METHOD) // será aplicada em métodos que tiver essa anotação
public @interface ExcludeFromJacocoGeneratedReport {
    /*
    * Criando anotação para o jacoco descosiderar o teste em um método especifico
    */
}
