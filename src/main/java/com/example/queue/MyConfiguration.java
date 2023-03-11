package com.example.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.ArrayBlockingQueue;

@Configuration
public class MyConfiguration {
    @Bean
    public ArrayBlockingQueue<Object> synchronousQueue() {
        ArrayBlockingQueue queue = null;
        try {
            FileInputStream fin = new FileInputStream("queue.dat");
            ObjectInputStream ois = new ObjectInputStream(fin);
            queue = (ArrayBlockingQueue<Object>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            queue = new ArrayBlockingQueue<>(20);
        }
        return queue;
    }
}