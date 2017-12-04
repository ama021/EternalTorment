package com.example.jisaaa3.eternaltorment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.id.list;

public class ArmoryActivity extends Activity {
    TextView t;
    TextView textView;
    ImageView imageView;
    Button b;
    int selectedArmor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armory);

        updateUI();

        textView = (TextView) findViewById(R.id.armorStatsText);
        imageView = (ImageView) findViewById(R.id.armor_display);
        b = (Button) findViewById(R.id.setArmor);
        t = (TextView) findViewById(R.id.armoryText);

        String[] armors = {"Knight Armor", "Glass Armor", "Blizzard Armor"};
        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, armors);
        ListView listView = (ListView) findViewById(R.id.armory_list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String armor = String.valueOf(parent.getItemAtPosition(position));
                        if(armor == "Knight Armor"){
                            textView.setText("This is an armor once wielded by the great knight Lotheric of Cinders. He was a horrid man but his armor was not.");
                            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.down_direction_knight));
                            selectedArmor = 0;
                        }
                        else if(armor == "Glass Armor"){
                            textView.setText("If you know what this armor is used for then you are the only one. Put it on and see how it feels.");
                            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.down_knight_glass));
                            selectedArmor = 1;
                        }
                        else{
                            textView.setText("An armor reviered by all warriors in the northern most real of Albion, for this armor will not let its user feal a chill.");
                            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.down_knight_arctic));
                            selectedArmor = 2;
                        }
                    }
                }
        );


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedArmor == 0)
                {
                    // Passing intent to choose armor
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("selectedArmor", 0);
                    setResult(Activity.RESULT_OK, returnIntent);
                }
                else if(selectedArmor == 1){
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("selectedArmor", 1);
                    setResult(Activity.RESULT_OK, returnIntent);
                }
                else{
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("selectedArmor", 2);
                    setResult(Activity.RESULT_OK, returnIntent);
                }
            }
        });

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Blade 2.ttf");
        textView.setTypeface(myCustomFont);

        // Setting the font for the button and title of the activity
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Blade 2.ttf");
        t.setTypeface(myCustomFont2);

        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Blade 2.ttf");
        b.setTypeface(myCustomFont3);
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
