package edu.javacourse.first.checkers;

import edu.javacourse.first.answer.CheckerAnswer;
import edu.javacourse.first.checkers.answer.BasicCheckerAnswer;
import edu.javacourse.first.domain.Person;
import edu.javacourse.first.exception.SendGetDataException;

public class ZagsChecker extends BasicChecker {

    private Person husband;
    private Person wife;
    private Person child;

    public ZagsChecker(String host, int port, String login, String password) {
        super(host, port, login, password);
    }

    public void setParameters(Person husband, Person wife, Person child) {
        this.husband = husband;
        this.wife = wife;
        this.child = child;
    }

    protected CheckerAnswer sendAndGetData() throws SendGetDataException {
        if (child == null) {
            return checkWedding();
        } else {
            return checkChild();
        }
    }

    private CheckerAnswer checkWedding() {
        System.out.println("ZagsChecker.checkWedding");
        return new BasicCheckerAnswer(true, "ZagsChecker.checkWedding()");
    }

    private CheckerAnswer checkChild() {
        System.out.println("ZagsChecker.checkChild: " + child.getGivenName());
        return new BasicCheckerAnswer(true, "ZagsChecker.checkChild(): " + child.getGivenName());
    }

}
