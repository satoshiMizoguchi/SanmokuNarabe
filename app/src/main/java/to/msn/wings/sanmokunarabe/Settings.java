//設定を行う処理を記述するアクティビティクラスです。

package to.msn.wings.sanmokunarabe;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    public void btnBack_onClick(View view){
        finish();
    }
}
