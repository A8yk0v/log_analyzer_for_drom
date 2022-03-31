package ru.byk0v.outputAble;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class OutputDto {
    /*
    Время начало периода
     */
    private LocalTime start;

    /*
    Время окончания периода
     */
    private LocalTime end;

    /*
    Минимальный уровень доступности в данном периоде
     */
    private double availability;
}
