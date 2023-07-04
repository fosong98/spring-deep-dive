import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimerInvocationHandler implements InvocationHandler {
    private Object target;
    public TimerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long end = System.nanoTime();

        System.out.println(
                String.format("%s.%s: %d", target.getClass().getSimpleName(), method.getName(), end-start)
        );

        return result;
    }
}
