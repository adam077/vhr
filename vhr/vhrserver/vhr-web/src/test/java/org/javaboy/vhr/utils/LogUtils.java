package org.javaboy.vhr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    /*
    工具集
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);

    void do2(String[] args) {
        // 为啥不能用do
        LOGGER.info("running with arguments: {}", String.join(" ", args));
    }

}
