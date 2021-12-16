//データの読み込みや、データのリセットを行う処理を記述するアクティビティクラスです。

package to.msn.wings.sanmokunarabe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ShowDatabase extends AppCompatActivity {
    ArrayList<HashMap<String, String>> data = new ArrayList<>();
    SQLiteDatabase db;
    String[] columns = {"date", "result"};
    int[] ints = {R.id.date, R.id.result};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);

        if(MainActivity.operateDatabase != null){
            try(SQLiteDatabase db = MainActivity.operateDatabase.getReadableDatabase();
                Cursor cs = db.query("judge", columns, null, null,
                        null, null, null, null);){
                cs.moveToFirst();
                for (int i = 0; i < cs.getCount(); i++) {
                    HashMap<String, String> row = new HashMap<>();
                    row.put("date", cs.getString(0));
                    row.put("result", cs.getString(1));
                    data.add(row);
                    cs.moveToNext();
                }
                Collections.reverse(data);
                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        this, data, R.layout.list_winlose,
                        columns,
                        ints
                );

                ListView list = findViewById(R.id.list);
                list.setAdapter(simpleAdapter);

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void btnReset_onClick(View view){
        if(MainActivity.operateDatabase != null){
            try(SQLiteDatabase db = MainActivity.operateDatabase.getReadableDatabase()){
                new OperateDatabase(this).onUpgrade(db, 0, 1);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(this, to.msn.wings.sanmokunarabe.ShowDatabase.class);
        startActivity(intent);
    }

    public void btnToMainActivity_onClick(View view){
        Intent intent = new Intent(this, to.msn.wings.sanmokunarabe.MainActivity.class);
        startActivity(intent);
    }

}