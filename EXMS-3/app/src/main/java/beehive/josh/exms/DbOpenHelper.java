package beehive.josh.exms;

/**
 * Created by Josh on 2016-11-28.
 */
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.ArrayList;


/**
 * Created by Josh on 2016-08-25.
 */
public class DbOpenHelper {
    private static final String DATABASE_NAME = "data.db";
    private static final String TABLE_DATA = "data";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context context;

    private static DbOpenHelper instance;

    public static DbOpenHelper getInstance(){
        if(instance == null){
            instance = new DbOpenHelper();
            instance.open();
        }
        return instance;
    }

    public DbOpenHelper(){
        this.context = MyApplication.getContext();
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        return this;
    }

    public void insertData(Data info) {
        mDB = mDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("code", info.getCode());
        values.put("name", info.getName());
        values.put("exp", info.getExp());

        mDB.insert(TABLE_DATA, null, values);

        mDB.close();
    }

    public ArrayList<Data> loadData(){
        mDB = mDBHelper.getReadableDatabase();

        ArrayList<Data> list = new ArrayList<Data>();

        Cursor mCursor = mDB.rawQuery("select * from data;", null);

        while (mCursor.moveToNext()) {
            String idx = mCursor.getString(mCursor.getColumnIndex("idx"));
            String code = mCursor.getString(mCursor.getColumnIndex("code"));
            String name = mCursor.getString(mCursor.getColumnIndex("name"));
            String exp = mCursor.getString(mCursor.getColumnIndex("exp"));

            list.add(new Data(idx, code, name, exp));
        }

        mDB.close();
        return list;
    }


    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table data (idx INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, code, name, exp);");

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS data;");
            db.execSQL("create table data (idx INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, code, name, exp);");
            Log.e("onUpgrade", "upgrade");
        }
    }

}

