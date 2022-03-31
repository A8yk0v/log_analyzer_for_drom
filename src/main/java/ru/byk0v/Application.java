package ru.byk0v;

import ru.byk0v.exceptions.ParseLogException;
import ru.byk0v.intputable.InputAble;
import ru.byk0v.intputable.InputDto;
import ru.byk0v.outputAble.OutputAble;
import ru.byk0v.outputAble.OutputDto;
import ru.byk0v.parseparams.ParamsDto;
import ru.byk0v.parseparams.ParseParams;

import java.util.Optional;

/**
 * Основной класс приложения, в котором происходит чтение параметров
 * и выполняется основной цикл приложения - построчная обработка лог данных
 */
public class Application {

    private int logCount = 0;
    private int errorCount = 0;
    private OutputDto period = null;
    private ParamsDto params;

    private ParseParams parseParams;
    private InputAble inputAble;
    private OutputAble outputAble;

    public Application(ParseParams parseParams, InputAble inputAble, OutputAble outputAble) {
        this.parseParams = parseParams;
        this.inputAble = inputAble;
        this.outputAble = outputAble;
    }

    public void run(String[] args) {
        try {
            params = parseParams.parse(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Выход...");
            System.exit(1);
        }

        while (inputAble.hasRead()) {
            Optional<InputDto> inData;
            try {
                inData = inputAble.read();
            } catch (ParseLogException e) {
                System.out.println("Ошибка при разборе строки лог файла - строка будет пропущена: "
                                                                                    + e.getMessage());
                continue;
            }

            // ОСНОВНАЯ ЛОГИКА ПРИЛОЖЕНИЯ
            if (inData.isPresent()) {
                dataHandling(inData.get());
            }

        }
        // Могли выйти из цикла и не отправить последний период, исправляемся
        if (period != null) {
            outputAble.print(period);
            period = null;
        }
    }

    private void dataHandling(InputDto data) {
        logCount++;
        // Является ли запрос отказом? Если да, то увеличиваем errorCount
        if (500 <= data.getCodeRequest() && data.getCodeRequest() < 600) {
            errorCount++;
        } else if (params.getRequestTime() != null && data.getTimeRequest() > params.getRequestTime()) {
            errorCount++;
        }

        double currentAvailabilityLevel = 100 - (double) errorCount / logCount * 100;

        // Формируем выходные данные
        if (currentAvailabilityLevel < params.getAvailability()) {
            // Период еще не создавался? То, создаем
            if (period == null) {
                period = new OutputDto();
                period.setStart(data.getLocalTime());
                period.setEnd(data.getLocalTime());
                period.setAvailability(currentAvailabilityLevel);
            } // иначе, если период с низкой доступность уже открыть, обновляем если нужно
            else {
                if (currentAvailabilityLevel < period.getAvailability()) {
                    period.setAvailability(currentAvailabilityLevel);
                }
                period.setEnd(data.getLocalTime());
            }
        } // стоит проверить, нужно ли закрыть период и вывести данные
        else {
            if (period != null) {
                outputAble.print(period);
                period = null;
            }
        }
    }
}
