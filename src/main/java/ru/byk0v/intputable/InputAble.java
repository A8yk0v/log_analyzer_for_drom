package ru.byk0v.intputable;

import ru.byk0v.exceptions.ParseLogException;

import java.util.Optional;

public interface InputAble {

    boolean hasRead();

    Optional<InputDto> read() throws ParseLogException;
}
