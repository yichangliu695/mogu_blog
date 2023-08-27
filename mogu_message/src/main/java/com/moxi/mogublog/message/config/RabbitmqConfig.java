package com.moxi.mogublog.message.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.moxi.mogublog.message.global.RabbitmqConf.*;

/**
 * RabbitMQ 配置类
 *
 * @author LiuYiChang
 * @date 2023/8/8 20:38
 */

@Configuration
public class RabbitmqConfig {



    /**
     * 声明交换机 direct 交换机
     */
    @Bean(EXCHANGE_DIRECT)
    public Exchange exchangeDirect(){
        //durable:交换机持久化,即使重启rabbitmq,交换机还在
        return ExchangeBuilder.directExchange(EXCHANGE_DIRECT).durable(true).build();
    }

    /**
     * 声明消息队列,mail用户处理邮件
     */
    @Bean(MOGU_MAIL)
    public Queue mailQueue(){
        return new Queue(MOGU_MAIL);
    }

    /**
     * 声明消息队列,blog处理博客
     */
    @Bean(MOGU_BLOG)
    public Queue blogQueue(){
        return new Queue(MOGU_BLOG);
    }

    /**
     * mail队列绑定交换机,指定routingKey
     */
    @Bean
    public Binding MailToExchange(@Qualifier(MOGU_MAIL) Queue queue,@Qualifier(EXCHANGE_DIRECT) Exchange exchange){
        //noargs() 表示在绑定市不需要传递特殊参数
        return BindingBuilder.bind(queue).to(exchange).with(KEY_MOGU_MAIL).noargs();
    }

    /**
     * blog队列绑定交换机,指定routingKey
     */
    @Bean
    public Binding BlogToExchange(@Qualifier(MOGU_BLOG) Queue queue,@Qualifier(EXCHANGE_DIRECT) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(KEY_MOGU_BLOG).noargs();
    }

    /**
     * 配置消息转化器
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
