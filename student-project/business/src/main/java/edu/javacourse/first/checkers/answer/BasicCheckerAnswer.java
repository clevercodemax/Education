package edu.javacourse.first.checkers.answer;

import edu.javacourse.first.answer.CheckerAnswer;

public class BasicCheckerAnswer implements CheckerAnswer {

    private boolean result;
    private String message;

    public BasicCheckerAnswer(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    @Override
    public boolean getResult() {
        return result;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
