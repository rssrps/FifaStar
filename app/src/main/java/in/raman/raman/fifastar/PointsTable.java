package in.raman.raman.fifastar;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PointsTable extends AppCompatActivity {

    private TextView groupHeading;
    private TextView groupA,groupB,groupC,groupD;

    private ImageView Image;
    private String aUrl,bUrl,cUrl,dUrl;
    private float x1,x2;
    static final int MIN_DISTANCE = 130;
    private int position;
    private TextView loadingText;

    private StorageReference mDataStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_table);

        groupHeading = (TextView) findViewById(R.id.group_id);
        groupA = (TextView) findViewById(R.id.groupA);
        groupB = (TextView) findViewById(R.id.groupB);
        groupC = (TextView) findViewById(R.id.groupC);
        groupD = (TextView) findViewById(R.id.groupD);
        Image = (ImageView) findViewById(R.id.image);
        loadingText = (TextView) findViewById(R.id.loadingTxt);

        groupA.setBackgroundColor(Color.parseColor("#f0f0f0"));
        groupA.setTypeface(null,Typeface.BOLD);
        position = 1;

        mDataStorage = FirebaseStorage.getInstance().getReference();

         mDataStorage.child("Data/a.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                aUrl = uri.toString();
                selectA();

            }
        });



        mDataStorage.child("Data/b.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                bUrl = uri.toString();

            }
        });

        mDataStorage.child("Data/c.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                cUrl = uri.toString();

            }
        });

        mDataStorage.child("Data/d.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                dUrl = uri.toString();

            }
        });




        groupA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectA();

            }
        });

        groupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectB();

            }
        });

        groupC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               selectC();

            }
        });


        groupD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               selectD();

            }
        });


    }



    public void selectA(){

        loadingText.setVisibility(View.VISIBLE);

        Picasso.with(getApplicationContext()).load(aUrl).into(Image, new Callback() {
            @Override
            public void onSuccess() {

                loadingText.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onError() {

            }
        });

        groupA.setBackgroundColor(Color.parseColor("#f0f0f0"));
        groupB.setBackgroundColor(Color.parseColor("#ffffff"));
        groupC.setBackgroundColor(Color.parseColor("#ffffff"));
        groupD.setBackgroundColor(Color.parseColor("#ffffff"));

        groupA.setTypeface(null,Typeface.BOLD);
        groupB.setTypeface(Typeface.DEFAULT);
        groupC.setTypeface(Typeface.DEFAULT);
        groupD.setTypeface(Typeface.DEFAULT);

        position = 1;

    }

    public  void selectB(){

        loadingText.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext()).load(bUrl).into(Image, new Callback() {
            @Override
            public void onSuccess() {

                loadingText.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onError() {

            }
        });

        groupB.setBackgroundColor(Color.parseColor("#f0f0f0"));
        groupA.setBackgroundColor(Color.parseColor("#ffffff"));
        groupC.setBackgroundColor(Color.parseColor("#ffffff"));
        groupD.setBackgroundColor(Color.parseColor("#ffffff"));

        groupB.setTypeface(null,Typeface.BOLD);
        groupA.setTypeface(Typeface.DEFAULT);
        groupC.setTypeface(Typeface.DEFAULT);
        groupD.setTypeface(Typeface.DEFAULT);

        position = 2;

    }


    public void selectC(){

        loadingText.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext()).load(cUrl).into(Image, new Callback() {
            @Override
            public void onSuccess() {
                loadingText.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {

            }
        });

        groupC.setBackgroundColor(Color.parseColor("#f0f0f0"));
        groupB.setBackgroundColor(Color.parseColor("#ffffff"));
        groupA.setBackgroundColor(Color.parseColor("#ffffff"));
        groupD.setBackgroundColor(Color.parseColor("#ffffff"));

        groupC.setTypeface(null,Typeface.BOLD);
        groupB.setTypeface(Typeface.DEFAULT);
        groupA.setTypeface(Typeface.DEFAULT);
        groupD.setTypeface(Typeface.DEFAULT);

        position = 3;

    }


    public void selectD(){

        loadingText.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext()).load(dUrl).into(Image, new Callback() {
            @Override
            public void onSuccess() {
                loadingText.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {

            }
        });

        groupD.setBackgroundColor(Color.parseColor("#f0f0f0"));
        groupB.setBackgroundColor(Color.parseColor("#ffffff"));
        groupC.setBackgroundColor(Color.parseColor("#ffffff"));
        groupA.setBackgroundColor(Color.parseColor("#ffffff"));

        groupD.setTypeface(null,Typeface.BOLD);
        groupB.setTypeface(Typeface.DEFAULT);
        groupC.setTypeface(Typeface.DEFAULT);
        groupA.setTypeface(Typeface.DEFAULT);

        position = 4;

    }


    public void selectPosition(int p){

        if(p==1)
            selectA();
        if (p==2)
            selectB();
        if(p==3)
            selectC();
        if (p==4)
            selectD();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {



        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (deltaX > MIN_DISTANCE)
                {
                    if(position>1)
                        position--;
                    selectPosition(position);
                }
                else
                {
                    if(Math.abs(deltaX) > MIN_DISTANCE)
                        if(position<4)
                            position++;
                    selectPosition(position);

                }
                break;
        }
        return super.onTouchEvent(event);

    }
}
