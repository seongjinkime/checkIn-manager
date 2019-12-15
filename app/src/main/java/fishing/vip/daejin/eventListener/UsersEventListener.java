package fishing.vip.daejin.eventListener;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fishing.vip.daejin.checkinmanager.MainActivity;
import fishing.vip.daejin.database.SQLHelper;

public class UsersEventListener implements ValueEventListener {

    private final String TAG = "UsersEventListener";
    private Context context;

    public UsersEventListener(Context context){
        this.context = context;

    }
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        SQLHelper helper = SQLHelper.getInstance(context);
        helper.clear();
        helper.addUsers(dataSnapshot);
        ((MainActivity)context).initComplete();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.e(TAG, "database Error");
        Log.e(TAG, databaseError.toString());
    }
}
