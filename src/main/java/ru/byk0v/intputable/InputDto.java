package ru.byk0v.intputable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class InputDto {
    /*
    Время из лог данных
     */
    private LocalTime localTime;

    /*
    Код ответа
     */
    private int codeRequest;

    /*
    Время затраченное на ответ
     */
    private double timeRequest;
}
