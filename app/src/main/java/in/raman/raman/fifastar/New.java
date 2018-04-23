package in.raman.raman.fifastar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class New extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        final EditText un,pwd;
        Button login;
        un = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(un.getText().toString().equals("admin") && pwd.getText().toString().equals("123")) {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    Intent goTonewActivity = new Intent(New.this,Success.class);
                    goTonewActivity.putExtra("key1",un.getText().toString());
                    goTonewActivity.putExtra("key2",pwd.getText().toString());
                    startActivity(goTonewActivity);

                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
