package com.example.jisaaa3.eternaltorment;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArmoryActivity extends Activity {
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armory);

        updateUI();

        t = (TextView) findViewById(R.id.armoryText);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Blade 2.ttf");
        t.setTypeface(myCustomFont);
    }

    private void updateUI() {


    }

    private class ArmoryItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ArmoryItemHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
        }

        public void bind(ArmoryItem item) {

        }

        @Override
        public void onClick(View view) {
            //Show clicked on results in other frame layouts
            //Broadcast receiver? to let other frame layouts know?
        }
    }

    private class ArmoryItemAdapter extends RecyclerView.Adapter<ArmoryItemHolder> {

        public ArmoryItemAdapter() {

        }

        @Override
        public ArmoryItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.armorylist_item, parent, false);
            return new ArmoryItemHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ArmoryItemHolder holder, int poistion) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }


    }
}
