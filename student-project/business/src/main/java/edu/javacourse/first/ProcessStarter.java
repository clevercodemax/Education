package edu.javacourse.first;

import edu.javacourse.first.answer.CheckerAnswer;
import edu.javacourse.first.checkers.GrnChecker;
import edu.javacourse.first.checkers.StudentChecker;
import edu.javacourse.first.checkers.ZagsChecker;
import edu.javacourse.first.db.FactoryDataSource;
import edu.javacourse.first.db.StudentOrderDataSource;
import edu.javacourse.first.domain.PersonChild;
import edu.javacourse.first.domain.StudentOrder;
import edu.javacourse.first.exception.CheckException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProcessStarter {

    public static void main(String[] args) {

        ProcessStarter t = new ProcessStarter();
        t.processList();
    }

    public void processList() {

        StudentOrderDataSource ds = FactoryDataSource.getDataSource();
        ds.addStudentOrder(null);

        List<StudentOrder> orderList = ds.getStudentOrders();
//
//        for (StudentOrder so : orderList) {
//            processStudentOrder(so);
//        }

    }

    private void processStudentOrder(StudentOrder so) {

        List<CheckerAnswer> answers = new ArrayList<>();

        try {
            answers.addAll(checkGrn(so));
//            answers.addAll(checkZags(so));
//            answers.addAll(checkStudent(so));
        } catch (CheckException ex) {
            // TODO Сделать обработку ошибки - что-то записать в базу
            return;
        }

        ApproveManager am = new ApproveManager();
        for (CheckerAnswer ca : answers) {
            if (!ca.getResult()) {
                am.denyOrder(so, answers);
                return;
            }
        }
        am.approveOrder(so, answers);

    }

    private List<CheckerAnswer> checkGrn(StudentOrder so) throws CheckException {

        List<CheckerAnswer> answers = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(3);
        List<Future<CheckerAnswer>> result = new ArrayList<>();

        GrnChecker grnH = new GrnChecker(so.getHusband());
        result.add(es.submit(grnH));
        GrnChecker grnW = new GrnChecker(so.getWife());
        result.add(es.submit(grnW));
        for (PersonChild pc : so.getChildren()) {
            GrnChecker grnC = new GrnChecker(pc);
            result.add(es.submit(grnC));
        }

        for (Future<CheckerAnswer> f : result) {
            try {
                CheckerAnswer answer = f.get();
                answers.add(answer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        es.shutdown();
        System.out.println("GRN Checker is finished");
        return answers;
    }

    private List<CheckerAnswer> checkZags(StudentOrder so) throws CheckException {

        List<CheckerAnswer> answers = new ArrayList<>();
        ZagsChecker zc = new ZagsChecker("1", 2, "3", "4");

        zc.setParameters(so.getHusband(), so.getWife(), null);
        answers.add(zc.check());
        for (PersonChild pc : so.getChildren()) {
            zc.setParameters(so.getHusband(), so.getWife(), pc);
            answers.add(zc.check());
        }
        return answers;
    }

    private List<CheckerAnswer> checkStudent(StudentOrder so) throws CheckException {

        List<CheckerAnswer> answers = new ArrayList<>();
        StudentChecker sc = new StudentChecker("1", 2, "3", "4");

        sc.setPerson(so.getHusband());
        answers.add(sc.check());
        sc.setPerson(so.getWife());
        answers.add(sc.check());

        return answers;
    }

}
