package frontend;

import java.util.EventListener;
import java.util.EventObject;

/**
 * 自定义事件监听器。
 */
public interface MyListener extends EventListener {
    public void handlerEvent(MyEvent e);
}
