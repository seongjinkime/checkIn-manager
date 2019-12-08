package fishing.vip.daejin.checkinmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDocument(currentDate());

    }


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



}
