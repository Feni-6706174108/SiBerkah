package org.d3ifcool.siberkah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{
    private ImageButton pendidikan;
    private ImageButton tempatIbadah;
    private ImageButton bencanaAlam;
    private ImageButton disabilitas;
    private ImageButton pantiAsuhan;
    private ImageButton hadiah;
    private ImageButton hewan;
    private ImageButton sarana;
    private ImageButton lain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        pendidikan = (ImageButton) findViewById(R.id.imageButtonPendidikan);
        tempatIbadah = (ImageButton) findViewById(R.id.imageButtonMosque);
        bencanaAlam = (ImageButton) findViewById(R.id.imageButtonTsunami);
        disabilitas = (ImageButton) findViewById(R.id.imageButtonDisabilitas);
        pantiAsuhan = (ImageButton) findViewById(R.id.imageButtonPanti);
        hadiah = (ImageButton) findViewById(R.id.imageButtonHadiah);
        hewan = (ImageButton) findViewById(R.id.imageButtonHewan);
        sarana = (ImageButton) findViewById(R.id.imageButtonSarana);
        lain = (ImageButton) findViewById(R.id.imageButtonLain);

        pendidikan.setOnClickListener((View.OnClickListener)this);
        tempatIbadah.setOnClickListener((View.OnClickListener)this);
        bencanaAlam.setOnClickListener((View.OnClickListener)this);
        disabilitas.setOnClickListener((View.OnClickListener)this);
        pantiAsuhan.setOnClickListener((View.OnClickListener)this);
        hadiah.setOnClickListener((View.OnClickListener)this);
        hewan.setOnClickListener((View.OnClickListener)this);
        sarana.setOnClickListener((View.OnClickListener)this);
        lain.setOnClickListener((View.OnClickListener)this);
    }

    @Override
    public void onClick(View view) {
        if (view == pendidikan){
            Intent intent = new Intent(MainMenu.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
