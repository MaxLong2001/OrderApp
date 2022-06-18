package frontend.Tool;

import frontend.Tool.MyEvent;

import java.util.EventListener;

/**
 * 自定义事件监听器。
 */
public interface MyListener extends EventListener {
    public void handlerEvent(MyEvent e);
}
