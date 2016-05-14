package springbook.user.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import springbook.learningtest.jdk.TransactionHandler;

import java.lang.reflect.Proxy;

/**
 * Created by kjnam on 2016. 5. 14..
 */
public class TxProxyFactoryBean implements FactoryBean<Object> {
    Object target;
    PlatformTransactionManager transactionManager;
    String pattern;
    Class<?> serviceInferface;

    public void setServiceInferface(Class<?> serviceInferface) {
        this.serviceInferface = serviceInferface;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    @Override
    public Object getObject() throws Exception {
        TransactionHandler txHandler = new TransactionHandler();
        txHandler.setTarget(target);
        txHandler.setTransactionManager(transactionManager);
        txHandler.setPattern(pattern);
        return Proxy.newProxyInstance(
                getClass().getClassLoader(), new Class[] { serviceInferface },
                txHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInferface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
