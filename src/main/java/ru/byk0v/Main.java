package ru.byk0v;

import ru.byk0v.intputable.InputAble;
import ru.byk0v.intputable.StdinReader;
import ru.byk0v.outputAble.OutputAble;
import ru.byk0v.outputAble.StreamOutput;
import ru.byk0v.parseparams.ParseParams;

public class Main {

    public static void main(String[] args) {

        ParseParams parseParams = new ParseParams();
        InputAble inputAble = new StdinReader();
        OutputAble outputAble = new StreamOutput();

        new Application(parseParams, inputAble, outputAble).run(args);
    }
}
