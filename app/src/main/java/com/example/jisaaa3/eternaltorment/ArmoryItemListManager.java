package com.example.jisaaa3.eternaltorment;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jisaaa3 on 10/26/2017.
 */

public class ArmoryItemListManager {
    private static ArmoryItemListManager sArmoryListManager;

    private List<ArmoryItem> mArmoryItems;

    public static ArmoryItemListManager get(Context context) {
        if (sArmoryListManager == null) {
            sArmoryListManager = new ArmoryItemListManager(context);
        }

        return sArmoryListManager;
    }

    public List<ArmoryItem> getArmoryItems() {
        return mArmoryItems;
    }


    //If we need this?
    /*
    public ArmoryItem getArmoryItem(UUID id) {

    }
    */


    private ArmoryItemListManager(Context context) {
        mArmoryItems = new ArrayList<>();

        //Init items here for testing
    }

}
