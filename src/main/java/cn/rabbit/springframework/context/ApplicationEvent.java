package cn.rabbit.springframework.context;

import lombok.Data;

@Data
public abstract class ApplicationEvent extends EventObject{
    public ApplicationEvent(Object source) {
        super(source);
    }
}
