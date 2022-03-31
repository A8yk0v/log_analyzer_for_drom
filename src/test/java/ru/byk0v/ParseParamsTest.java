package ru.byk0v;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import ru.byk0v.exceptions.ParseParamsException;
import ru.byk0v.parseparams.ParamsDto;
import ru.byk0v.parseparams.ParseParams;

public class ParseParamsTest {

    private ParseParams parseParams = new ParseParams();

    @Test
    public void parsingParametersUAndT() throws ParseParamsException {
        // give
        String[] args = "-u 73 -t 44".split(" ");

        // When
        ParamsDto params = parseParams.parse(args);

        // Then
        Assertions.assertThat(params.getAvailability()).isEqualTo(73.0);
        Assertions.assertThat(params.getRequestTime()).isEqualTo(44);
    }

    @Test
    public void parsingParameterU() throws ParseParamsException {
        String[] args = "-u 73".split(" ");

        ParamsDto params = parseParams.parse(args);

        Assertions.assertThat(params.getAvailability()).isEqualTo(73.0);
        Assertions.assertThat(params.getRequestTime()).isNull();
    }

    @Test
    public void parsingParameterT() throws ParseParamsException {
        String[] args = "-t 33".split(" ");

        ParamsDto params = parseParams.parse(args);

        Assertions.assertThat(params.getAvailability()).isEqualTo(100);
        Assertions.assertThat(params.getRequestTime()).isEqualTo(33);
    }

    @Test
    public void parsingWithoutParameters() throws ParseParamsException {
        String[] args = new String[0];

        ParamsDto params = parseParams.parse(args);

        Assertions.assertThat(params.getAvailability()).isEqualTo(100);
        Assertions.assertThat(params.getRequestTime()).isNull();
    }


    @Test(expected = ParseParamsException.class)
    public void parsingWrongParameter() throws ParseParamsException {
        String[] args = "-u 73 -t 4g".split(" ");

        ParamsDto params = parseParams.parse(args);
    }

    @Test(expected = ParseParamsException.class)
    public void parsingNonExistentParameter() throws ParseParamsException {
        String[] args = "-g 73".split(" ");

        ParamsDto params = parseParams.parse(args);
    }
}
