//アプリを起動するとはじまるオープニングアニメーションの処理を記述するアクティビティクラスです。
//テキストビューにセットした文字列に対して、アニメーションファイル(R.animator.anim)に記述されたアニメーションを適用します。
//アニメーションが終了すると、メインメニューのアクティビティクラス(to.msn.wings.sanmokunarabe.MainMenu.class)へとインテントします。

package to.msn.wings.sanmokunarabe;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Opening extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        TextView textOpening = findViewById(R.id.textOpening);
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.anim);
        anim.setTarget(textOpening);
        anim.start();

        anim.addListener(
                new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Intent intent = new Intent(Opening.this, to.msn.wings.sanmokunarabe.MainMenu.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }
        );
    }


}