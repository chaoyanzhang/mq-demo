package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class SpringRabbitListener {
//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String msg){
//        System.out.println("消费者接收到simple.queue的消息：【"+msg+"】");
//    }
//    @RabbitListener(queues = "simple.queue")
//    public void listenWorkQueue1(String msg) throws InterruptedException {
//        System.out.println("消费者1接收到的消息：【"+msg+"】"+ LocalTime.now());
//        Thread.sleep(20);
//    }
//    @RabbitListener(queues = "simple.queue")
//    public void listenWorkQueue2(String msg) throws InterruptedException {
//        System.err.println("消费者2接收到的消息：【"+msg+"】"+LocalTime.now());
//        Thread.sleep(200);
//    }
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.err.println("消费者1接收到fanout的消息：【"+msg+"】"+LocalTime.now());
        Thread.sleep(200);
    }
    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2接收到fanout的消息：【"+msg+"】"+LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "directQueue1"),exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),key = {"red","blue"}))
    public void listenDirectQueue(String msg) throws InterruptedException {
        System.err.println("消费者接收到directQueue1的消息：【"+msg+"】"+LocalTime.now());
        Thread.sleep(200);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "directQueue2"),exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),key = {"red","yellow"}))
    public void listenDirectQueue2(String msg) throws InterruptedException {
        System.err.println("消费者接收到directQueue2的消息：【"+msg+"】"+LocalTime.now());
        Thread.sleep(200);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topicQueue1"),
            exchange = @Exchange(name = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg) throws InterruptedException {
        System.err.println("消费者接收到topicQueue1的消息：【"+msg+"】"+LocalTime.now());
        Thread.sleep(200);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topicQueue2"),
            exchange = @Exchange(name = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg) throws InterruptedException {
        System.err.println("消费者接收到topicQueue2的消息：【"+msg+"】"+LocalTime.now());
        Thread.sleep(200);
    }
    @RabbitListener( queues = "object.Queue")
    public void listenObjectQueue(Map<String,Object> msg) throws InterruptedException {
        System.err.println("消费者接收到ObjectQueue的消息：【"+msg+"】"+LocalTime.now());
        Thread.sleep(200);
    }
}
