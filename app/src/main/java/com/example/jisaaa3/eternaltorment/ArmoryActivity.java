package com.example.jisaaa3.eternaltorment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArmoryActivity extends AppCompatActivity {
    private RecyclerView mArmoryRecyclerView;
    private ArmoryItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armory);

        mArmoryRecyclerView = (RecyclerView) findViewById(R.id.armory_recycler_view);
        mArmoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
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
