package learn.risk_online.domain;

import java.util.ArrayList;

public class Result<T> {
    private final ArrayList<String> messages = new ArrayList<>();
    private T payload;

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void addErrorMessage(String error){
        messages.add(error);
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess(){
        return messages.size() == 0;
    }
}
