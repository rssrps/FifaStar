package in.raman.raman.fifastar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Bundle b = getIntent().getExtras();
        Toast.makeText(getApplicationContext(),"Using key 1" + b.getString("key1"),Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"Using key 2"+ b.getString("key2"),Toast.LENGTH_SHORT).show();

    }
}
