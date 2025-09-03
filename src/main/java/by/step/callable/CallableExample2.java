package by.step.callable;

import by.step.ConcurrentUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class CallableExample2 {
    public static void main(String[] args) throws Exception {
        CallableClass callableObject = new CallableClass(100);
        String call = callableObject.call();
        System.out.println(call); //вызов идёт из потока main
    }

}