package cn.rabbit.springframework.aop;

public class TargetSource {
    private Object object;

    public TargetSource(Object object) {
        this.object = object;
    }

    public Class<?>[] getTargetClass() {
        return object.getClass().getInterfaces();
    }

    public Object getTarget() {
        return object;
    }
}
