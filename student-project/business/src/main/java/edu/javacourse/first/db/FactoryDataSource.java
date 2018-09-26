package edu.javacourse.first.db;

import edu.javacourse.first.db.reflection.CallMe;
import edu.javacourse.first.domain.StudentOrder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class FactoryDataSource {

    private static String className = "edu.javacourse.first.db.FakeDataSource";

    private static String cName = "edu.javacourse.first.db.reflection.FlexibleDataSource";
    private static String mName = "getAllOrders";

    public static StudentOrderDataSource getDataSource() {
        try {
            Class aClass = Class.forName(className);
            Object o = aClass.newInstance();
            StudentOrderDataSource ds = (StudentOrderDataSource) o;
            return ds;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FakeDataSource();
    }

    public static List<StudentOrder> getOrderList() {
        try {
            Class aClass = Class.forName(cName);
            Object ds = aClass.newInstance();

            Method method = aClass.getMethod(mName, int.class, int.class);
            Object invoke = method.invoke(ds, 1, 2);
            return (List<StudentOrder>) invoke;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void fieldExample() {
        try {
            Class aClass = Class.forName(cName);
            Field field = aClass.getDeclaredField("name");

            //разрешаем редактировать поле
            field.setAccessible(true);

            Object ds = aClass.newInstance();
            field.set(ds, "12345");
            Object o = field.get(ds);
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void constructorExample() {
        try {
            Class aClass = Class.forName(cName);
            Constructor con = aClass.getConstructor(String.class);
            Object ds = con.newInstance("SuperHero!!!");

            Field field = aClass.getDeclaredField("name");
            field.setAccessible(true);
            Object o = field.get(ds);
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void annotationExample() {
        try {
            Class aClass = Class.forName(cName);
            Object ds = aClass.newInstance();

            Method[] ms = aClass.getDeclaredMethods();
            for (Method m : ms) {
                CallMe cm = m.getAnnotation(CallMe.class);
                if (cm != null) {
                    System.out.println(cm.comment());
                    m.invoke(ds);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
