package com.catvgd.springbootdemo.config.jmx;

import java.net.MalformedURLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@EnableMBeanExport
public class JmxConfig {

    @Bean(name = "mbeanServer")
    public MBeanServerFactoryBean mbeanServerFactoryBean() {
        MBeanServerFactoryBean serverFactoryBean = new MBeanServerFactoryBean();
        serverFactoryBean.setDefaultDomain("com.gdxw");
        return serverFactoryBean;
    }

    /**
     * 启动RMI注册表
     * 
     * @return
     */
    @Bean(name = "rmiRegistryFactoryBean")
    public RmiRegistryFactoryBean rmiRegistryFactoryBean() {
        RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
//        rmiRegistryFactoryBean.setHost("localhost");
        rmiRegistryFactoryBean.setPort(1099);
        rmiRegistryFactoryBean.setAlwaysCreate(true);
        return rmiRegistryFactoryBean;
    }

//    @Bean(name = "rmiServiceExporter")
//    public RmiServiceExporter rmiServiceExporter() {
//        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
//        rmiServiceExporter.setServicePort(1199);
//        return rmiServiceExporter;
//    }
    public MBeanServerConnectionFactoryBean mbeanServerConnectionFactoryBean() {
        MBeanServerConnectionFactoryBean mbeanServerConnectionFactoryBean = new MBeanServerConnectionFactoryBean();
        try {
            mbeanServerConnectionFactoryBean.setServiceUrl("service:jmx:rmi://localhost:9875");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return mbeanServerConnectionFactoryBean;
    }

    /**
     * 启动远程 JMX
     * 
     * @return
     */
    @Bean
    public ConnectorServerFactoryBean connectorServerFactoryBean() {
        ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
        connectorServerFactoryBean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/spittr");
        return connectorServerFactoryBean;
    }

    
    
}
