package com.catvgd.springbootdemo.common.logger.log4j2;

import java.util.List;

public interface Log4j2ManagerMBean {

    /**
     * 列出指定前缀的日志级别
     * 
     * @param loggerNamePrefix
     * @return
     */
    public List<String> listLoggerInfo(String loggerNamePrefix);

    /**
     * 改变指定日志的级别
     * 
     * @param loggerName
     * @param loggerLevel
     * @return
     */
    public String changeLoggerLevel(String loggerName, String loggerLevel);

    /**
     * 改变根日志的级别
     * 
     * @param loggerName
     * @param loggerLevel
     * @return
     */
    public String changeRootLoggerLevel(String loggerLevel);
    
}
