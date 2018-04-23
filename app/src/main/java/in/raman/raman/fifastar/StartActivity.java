package in.raman.raman.fifastar;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartActivity extends AppCompatActivity {

    private Button proceed,register,voting;
    private DatabaseReference mDatabase;
    private TextView entries;
    private CircleImageView mLogo;
    private StorageReference mDataStorage;
    private ImageView announce;
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        proceed = (Button) findViewById(R.id.proceed);
        entries = (TextView) findViewById(R.id.startAnnounce);
        register = (Button) findViewById(R.id.register);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        voting = (Button) findViewById(R.id.voting);
        mLogo = (CircleImageView) findViewById(R.id.logo);
        announce = (ImageView) findViewById(R.id.announce);


        mDataStorage = FirebaseStorage.getInstance().getReference();

        mDataStorage.child("Data/ec.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                String url = uri.toString();
                Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.applogo1).into(mLogo);

            }
        });

        mDatabase.child("data").child("StartAnnouncement").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String announce = dataSnapshot.getValue().toString();
                entries.setText(announce);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Registrations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> regchilden = dataSnapshot.getChildren();
                for (DataSnapshot entry : regchilden) {

                    String number = entry.child("Contact").getValue(String.class);
                    result = result + number + " ";
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mainIntent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(mainIntent);

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(StartActivity.this,New.class);
                startActivity(i);

            }
        });

        voting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(StartActivity.this,Voting.class);
                startActivity(i);

            }
        });


        announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                announce.setAlpha( (float) 0.5);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        announce.setAlpha((float) 1);
                        Intent pointsTableIntent = new Intent(StartActivity.this,Annouuncement.class);
                        startActivity(pointsTableIntent);

                    }
                }, 150);

            }
        });

    }
}
