package lk.jiat.eshop.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import lk.jiat.eshop.R;

public class SplashActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            getWindow().setDecorFitsSystemWindows(false);
           WindowInsetsController controller = getWindow().getInsetsController();

           if(controller != null){
               controller.hide(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.navigationBars());

           }
        }else{
           getWindow().setFlags(
                   WindowManager.LayoutParams.FLAG_FULLSCREEN,
                   WindowManager.LayoutParams.FLAG_FULLSCREEN
           );


        }

        setContentView(R.layout.activity_splash3);

        ImageView imageView = findViewById(R.id.splashLogo);

//        Picasso.get()
//                .load(R.drawable.ic_launcher_foreground)
//                .resize(300,300)
//                .into(imageView);


        Glide.with(this)
                .asBitmap()
                .load(R.drawable.app_logo)
                .override(300)
                .into(imageView);

        new Handler(Looper.getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                     findViewById(R.id.splashprogressBar).setVisibility(View.VISIBLE);
                    }
                }, 1000);


        new Handler(Looper.getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.splashprogressBar).setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(SplashActivity3.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }, 5000);



    }
}