package com.wangwenjun.concurrent.chapter18_active_objects;

/**
 * @author malichun
 * @create 2022/12/07 0007 20:42
 */
public class DisplayStringRequest extends MethodRequest{

    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}
