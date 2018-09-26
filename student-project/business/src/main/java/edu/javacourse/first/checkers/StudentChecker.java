package edu.javacourse.first.checkers;

import edu.javacourse.first.answer.CheckerAnswer;
import edu.javacourse.first.checkers.answer.BasicCheckerAnswer;
import edu.javacourse.first.domain.Person;
import edu.javacourse.first.exception.SendGetDataException;

public class StudentChecker extends BasicChecker {

    private Person person;

    public StudentChecker(String host, int port, String login, String password) {
        super(host, port, login, password);
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    protected CheckerAnswer sendAndGetData() throws SendGetDataException {
        System.out.println("StudentChecker.sendAndGetData(): " + person.getGivenName());
        return new BasicCheckerAnswer(true, "StudentChecker: " + person.getGivenName());
    }

}
