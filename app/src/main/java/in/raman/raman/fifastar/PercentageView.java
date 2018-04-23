package in.raman.raman.fifastar;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class PercentageView extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String left,right;
    private ProgressDialog mProgress;
    private int x=0;
    private PieChart mPieChart;
    private String l,r;
    private int v1,v2,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage_view);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("voting");
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading");
        mProgress.setMessage("Please wait");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();

        mPieChart = (PieChart) findViewById(R.id.piechart);

        mDatabase.child("left").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                left = dataSnapshot.getValue().toString();
                x++;

                if(x==4){
                    mProgress.dismiss();

                    v1 = Integer.parseInt(l);
                    v2 = Integer.parseInt(r);
                    total = v1 + v2;

                    int set = ( v1 * 100 )/total;

                    mPieChart.addPieSlice(new PieModel(left, set, Color.parseColor("#ff5000")));
                    mPieChart.addPieSlice(new PieModel(right, 100 - set, Color.parseColor("#56B7F1")));
                    mPieChart.startAnimation();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("right").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                right = dataSnapshot.getValue().toString();
                x++;
                if(x==4){
                    mProgress.dismiss();

                    v1 = Integer.parseInt(l);
                    v2 = Integer.parseInt(r);
                    total = v1 + v2;

                    int set = ( v1 * 100 )/total;


                    mPieChart.addPieSlice(new PieModel(left, set, Color.parseColor("#ff5000")));
                    mPieChart.addPieSlice(new PieModel(right, 100 - set, Color.parseColor("#56B7F1")));
                    mPieChart.startAnimation();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("one").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                l = dataSnapshot.getValue().toString();
                x++;
                if(x==4){
                    mProgress.dismiss();

                    v1 = Integer.parseInt(l);
                    v2 = Integer.parseInt(r);
                    total = v1 + v2;

                    int set = ( v1 * 100 )/total;

                    mPieChart.addPieSlice(new PieModel(left,set , Color.parseColor("#ff5000")));
                    mPieChart.addPieSlice(new PieModel(right, 100 - set, Color.parseColor("#56B7F1")));
                    mPieChart.startAnimation();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mDatabase.child("two").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                r = dataSnapshot.getValue().toString();
                x++;
                if(x==4){
                    mProgress.dismiss();

                    v1 = Integer.parseInt(l);
                    v2 = Integer.parseInt(r);
                    total = v1 + v2;

                    int set = ( v1 * 100 )/total;

                    mPieChart.addPieSlice(new PieModel(left, set, Color.parseColor("#ff5000")));
                    mPieChart.addPieSlice(new PieModel(right, 100 - set, Color.parseColor("#56B7F1")));
                    mPieChart.startAnimation();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
