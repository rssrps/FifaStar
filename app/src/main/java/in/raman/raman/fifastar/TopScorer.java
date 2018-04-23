package in.raman.raman.fifastar;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class TopScorer extends AppCompatActivity {

    private ImageView topImage;
    private Button next,prev;
    private int position;
    private String url1,url2,url3,url4;
    private StorageReference mDataStorage;
    private TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scorer);

        topImage = (ImageView) findViewById(R.id.topImage);
        next = (Button) findViewById(R.id.next_Btn);
        prev = (Button) findViewById(R.id.previous_btn);
        prev.setVisibility(View.INVISIBLE);
        position = 1;
        mDataStorage = FirebaseStorage.getInstance().getReference();
        loading = (TextView) findViewById(R.id.loading);


        mDataStorage.child("Data").child("top").child("one.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                url1 = uri.toString();
                Picasso.with(getApplicationContext()).load(url1).into(topImage, new Callback() {
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

        mDataStorage.child("Data").child("top").child("two.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                url2 = uri.toString();

            }
        });

        mDataStorage.child("Data").child("top").child("three.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                url3 = uri.toString();

            }
        });

        mDataStorage.child("Data").child("top").child("four.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                url4 = uri.toString();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position<4)
                    position++;

                selectPosition(position);

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position>1)
                    position--;

                selectPosition(position);
            }
        });

    }


    public void selectPosition(int p){

        String url = url1;

        if(p==1)
        {
            prev.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
            url = url1;

        }

        if(p==2)
        {
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            url = url2;
        }

        if(p==3)
        {
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            url = url3;
        }

        if(p==4)
        {
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.INVISIBLE);
            url = url4;
        }

        Picasso.with(getApplicationContext()).load(url).into(topImage);

    }
}
