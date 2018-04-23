package in.raman.raman.fifastar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Fixtures extends AppCompatActivity {


    private ImageView fixture;
    private TextView fixtureAnnouncement,loading;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private Button ClBtn;
    String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        fixture = (ImageView) findViewById(R.id.fixtureImage);
        fixtureAnnouncement = (TextView) findViewById(R.id.fixtureAnnouncement);
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ClBtn = (Button) findViewById(R.id.CL_btn);
        loading = (TextView) findViewById(R.id.loading);

        mStorage.child("Data").child("fixture").child("fixtures.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                url = uri.toString();
                Picasso.with(getApplicationContext()).load(url).into(fixture, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });

            }
        });

        mDatabase.child("data").child("fixture_announcement").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String announce = dataSnapshot.getValue().toString();
                fixtureAnnouncement.setText(announce);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ClBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Fixtures.this,ChampionsLeague.class);
                startActivity(i);

            }
        });



    }
}
