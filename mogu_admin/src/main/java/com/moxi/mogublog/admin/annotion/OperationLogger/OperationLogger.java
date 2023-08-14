package com.moxi.mogublog.admin.annotion.OperationLogger;

import com.moxi.mougblog.base.enums.PlatformEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义操作日志注解
 *
 * @author LiuYiChang
 * @date 2020年3月23日09:35:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogger {

    /**
     * 业务名称
     */
    String value() default "";

    /**
     * 平台，默认为WEB端
     */
    PlatformEnum platform() default PlatformEnum.ADMIN;

    /**
     * 是否将当前日志记录到数据库中
     */
    boolean save() default true;
}