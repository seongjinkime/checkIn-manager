package fishing.vip.daejin.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import fishing.vip.daejin.model.User;

public class SQLHelper extends SQLiteOpenHelper {
    private final String TAG = "SQL_MANAGER";

    public static SQLHelper sqlHelper;
    private Context context;
    private String table;
    private String name;

    public SQLHelper(Context context){
        super(context, "USER_DB", null, 1);
        this.context = context;
        this.name = "USER_DB";
        this.table = "USERS";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE " + table +" (");
        sb.append(" ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" PHONE TEXT, ");
        sb.append(" NAME TEXT, ");
        sb.append(" CNT INTEGER ) ");
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static SQLHelper getInstance(Context context){
        if (sqlHelper == null){
            return new SQLHelper(context);
        }
        return sqlHelper;
    }

    public void clear(){
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM " + table);
        db.execSQL(sb.toString());

    }

    public ArrayList<User> getUser(String number){
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + table + " WHERE PHONE LIKE '%"+number+"';";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor == null || cursor.getCount() == 0 ){
            return users;
        }

        cursor.moveToFirst();
        do{
            User user = new User(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            users.add(user);
        }while (cursor.moveToNext());

        return users;
    }

    public void addUsers(DataSnapshot dataSnapshot){
        String insert = "INSERT INTO " + table + " (PHONE, NAME, CNT) VALUES (?, ?, ?);";
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(insert);
        User user;

        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
            user = snapshot.getValue(User.class);
            Log.e(TAG, user.getPhone() + " : " + user.getName() + " : " + user.getVisitCount());
            stmt.bindString(1, user.getPhone());
            stmt.bindString(2, user.getName());
            stmt.bindString(3, String.valueOf(user.getVisitCount()));
            stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        System.gc();
        Log.e(TAG, "SYNC OK");

    }

}
