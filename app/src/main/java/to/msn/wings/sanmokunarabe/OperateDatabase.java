//データベースの作成や更新を行いSQLiteデータベースをオープンするためのアクティビティクラスです。

package to.msn.wings.sanmokunarabe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OperateDatabase extends SQLiteOpenHelper {
    static final private String DBNAME = "sanmoku_database";
    private static final String TABLE_NAME = "judge";
    static final private int VERSION = 2;
    private static final String COLUMN_NAME_DATE = "date";
    private static final String COLUMN_NAME_RESULT = "result";
    private static final String createSQL =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_DATE + " TEXT," +
                    COLUMN_NAME_RESULT + " TEXT)";
    private static final String dropSQL =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    OperateDatabase(Context context) {
        super(context, DBNAME, null, VERSION);
    }

//    PRIMARY KEY AUTOINCREMENT
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createSQL);
        Log.d("debug", "onCreate(SQLiteDatabase db)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL(dropSQL);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db,
                            int oldVer, int newVer) {
        onUpgrade(db, oldVer, newVer);
    }

}
