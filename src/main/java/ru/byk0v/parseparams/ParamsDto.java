package ru.byk0v.parseparams;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParamsDto {
    /*
    Минимально допустимый уровень доступности (проценты)
    Например: "99.9"
     */
    private Double availability = 100d;

    /*
    Приемлемое время ответа (миллисекунды)
    Например, "45").
     */
    private Integer requestTime = null;
}
