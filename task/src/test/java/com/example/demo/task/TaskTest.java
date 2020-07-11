package com.example.demo.task;

import com.example.demo.task.task.AsyncCallBackTask;
import com.example.demo.task.task.AsyncExecutorTask;
import com.example.demo.task.task.AsyncTask;
import com.example.demo.task.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {
    @Autowired
    private Task task;

    @Test
    public void testSyncTasks() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
    }

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testAsyncTasks() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
    }

    @Autowired
    private AsyncCallBackTask asyncCallBackTask;

    @Test
    public void testAsyncCallbackTask() throws Exception {
        long start = currentTimeMillis();
        Future<String> task1 = asyncCallBackTask.doTaskOneCallback();
        Future<String> task2 = asyncCallBackTask.doTaskTwoCallback();
        Future<String> task3 = asyncCallBackTask.doTaskThreeCallback();

        // 三个任务都调用完成，退出循环等待
        while (!task1.isDone() || !task2.isDone() || !task3.isDone()) {
            sleep(1000);
        }

        long end = currentTimeMillis();
        out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    @Autowired
    private AsyncExecutorTask asyncExecutorTask;

    @Test
    public void testAsyncExecutorTask() throws Exception {
        asyncExecutorTask.doTaskOne();
        asyncExecutorTask.doTaskTwo();
        asyncExecutorTask.doTaskThree();

        sleep(30 * 1000L);
    }
}
