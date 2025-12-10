package com.wuyunbin.quadra.admin.application.handler;

import com.wuyunbin.quadra.admin.domain.event.AdminPasswordChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    @EventListener
    @Async
    public void handle(AdminPasswordChangedEvent event) {
        // 发送邮件
        System.out.println("发送邮件"+ event.adminId());
    }
}
