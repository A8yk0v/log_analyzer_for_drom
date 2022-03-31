package ru.byk0v.parseparams;

import ru.byk0v.exceptions.ParseParamsException;

public class ParseParams {

    public ParamsDto parse(String[] args) throws ParseParamsException {
        ParamsDto paramsDto = new ParamsDto();

        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].charAt(0) == '-') {
                    if (args[i].charAt(1) == 'u') {
                        paramsDto.setAvailability(Double.parseDouble(args[++i]));
                    } else if (args[i].charAt(1) == 't') {
                        paramsDto.setRequestTime(Integer.parseInt(args[++i]));
                    } else {
                        throw new ParseParamsException("Неизвестный параметр: " + args[i]);
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new ParseParamsException("Ошибка при разборе параметра: " + e.getMessage());
        }

        return paramsDto;
    }
}
