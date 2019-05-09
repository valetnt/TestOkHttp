package ch.arpage.testokhttp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class MainActivity extends AppCompatActivity {

    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtString = (TextView) findViewById(R.id.txtString);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // When you click on the string an async call with OkHttp is fired.
        txtString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.connect();
            }
        });

        // Update the UI with the current status of the connection.
        viewModel.getResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtString.setText(s);
            }
        });
    }
}

