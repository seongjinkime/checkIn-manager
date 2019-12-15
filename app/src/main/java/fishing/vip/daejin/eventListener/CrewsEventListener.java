package fishing.vip.daejin.eventListener;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fishing.vip.daejin.interfaces.Observer;
import fishing.vip.daejin.model.Crews;

public class CrewsEventListener implements ValueEventListener {
    private final String TAG = "crewEventListener";
    private String today;
    Observer observer;

    public CrewsEventListener(Observer observer, String today){
        this.today = today;
        this.observer = observer;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (!dataSnapshot.exists()){
            createCrews();
        }else{
            updateCrews(dataSnapshot);
        }
    }

    private void createCrews(){
        Crews crews = new Crews(today);
        String refPath = "crews/" + today;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference(refPath).setValue(crews);
    }

    private void updateCrews(DataSnapshot dataSnapshot){
        try{
            Crews crews = dataSnapshot.getValue(Crews.class);
            observer.update(crews);
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.e(TAG, "Database Error " + databaseError.getMessage());
    }
}
