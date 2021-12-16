//メインメニューの処理を記述するアクティビティクラスです。
//行うことができる操作は画面下部のボタン２つを押すだけで、
//「対局開始」ボタンを押すと、対局画面のアクティビティクラス(to.msn.wings.sanmokunarabe.MainActivity.class)へとインテントします。
//「設定」ボタンを押すと、設定画面のアクティビティクラス(to.msn.wings.sanmokunarabe.Settings.class) へとインテントします。
//現在、就職活動と並行し製作を継続している部分です。

package to.msn.wings.sanmokunarabe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {
    MediaPlayer mp;
    MediaPlayer se;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ConstraintLayout constraint = findViewById(R.id.constraint);
        constraint.setBackgroundResource(R.drawable.board);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mp = MediaPlayer.create(this, R.raw.mainmenubgm);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mp.release();
    }

    public void btnStart_onClick(View view){
        ringSe();
        intent = new Intent(this, to.msn.wings.sanmokunarabe.MainActivity.class);
        startActivity(intent);
    }

    public void btnSetting_onClick(View view){
        ringSe();
        intent = new Intent(this, to.msn.wings.sanmokunarabe.Settings.class);
        startActivity(intent);
    }

    public void ringSe(){
        se = MediaPlayer.create(this, R.raw.decide);
        se.start();
    }

}