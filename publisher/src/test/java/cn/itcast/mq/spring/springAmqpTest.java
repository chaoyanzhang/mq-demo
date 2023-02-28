package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class springAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test_sendMessageToSimpleQueue(){
        String queueName = "simple.queue";
        String message = "hello,springAMQP!";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    public void test_sendMessageToWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello,spring__!";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message+i);
            Thread.sleep(20);
        }

    }
    @Test
    public void test_sendMessageToFanoutExchange(){
        String exchangeName = "itcast.exchange";
        String message = "hello every one!";
        rabbitTemplate.convertAndSend(exchangeName,null,message);

    }
    @Test
    public void test_sendMessageToDirectExchange(){
        String exchangeName = "itcast.direct";
        String message = "hello red!";
        rabbitTemplate.convertAndSend(exchangeName,"red",message);

    }
    @Test
    public void test_sendMessageToTopicExchange(){
        String exchangeName = "itcast.topic";
        String message = "hello yan!";
        rabbitTemplate.convertAndSend(exchangeName,"china.news",message);

    }
    @Test
    public void test_sendMessageToObjectQueue(){
        Map<String,Object> message = new HashMap<>();
        message.put("name","hanhan");
        message.put("age",21);
        rabbitTemplate.convertAndSend("object.Queue",message);

    }
}
