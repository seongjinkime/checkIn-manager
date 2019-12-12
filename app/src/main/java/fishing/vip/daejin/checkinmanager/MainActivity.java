package fishing.vip.daejin.checkinmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {

    EditText tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadWallpaper();
        tmp = (EditText)findViewById(R.id.tmpEdit);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        String refPath = "users";
        DatabaseReference ref = database.getReference(refPath);
        HashMap<String, String> test = new HashMap<>();
        /*
        for(int i = 0 ; i < 9000 ; i++){
            //Log.e("TEST", String.format("010-0000-%04d", i));
            test.put(String.format("010-0000-%04d", i), "TESTER");
        }
        ref.setValue(test);
        */


        database.getReference("users").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                SharedPreferences pref = getSharedPreferences("tFile", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    //HashMap<String, String> tmp = (HashMap<String, String>)snapshot.getValue();
                    //String phone = tmp.keySet().iterator().next();
                    //String name = tmp.get(phone);
                    //Log.e("TEST",snapshot.getKey() + " : " + snapshot.getValue());
                    editor.putString(snapshot.getKey(), snapshot.getValue().toString());
                }
                editor.commit();
                Log.e("TEST", "Done");
                SharedPreferences pref2 = getSharedPreferences("tFile", MODE_PRIVATE);
                Log.e("TEST", pref2.getString("010-0000-1234", ""));
                //Log.e("TEST", String.valueOf(dataSnapshot.exists()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //database.getReference("users")
        //database.getReference(refPath).setValue("Test1");
        //loadDocument(currentDate());

    }

    private void loadWallpaper(){
        ImageView imageView = (ImageView)findViewById(R.id.wallpaper);
        Picasso.get()
                .load(R.drawable.wallpaper).fit()
                .transform(new BlurTransformation(getApplicationContext(), 25, 2))
                .into(imageView);
    }

    private void addNumber(String num){
        String newStr = tmp.getText().toString() + num;
        tmp.setText(newStr);
    }

    public void onClick(View v){
        Log.e("TEST", String.valueOf(v.getId()));
        switch (v.getId()){

            case R.id.button0:
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
                Button btn = (Button)v;
                addNumber(btn.getText().toString());
                break;
            case R.id.check_in:
                Toast.makeText(getApplicationContext(), "CHECK IN", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    /*

    private String currentDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyy-mm-dd");
        return df.format(Calendar.getInstance().getTime());

    }

    private void loadDocument(String date){
        if(!tmpIsDocumentExist(date)){
            tmpCreateNewDocument(date);
        }
        tmpLoadDocument(date);
    }
    //Put the document manager

    private boolean tmpIsDocumentExist(String date){
        return false;
    }

    private void tmpCreateNewDocument(String date){
        showToast(date + "의 새로운 명부를 생성 합니다");
    }

    private void tmpLoadDocument(String date){
        showToast(date + "의 명부를 로드 합니다");
    }

    private void showToast(String msg){
        Log.e("TMP", msg);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_checkin:
                startCheckIn();
                break;
            case R.id.button_print:
                startPrint();
                break;
        }

    }

    private void startCheckIn(){
        showToast("체크인을 시작 합니다");
        Intent intent = new Intent(MainActivity.this, checkInActivity.class);
        getApplicationContext().startActivity(intent);
    }

    private void startPrint(){
        showToast("프린트를 시작합니다");
    }
    */


}
