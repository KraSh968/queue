package com.example.queue;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

@RestController
public class Controller {
    private Worker worker;
    private ArrayBlockingQueue<Object> queue;

    public Controller(Worker worker, ArrayBlockingQueue<Object> queue) {
        this.worker = worker;
        this.queue = queue;
    }

    @RequestMapping("/tst")
    public void tst(@RequestBody Object object) throws InterruptedException {
        try {
            Integer object1 = (Integer) object;
        } catch (Exception e) {
            worker.save();
        }
        queue.put(object);
    }
}