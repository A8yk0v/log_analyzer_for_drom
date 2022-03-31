package ru.byk0v.intputable;

import ru.byk0v.exceptions.ParseLogException;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

public class StdinReader implements InputAble {

    Scanner scanner = new Scanner(System.in);

    @Override
    public boolean hasRead() {
        return scanner.hasNextLine();
    }

    @Override
    public Optional<InputDto> read() throws ParseLogException {
        InputDto inputDto = null;

        try {
            if (scanner.hasNextLine()) {
                String str = scanner.nextLine();

                String[] split = str.split(" ");
                LocalTime localTime = LocalTime.parse(split[3].substring(12));
                int codeRequest = Integer.parseInt(split[8]);
                double timeRequest = Double.parseDouble(split[10]);

                inputDto = new InputDto(localTime, codeRequest, timeRequest);
            }
        } catch (Exception e) {
            throw new ParseLogException("Ошибка при разборе строки лог-файла "
                    + e.getMessage(), e);
        }

        return Optional.of(inputDto);
    }
}
