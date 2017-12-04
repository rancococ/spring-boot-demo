package com.catvgd.springbootdemo.common.logger.log4j2;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import com.catvgd.springbootdemo.common.logger.LoggerMessage;
import com.catvgd.springbootdemo.common.logger.LoggerQueue;

/**
 * 自定义Appender，继承 AbstractAppender 只需要覆盖自已想要的方法即可<br>
 * 类上面的注解是用来设置配置文件中的标签。
 */
@Plugin(name = "LocalQueue", category = "Core", elementType = Appender.ELEMENT_TYPE, printObject = true)
public class LocalQueueAppender extends AbstractAppender {

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();

    // 需要实现的构造方法，直接使用父类就行
    protected LocalQueueAppender(final String name, final Filter filter, final Layout<? extends Serializable> layout,
            final boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @Override
    public void append(LogEvent event) {
        readLock.lock();
        try {
            String originalMessage = "";
            String formattedMessage = "";
            final Layout<? extends Serializable> layout = this.getLayout();
            if (layout != null && layout instanceof PatternLayout) {
                originalMessage = event.getMessage().getFormattedMessage();
                formattedMessage = ((PatternLayout) layout).toSerializable(event);
            } else {
                originalMessage = event.getMessage().getFormattedMessage();
                formattedMessage = event.getMessage().getFormattedMessage();
            }
            // 下面这个是要实现的自定义逻辑
            LoggerMessage loggerMessage = new LoggerMessage(event.getTimeMillis(), event.getThreadName(), event.getLoggerName(),
                    event.getLevel().name(), originalMessage, formattedMessage);
            LoggerQueue.getInstance().push(loggerMessage);
        } catch (Exception ex) {
            if (!ignoreExceptions()) {
                throw new AppenderLoggingException(ex);
            }
        } finally {
            readLock.unlock();
        }
    }

    // 下面这个方法可以接收配置文件中的参数信息
    @PluginFactory
    public static LocalQueueAppender createAppender(@PluginAttribute("name") String name, @PluginElement("Filter") final Filter filter,
            @PluginElement("Layout") Layout<? extends Serializable> layout, @PluginAttribute("ignoreExceptions") boolean ignoreExceptions) {
        if (name == null) {
            LOGGER.error("No name provided for MyCustomAppenderImpl");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new LocalQueueAppender(name, filter, layout, ignoreExceptions);
    }

}
