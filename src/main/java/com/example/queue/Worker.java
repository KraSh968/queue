package com.example.queue;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

@Component
public class Worker {
    private ArrayBlockingQueue<Object> queue;

    public Worker(ArrayBlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void work() throws InterruptedException {
        while (true) {
            if (!queue.isEmpty()) {
                System.out.println(queue.take());
            }
        }
    }

    @EventListener(ApplicationFailedEvent.class)
    public void save() {
        try {
            FileOutputStream fout = new FileOutputStream("queue.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(queue);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {

    }
}
