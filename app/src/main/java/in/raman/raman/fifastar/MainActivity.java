package in.raman.raman.fifastar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ImageView pointsTableImage;
    private ImageView championsLeagueImage;
    private ImageView topScorerImage;
    private ImageView rulesImage;

    private Button pointsTableBtn;
    private Button championBtn;
    private Button topScorer;
    private Button rulesBtn;

    private TextView mainAnnouncement;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointsTableImage = (ImageView) findViewById(R.id.points_table);
        pointsTableBtn = (Button) findViewById(R.id.points_btn);
        championBtn = (Button) findViewById(R.id.CL_btn);
        championsLeagueImage = (ImageView) findViewById(R.id.football_icon);
        mainAnnouncement = (TextView) findViewById(R.id.main_announcement);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        rulesImage = (ImageView) findViewById(R.id.rules_image);
        rulesBtn = (Button) findViewById(R.id.rulesBtn);

        topScorer = (Button) findViewById(R.id.topscorer_btn);
        topScorerImage = (ImageView) findViewById(R.id.golden_boot);

        mDatabase.child("data").child("Main_announcement").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String announce = dataSnapshot.getValue().toString();
                mainAnnouncement.setText(announce);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




       // ------------------  Points Table Intent ------------------------- \\


        pointsTableImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pointsTableImage.setAlpha((float) 0.4);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        pointsTableImage.setAlpha((float) 1);
                        Intent pointsTableIntent = new Intent(MainActivity.this,PointsTable.class);
                        startActivity(pointsTableIntent);

                    }
                }, 150);
            }
        });

        pointsTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pointsTableIntent = new Intent(MainActivity.this,PointsTable.class);
                startActivity(pointsTableIntent);

            }
        });



        // -------------------------- Fixtures Intent  ------------------------------ \\


        championsLeagueImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                championsLeagueImage.setAlpha((float) 0.4);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        championsLeagueImage.setAlpha((float) 1);
                        Intent CLIntent = new Intent(MainActivity.this,Fixtures.class);
                        startActivity(CLIntent);

                    }
                }, 150);

            }
        });

        championBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CLIntent = new Intent(MainActivity.this,Fixtures.class);
                startActivity(CLIntent);
            }
        });



        // -------------------------- Top Scorer Intent  ------------------------------ \\


        topScorerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                topScorerImage.setAlpha((float) 0.4);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        topScorerImage.setAlpha((float) 1);
                        Intent TopIntent = new Intent(MainActivity.this,TopScorer.class);
                        startActivity(TopIntent);

                    }
                }, 150);

            }
        });

        topScorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent TopIntent = new Intent(MainActivity.this,TopScorer.class);
                startActivity(TopIntent);

            }
        });




        // -------------------------- Rules Intent  ------------------------------ \\


        rulesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rulesImage.setAlpha((float) 0.4);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        rulesImage.setAlpha((float) 1);
                        Intent RulesIntent = new Intent(MainActivity.this,Rules.class);
                        startActivity(RulesIntent);

                    }
                }, 150);

            }
        });

        rulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent RulesIntent = new Intent(MainActivity.this,Rules.class);
                startActivity(RulesIntent);

            }
        });


    }
}
