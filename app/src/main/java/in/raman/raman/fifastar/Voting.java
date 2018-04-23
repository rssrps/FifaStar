package in.raman.raman.fifastar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Voting extends AppCompatActivity {

    private StorageReference mDataStorage;
    private String oneUrl,twoUrl;
    private ImageView one,two;
    private TextView percent1,percent2,loading,votingCaption,announce;
    private DatabaseReference mDatabase;
    String per1,per2;
    private ProgressDialog mProgress;
    private int value1,value2;
    String postid;
    private Button b1,b2;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        one = (ImageView) findViewById(R.id.one);
        two = (ImageView) findViewById(R.id.two);

        chart = (Button) findViewById(R.id.chart);
        chart.setVisibility(View.INVISIBLE);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);

        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);

        percent1 = (TextView) findViewById(R.id.percent1);
        percent2 = (TextView) findViewById(R.id.percent2);
        loading = (TextView) findViewById(R.id.loading);
        announce = (TextView) findViewById(R.id.announce);

        votingCaption = (TextView) findViewById(R.id.voteCaption);
        votingCaption.setVisibility(View.INVISIBLE);

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading");
        mProgress.setMessage("Making vote,Please wait");
        mProgress.setCanceledOnTouchOutside(false);

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();


        mDataStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("voting");

        mDataStorage.child("Data/voting/one.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                oneUrl = uri.toString();
                Picasso.with(getApplicationContext()).load(oneUrl).into(one, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading.setVisibility(View.INVISIBLE);

                        mDatabase.child("caption").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String caption = dataSnapshot.getValue().toString();
                                votingCaption.setText(caption);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        votingCaption.setVisibility(View.VISIBLE);
                        b1.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });

            }
        });

        mDatabase.child("announce").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                announce.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDataStorage.child("Data/voting/two.PNG").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                twoUrl = uri.toString();
                Picasso.with(getApplicationContext()).load(twoUrl).into(two, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading.setVisibility(View.INVISIBLE);
                        b2.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });

            }
        });

        mDatabase.child("one").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                per1 = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("two").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                per2 = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                postid = dataSnapshot.getValue().toString();

                if(pref.getString(postid,"no").equals("no")){

                    percent1.setVisibility(View.INVISIBLE);
                    percent2.setVisibility(View.INVISIBLE);
                    chart.setVisibility(View.INVISIBLE);
                }
                else
                {
                    percent1.setVisibility(View.VISIBLE);
                    percent2.setVisibility(View.VISIBLE);
                    chart.setVisibility(View.VISIBLE);


                    mDatabase.child("one").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            per1 = dataSnapshot.getValue().toString();
                            percent1.setText("Votes: "+per1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    mDatabase.child("two").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            per2 = dataSnapshot.getValue().toString();
                            percent2.setText("Votes: "+per2);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });






                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(pref.getString(postid,"no").equals("yes"))
                {
                    Toast.makeText(getApplicationContext(),"You have already voted on this!",Toast.LENGTH_SHORT).show();
                }

                else {

                    mProgress.show();
                    value1 = Integer.parseInt(per1);
                    value2 = Integer.parseInt(per2);

                    value1 = value1 + 1;
                    final int total = value1 + value2;

                    final String val_update = String.valueOf(value1);

                    mDatabase.child("one").setValue(val_update).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            mProgress.dismiss();
                            Toast.makeText(getApplicationContext(), "Your vote has been recorded", Toast.LENGTH_SHORT).show();

                            editor.putString(postid,"yes");
                            editor.commit();

                            percent1.setVisibility(View.VISIBLE);
                            percent2.setVisibility(View.VISIBLE);
                            chart.setVisibility(View.VISIBLE);

                            percent1.setText(String.valueOf((value1 * 100) / total) + "%");
                            percent2.setText(String.valueOf(100 - ((value1 * 100) / total)) + "%");

                        }
                    });


                }


            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(pref.getString(postid,"no").equals("yes"))
                {
                    Toast.makeText(getApplicationContext(),"You have already voted this post!",Toast.LENGTH_SHORT).show();
                }


                else {

                    mProgress.show();
                    value1 = Integer.parseInt(per1);
                    value2 = Integer.parseInt(per2);

                    value2 = value2 + 1;
                    final int total = value1 + value2;

                    final String val_update = String.valueOf(value2);

                    mDatabase.child("two").setValue(val_update).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            mProgress.dismiss();
                            Toast.makeText(getApplicationContext(), "Your vote has been recorded", Toast.LENGTH_SHORT).show();

                            editor.putString(postid,"yes");
                            editor.commit();

                            percent1.setVisibility(View.VISIBLE);
                            percent2.setVisibility(View.VISIBLE);

                            percent1.setText(String.valueOf((value1 * 100) / total) + "%");
                            percent2.setText(String.valueOf(100 - ((value1 * 100) / total)) + "%");

                        }
                    });


                }



            }
        });


        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Voting.this, PercentageView.class);
                startActivity(i);
            }
        });

    }
}
