package fishing.vip.daejin.checkinmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fishing.vip.daejin.database.FBManager;
import fishing.vip.daejin.database.SQLHelper;
import fishing.vip.daejin.model.Crews;
import fishing.vip.daejin.model.User;
import fishing.vip.daejin.observers.CrewsObserver;
import jp.wasabeef.picasso.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {

    public EditText tmp;
    private Crews crews;
    private String phone;
    ProgressDialog progressDialog;
    private Toaster toaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toaster = new Toaster(this);
        phone = "";
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("로딩 중");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loadWallpaper();
        tmp = (EditText)findViewById(R.id.tmpEdit);
        initDatabase();

    }
    public void initDatabase(){
        progressDialog.show();
        FBManager fbManager = FBManager.getInstance(this);
        fbManager.startWatchingCrews(new CrewsObserver(this));
        fbManager.syncUsers();
    }

    private void initUi(){

    }

    public void crewsChanged(Crews crews){
        this.crews = crews;
        Log.e("TEST", "crewChanged");
        Log.e("TEST", String.valueOf(crews.getTotal()));
    }

    public void initComplete(){
        progressDialog.hide();
    }


    private void loadWallpaper(){
        ImageView imageView = (ImageView)findViewById(R.id.wallpaper);
        Picasso.get()
                .load(R.drawable.wallpaper).fit()
                .transform(new BlurTransformation(getApplicationContext(), 25, 2))
                .into(imageView);
    }

    private void addNum(String num){
        phone += num;
        tmp.setText(phone);
        if(phone.length()==4){
            ArrayList<User> users = SQLHelper.getInstance(this).getUser(phone);
            if(users.size() == 1){
                ((TextView)findViewById(R.id.tv_desc)).setText(users.get(0).getName() + " 님");
            }else if(users.size()>1){
                ((TextView)findViewById(R.id.tv_desc)).setText(users.get(0).getName() + " 님 외 " + String.valueOf(users.size()-1) + " 명");
            }
        }else{
            ((TextView)findViewById(R.id.tv_desc)).setText("전화번호 끝 4자리를 입력해주세요");
        }
    }

    private void delNum(){
        if(phone.length()>0){
            phone = phone.substring(0, phone.length()-1);
            tmp.setText(phone);
        }
        ((TextView)findViewById(R.id.tv_desc)).setText("전화번호 끝 4자리를 입력해주세요");
    }

    private void checkIn(){
        if(phone.length() != 4){
            toaster.showToast("전화번호 끝 4자리를 입력해주세요", Toaster.NORMAL);
            return;
        }

        ArrayList<User> users = SQLHelper.getInstance(this).getUser(phone);

        if(users.size() < 1){
            Log.e("Check in", "no user");
        }else if(users.size() == 1){
            String phone = users.get(0).getPhone();
            if(crews.phoneExists(phone)){
                toaster.showToast("이미 체크인 되었습니다", Toaster.NORMAL);
                return;
            }
            crews.addPhone(phone);
            FBManager.getInstance(this).updateCrews(crews);
        }else{
            Log.e("Check in", "One More User");
        }

    }

    public void onClick(View v){
        if(v.getTag() != null && v.getTag().toString().equals("keyPad")){
            addNum(((Button)v).getText().toString());
            return;
        }

        switch (v.getId()){
            case R.id.btn_del:
                delNum();
                break;
            case R.id.check_in:
                checkIn();
                break;
        }
    }

}
