package com.wuyunbin.quadra.admin.application.handler;


import com.wuyunbin.quadra.admin.domain.event.AdminPasswordChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LogListener {

    @EventListener
    @Async
    public void handle(AdminPasswordChangedEvent event) {
        System.out.println("监听到密码修改事件：" + event.adminId());
    }
}
