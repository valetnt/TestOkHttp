package ch.arpage.testokhttp;

import android.os.AsyncTask;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpHandler extends AsyncTask<String, Integer, String> {

    private OkHttpClient client;
    private MyObservable<String> response = new MyObservable<>("not-a-response");

    OkHttpHandler() {
        this.client = new OkHttpClient();
    }

    @Override
    protected String doInBackground(String... params) {
        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);
        builder.header("Authorization", Credentials.basic(params[1], params[2]));

        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            return ("Status: " + response.code());

        } catch (Exception e) {
            e.printStackTrace();
            return "Connection failed.";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        response.setValue(s);
    }


    public MyObservable<String> getResponse() {
        return response;
    }
}
