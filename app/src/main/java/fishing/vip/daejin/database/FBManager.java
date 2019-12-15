package fishing.vip.daejin.database;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fishing.vip.daejin.checkinmanager.MainActivity;
import fishing.vip.daejin.eventListener.CrewsEventListener;
import fishing.vip.daejin.eventListener.UsersEventListener;
import fishing.vip.daejin.interfaces.Observer;
import fishing.vip.daejin.model.Crews;
import fishing.vip.daejin.observers.CrewsObserver;

public class FBManager {
    private final String TAG = "FB Manager";
    public static FBManager fbManager;

    Context context;
    FirebaseDatabase database;

    public FBManager(Context context){
        this.context = context;
        this.database = FirebaseDatabase.getInstance();
    }

    private String today(){
        return  "2019/12/07";
    }

    public void startWatchingCrews(Observer observer){
        String ref = "crews/" + today();
        CrewsEventListener listener = new CrewsEventListener(observer, today());
        database.getReference(ref).orderByKey().addValueEventListener(listener);
    }

    public void syncUsers(){
        UsersEventListener listener = new UsersEventListener(context);
        database.getReference("users").orderByKey().addListenerForSingleValueEvent(listener);
    }

    public void updateCrews(Crews crews){
        String ref = "crews/" + today();
        database.getReference(ref).setValue(crews);
    }

    public static FBManager getInstance(Context context){
        if(fbManager == null){
            fbManager = new FBManager(context);
        }
        return fbManager;
    }

}

