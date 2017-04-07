package bengeos.com.dailynews.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bengeos on 1/16/17.
 */

public class SQLHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "EthioTubes";
    private final static int DATABASE_VERSION = 1;

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void createTable(String Db_Table, String[] fields) {
        // Note: assumes fields[] starts with the "id" field (the one that is INTEGER PRIMARY KEY AUTOINCREMENT)

        String str = "(" + fields[0] + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (int x = 1; x < fields.length - 1; x++) {
            str += fields[x] + " TEXT, ";
        }
        str += fields[fields.length - 1] + " TEXT);";

        getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " + Db_Table + str);
    }
}
