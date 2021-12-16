//対局処理などを行うこのアプリのメインの処理を記述するアクティビティクラスです。


package to.msn.wings.sanmokunarabe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    TextView tvGuide;
    int cnt = 0;
    int total = 0;
    ArrayList<Integer> ids;
    MediaPlayer mp;
    MediaPlayer se;
    Button tmp;
    int[] _bd = {0,0,0,0,0,0,0,0,0};
    int[] _vid = {R.id.bt0, R.id.bt1, R.id.bt2,R.id.bt3,R.id.bt4,R.id.bt5,R.id.bt6,R.id.bt7,R.id.bt8};
    boolean comMode = false;
    Random random;
    int randomId;
    Button[] btns;
    Vibrator vibrator;
    static OperateDatabase operateDatabase;
    int[] arrayToJudge = new int[8];
    String blackCircle = "●";
    String whiteCircle = "○";
    Date date;
    SimpleDateFormat sdf;
    TimeZone timeZone;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//        優しく一回ブッ
        vibrator.vibrate(100);
        vibrator.vibrate(1000);
        tvGuide = findViewById(R.id.tvGuide);
        tvGuide.setText(blackCircle + "の手番です。");
        ids = new ArrayList<Integer>();

        //データベースの準備
        operateDatabase = new OperateDatabase(this);
        if(db == null) {
            db = operateDatabase.getWritableDatabase();
        }
        sayGreeting();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

//    オプションメニュー押されたとき
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //対人モードへ
            case R.id.reset:selectSe();reset();
                            break;
            //対局に戻る
            case R.id.conti:selectSe();break;
            //COMモードへ
            case R.id.vsCom:selectSe();vsComp();
                            break;
            //メインメニューへ
            case R.id.mainmenu:selectSe();backToMainMenu();
                            break;
            //対局結果を見る
            case R.id.database:Intent intent = new Intent(this, to.msn.wings.sanmokunarabe.ShowDatabase.class);
                            startActivity(intent);
        }

        return true;
    }

    //指せたかの判定や、指した手の表示など
    public void bt_onClick(View view){
        //指せないところに指そうとしたとき(もうすでに置いてあるマス)
        if (ids.contains(view.getId() )){
            se = MediaPlayer.create(this, R.raw.kora);
            se.start();
        //ブブッ バイブ TODO なぜかアプリ落ちる
         //指せたとき
        }else{
            total ++;
            se = MediaPlayer.create(this, R.raw.igo);
            se.start();
            //指したのが●の時
            if(cnt % 2 == 0){
              switch (view.getId()){
                  case R.id.bt0:tmp = findViewById(R.id.bt0);
                                tmp.setText(blackCircle);
                                _bd[0] = 1;
                                break;
                  case R.id.bt1:tmp = findViewById(R.id.bt1);
                      tmp.setText(blackCircle);
                      _bd[1] = 1;
                      break;
                  case R.id.bt2:tmp = findViewById(R.id.bt2);
                      tmp.setText(blackCircle);
                      _bd[2] = 1;break;
                  case R.id.bt3:tmp = findViewById(R.id.bt3);
                      tmp.setText(blackCircle);
                      _bd[3] = 1;
                      break;
                  case R.id.bt4:tmp = findViewById(R.id.bt4);
                      tmp.setText(blackCircle);
                      _bd[4] = 1;
                      break;
                  case R.id.bt5:tmp = findViewById(R.id.bt5);
                      tmp.setText(blackCircle);
                      _bd[5] = 1;

                      break;

                  case R.id.bt6:tmp = findViewById(R.id.bt6);

                      tmp.setText(blackCircle);
                      _bd[6] = 1;
                      break;
                  case R.id.bt7:tmp = findViewById(R.id.bt7);
                      tmp.setText(blackCircle);
                      _bd[7] = 1;
                      break;
                  case R.id.bt8:tmp = findViewById(R.id.bt8);
                      tmp.setText(blackCircle);
                      _bd[8] = 1;
                      break;
              }

            //指したのが○の時
            }else {
                switch (view.getId()) {
                    case R.id.bt0:
                        tmp = findViewById(R.id.bt0);
                        tmp.setText(whiteCircle);
                        _bd[0] = -1;
                        break;
                    case R.id.bt1:
                        tmp = findViewById(R.id.bt1);
                        tmp.setText(whiteCircle);
                        _bd[1] = -1;
                        break;
                    case R.id.bt2:
                        tmp = findViewById(R.id.bt2);
                        tmp.setText(whiteCircle);
                        _bd[2] = -1;
                        break;
                    case R.id.bt3:
                        tmp = findViewById(R.id.bt3);
                        tmp.setText(whiteCircle);
                        _bd[3] = -1;
                        break;
                    case R.id.bt4:
                        tmp = findViewById(R.id.bt4);
                        tmp.setText(whiteCircle);
                        _bd[4] = -1;
                        break;
                    case R.id.bt5:
                        tmp = findViewById(R.id.bt5);
                        tmp.setText(whiteCircle);
                        _bd[5] = -1;
                        break;
                    case R.id.bt6:
                        tmp = findViewById(R.id.bt6);
                        tmp.setText(whiteCircle);
                        _bd[6] = -1;
                        break;
                    case R.id.bt7:
                        tmp = findViewById(R.id.bt7);
                        tmp.setText(whiteCircle);
                        _bd[7] = -1;
                        break;
                    case R.id.bt8:
                        tmp = findViewById(R.id.bt8);
                        tmp.setText(whiteCircle);
                        _bd[8] = -1;
                        break;
                }

            }
                    ids.add(view.getId());
                    afterPlaying();
        }

    }

    //手を指すのに成功した後の処理
    public void afterPlaying(){
        //勝敗が出たとき
        if(judge()){
            if(cnt % 2 == 0){
                tvGuide.setText(blackCircle + "の勝ちです。");
                se = MediaPlayer.create(this, R.raw.thankyou);
                se.start();

                registerData(db, "勝ち");
            }else{
                tvGuide.setText(whiteCircle + "の勝ちです。");
                se = MediaPlayer.create(this, R.raw.thankyou);
                se.start();

                registerData(db, "負け");
            }

            //勝敗がついたのですべてのマスを選択不能に
            for(int id : _vid){
                ids.add(id);
            }

            comMode = false;
            //引き分けか勝負が続いているとき
        }else{
            if (total == 9){
                tvGuide.setText("引き分けです。");
                se = MediaPlayer.create(this, R.raw.thankyou);
                se.start();
                registerData(db, "引き分け");

                //勝敗がついたのですべてのマスを選択不能に
                for(int id : _vid){
                    ids.add(id);
                }

                comMode = false;
            }else if (cnt % 2 == 0){
                tvGuide.setText(whiteCircle + "の手番です。");
            }else{
                tvGuide.setText(blackCircle + "の手番です。");
            }
            cnt++;
        }
        //COMモードでCOMの手番の時
        if(comMode && (cnt % 2 != 0)){
            random = new Random();
            btns = new Button[]{findViewById(R.id.bt0),findViewById(R.id.bt1),findViewById(R.id.bt2),findViewById(R.id.bt3),
                    findViewById(R.id.bt4),findViewById(R.id.bt5),findViewById(R.id.bt6),findViewById(R.id.bt7),findViewById(R.id.bt8),};
            //まだ置かれていないマスにランダムで置く
            do{
                randomId = random.nextInt(9);
            }while(ids.contains(_vid[randomId]));

            bt_onClick(btns[randomId]);

        }


    }

//    勝敗判定を行い結果を返す。３なら●が、-3なら○が勝ち。
    public boolean judge() {
        arrayToJudge[0] = _bd[0] + _bd[1] + _bd[2];
        arrayToJudge[1] = _bd[3] + _bd[4] + _bd[5];
        arrayToJudge[2] = _bd[6] + _bd[7] + _bd[8];
        arrayToJudge[3] = _bd[0] + _bd[4] + _bd[8];
        arrayToJudge[4] = _bd[0] + _bd[3] + _bd[6];
        arrayToJudge[5] = _bd[1] + _bd[4] + _bd[7];
        arrayToJudge[6] = _bd[2] + _bd[5] + _bd[8];
        arrayToJudge[7] = _bd[6] + _bd[4] + _bd[2];
        for (int num : arrayToJudge) {
            if (num == 3 || num == -3) {
                return true;
            }
        }
        return false;
    }
//    オプションメニューでリセットを選んだ時
    public void reset(){
        setContentView(R.layout.activity_main);

        tvGuide = findViewById(R.id.tvGuide);
        tvGuide.setText(blackCircle + "の手番です。");
        ids = new ArrayList<Integer>();
        cnt = 0;
        total = 0;
        _bd = new int[]{0,0,0,0,0,0,0,0,0};
        comMode = false;
    }
//    オプションメニューでCOMモードを選んだ時
    public void vsComp(){
        reset();
        comMode = true;
    }

//    オプションメニューでメインメニューを選んだ時
    public void backToMainMenu(){
        Intent intent = new Intent(this, to.msn.wings.sanmokunarabe.MainMenu.class);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        se = MediaPlayer.create(this, R.raw.yorosiku);
        se.start();
        mp = MediaPlayer.create(this, R.raw.bgm);
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

    //項目選択音を鳴らす
    public void selectSe(){
        se = MediaPlayer.create(this, R.raw.optionpress);
        se.start();
    }

    //対局画面が開かれた時間帯に応じて挨拶をトーストで表示
    public void sayGreeting(){
        date = new Date();
        timeZone = TimeZone.getTimeZone( "Asia/Tokyo");
        sdf = new SimpleDateFormat( "HH" );
        sdf.setTimeZone(timeZone);
        int now = Integer.parseInt(sdf.format(date));
        String greeting = "";
        if(now <= 10){
            greeting = "おはようございます";
        }else if(now <= 17){
            greeting = "こんにちは";
        }else{
            greeting = "こんばんは";
        }

        Toast.makeText(this, greeting, Toast.LENGTH_SHORT).show();
    }

    //COMモードの時のみ、データベースへデータを追加します。項目は日時と結果のみです。
    public void registerData(SQLiteDatabase db, String result){
            if(comMode){
                ContentValues values = new ContentValues();
                date = new Date();
                timeZone = TimeZone.getTimeZone("Asia/Tokyo");
                sdf = new SimpleDateFormat("y年MM月dd日(E)HH時mm分");
                sdf.setTimeZone(timeZone);
                String now = sdf.format(date);
                values.put("date", now);
                values.put("result", result);
                try{
                    db.insert("judge", null, values);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    db.close();
                }
            }
    }
}