package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean //方法名称是bean唯一ID
    public Queue simpleQueue(){
        return new Queue("simple.queue");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("itcast.exchange");
    }

    @Bean //方法名称是bean唯一ID
    public Queue fanOutQueue1(){
        return new Queue("fanout.queue1");
    }

    @Bean
    public Queue fanOutQueue2(){
        return new Queue("fanout.queue2");
    }

    @Bean
    public Binding bingQueue1(Queue fanOutQueue1, FanoutExchange fanoutExchange){ //根据类型和参数注入
        return BindingBuilder
                .bind(fanOutQueue1)
                .to(fanoutExchange);
    }

    @Bean
    public Binding bingQueue2(Queue fanOutQueue2, FanoutExchange fanoutExchange){ //根据类型和参数注入
        return BindingBuilder
                .bind(fanOutQueue2)
                .to(fanoutExchange);
    }

    @Bean
    public Queue objectQueue(){
        return new Queue("object.Queue");
    }
}
