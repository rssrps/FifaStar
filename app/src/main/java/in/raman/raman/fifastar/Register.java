package in.raman.raman.fifastar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText name,branch,year,contact;
    private Button registerBtn;
    private DatabaseReference mDatabase;
    private ProgressDialog loading;
    private RadioButton f17,f15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        branch = (EditText) findViewById(R.id.branch);
        year = (EditText) findViewById(R.id.year);
        contact = (EditText) findViewById(R.id.contact);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        f17 = (RadioButton) findViewById(R.id.f17);
        f15 = (RadioButton) findViewById(R.id.f15);

        loading = new ProgressDialog(this);
        loading.setTitle("Registering");
        loading.setMessage("Please wait while we register you as a player");
        loading.setCanceledOnTouchOutside(false);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(name.getText().toString().trim().equals("") || branch.getText().toString().trim().equals("") || year.getText().toString().trim().equals("") || contact.getText().toString().trim().equals("")|| (f17.isChecked()==false&&f15.isChecked()==false) )
                {
                        Toast.makeText(getApplicationContext(),"Feild cannot be empty!",Toast.LENGTH_SHORT).show();
                }

                else{

                   final String rd;
                    if(f17.isChecked())
                        rd= "Fifa 17";
                    else
                        rd= "Fifa 15";

                    loading.show();
                    mDatabase.child("Registrations").child(name.getText().toString() + contact.getText().toString().trim()).child("Branch").setValue(branch.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            mDatabase.child("Registrations").child(name.getText().toString() + contact.getText().toString().trim()).child("year").setValue(year.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    mDatabase.child("Registrations").child(name.getText().toString() + contact.getText().toString().trim()).child("Contact").setValue(contact.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            mDatabase.child("Registrations").child(name.getText().toString() + contact.getText().toString().trim()).child("Preference").setValue(rd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                    loading.dismiss();
                                                    Toast.makeText(getApplicationContext(),"Successfully registered!",Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(Register.this,MainActivity.class);
                                                    startActivity(i);
                                                    finish();
                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        }
                    });



                }


            }
        });


        f17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(f15.isChecked())
                    f15.setChecked(false);

            }
        });


        f15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(f17.isChecked())
                    f17.setChecked(false);

            }
        });


    }
}
