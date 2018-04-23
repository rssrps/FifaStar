package in.raman.raman.fifastar;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class ChampionsLeague extends AppCompatActivity {

    private ImageView clImage;
    private StorageReference mStorage;
    String url;
    private TextView loading,announcement;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions_league);

        clImage = (ImageView) findViewById(R.id.CL_image);
        mStorage = FirebaseStorage.getInstance().getReference();
        loading = (TextView) findViewById(R.id.loading);
        announcement = (TextView) findViewById(R.id.announcement);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mStorage.child("Data/champions_league.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                url = uri.toString();
                Picasso.with(getApplicationContext()).load(url).into(clImage, new Callback() {
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

       mDatabase.child("data").child("CL_announcement").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               String CL_announcement = dataSnapshot.getValue().toString();
               announcement.setText(CL_announcement);

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });


    }
}
