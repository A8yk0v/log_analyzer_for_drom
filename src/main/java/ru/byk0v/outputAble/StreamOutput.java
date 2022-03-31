package ru.byk0v.outputAble;

public class StreamOutput implements OutputAble {

    @Override
    public void print(OutputDto outputDto) {
        System.out.printf("%s    %s    %.1f\n"
                , outputDto.getStart().toString(), outputDto.getEnd().toString(), outputDto.getAvailability());
    }
}