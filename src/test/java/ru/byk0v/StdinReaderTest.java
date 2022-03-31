package ru.byk0v;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.byk0v.exceptions.ParseLogException;
import ru.byk0v.intputable.InputDto;
import ru.byk0v.intputable.StdinReader;

import java.util.Optional;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.withPrecision;

@RunWith(MockitoJUnitRunner.class)
public class StdinReaderTest {

    @Mock
    private Scanner scanner;

    @InjectMocks
    private StdinReader reader;

    @Test
    public void inputReadTest() throws ParseLogException {
        // Give
        Optional<InputDto> input;

        Mockito.when(scanner.hasNextLine()).thenReturn(true);
        Mockito.when(scanner.nextLine()).thenReturn(
                "192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\"" +
                " 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0"
        );

        // When
        input = reader.read();

        // Then
        Assertions.assertThat(input.isPresent()).isTrue();
        Assertions.assertThat(input.get().getLocalTime()).isEqualTo("16:47:02");
        Assertions.assertThat(input.get().getCodeRequest()).isEqualTo(200);
        Assertions.assertThat(input.get().getTimeRequest()).isEqualTo(44.51, withPrecision(2d));
    }

    @Test(expected = ParseLogException.class)
    public void readingWrongInputTest() throws ParseLogException {
        // Give
        Optional<InputDto> input;

        Mockito.when(scanner.hasNextLine()).thenReturn(true);
        Mockito.when(scanner.nextLine()).thenReturn(
                "192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT " +
                        "/rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\"" +
                        " 0k 2 44.510983 \"-\" \"@list-item-updater\" prio:0"
        );

        // When
        input = reader.read();
    }
}
