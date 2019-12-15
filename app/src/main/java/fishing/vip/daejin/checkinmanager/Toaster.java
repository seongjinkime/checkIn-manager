package fishing.vip.daejin.checkinmanager;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
    public final static int NORMAL = -1121;
    public final static int WELCOME = -2324;
    public final static int NO_INFO = -3212;
    private Context context;

    public Toaster(Context context){
        this.context = context;
    }

    public void showToast(String msg, int type){
        switch (type){
            case NORMAL:
                showNormalToast(msg);
                break;
            case WELCOME:
                break;
            case NO_INFO:
                break;
        }
    }

    private void showNormalToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
