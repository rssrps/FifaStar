package in.raman.raman.fifastar;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Annouuncement extends AppCompatActivity {

    private ImageView announceImage;
    private StorageReference mDataStorage;
    private TextView loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annouuncement);

        announceImage = (ImageView) findViewById(R.id.announceImage);
        mDataStorage = FirebaseStorage.getInstance().getReference();
        loading = (TextView)  findViewById(R.id.loading);

        mDataStorage.child("Data/announcements.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                String Url;
                Url = uri.toString();

                Picasso.with(getApplicationContext()).load(Url).placeholder(R.drawable.white).into(announceImage, new Callback() {
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



    }
}
