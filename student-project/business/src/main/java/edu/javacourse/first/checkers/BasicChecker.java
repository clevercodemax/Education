package edu.javacourse.first.checkers;

import edu.javacourse.first.answer.CheckerAnswer;
import edu.javacourse.first.exception.CheckException;
import edu.javacourse.first.exception.ConnectException;
import edu.javacourse.first.exception.SendGetDataException;

import java.io.IOException;
import java.net.Socket;

public abstract class BasicChecker {

    protected String host;
    protected int port;
    protected String login;
    protected String password;

    protected Socket socket;

    public BasicChecker(String host, int port, String login, String password) {
        this.host = host;
        this.port = port;
        this.login = login;
        this.password = password;
    }

    public CheckerAnswer check() throws CheckException {
        try {
            connect();
            try {
                CheckerAnswer result = sendAndGetData();
                return result;
            } catch (SendGetDataException ex) {
                throw new CheckException(ex.getMessage(), ex);
            } finally {
                disconnect();
            }
        } catch (ConnectException ex) {
            throw new CheckException(ex.getMessage(), ex);
        }
    }

    private void connect() throws ConnectException {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectException(e.getMessage(), e);
        }
    }


    private void disconnect() throws ConnectException {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectException(e.getMessage(), e);
        }
    }

    protected abstract CheckerAnswer sendAndGetData() throws SendGetDataException;

}
