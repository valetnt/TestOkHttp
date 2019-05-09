package ch.arpage.testokhttp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Observable;
import java.util.Observer;


public class MainActivityViewModel extends ViewModel {

    private OkHttpHandler okHttpHandler;
    private MutableLiveData<String> response;

    public MainActivityViewModel() {
        this.okHttpHandler = new OkHttpHandler();
        this.response = new MutableLiveData<>();
    }

    public void connect() {
        String url = "whatever-endpoint";
        okHttpHandler.execute(url, "username", "password");
    }

    public LiveData<String> getResponse() {
        okHttpHandler.getResponse().addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                MyObservable<String> observable = (MyObservable<String>) o;
                response.setValue(observable.getValue());
            }
        });
        return response;
    }
}
