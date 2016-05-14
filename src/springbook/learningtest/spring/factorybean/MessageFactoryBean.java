package springbook.learningtest.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by kjnam on 2016. 5. 14..
 */
public class MessageFactoryBean implements FactoryBean<Message> {
    String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}