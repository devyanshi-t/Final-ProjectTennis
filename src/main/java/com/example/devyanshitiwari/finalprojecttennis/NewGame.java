package com.example.devyanshitiwari.finalprojecttennis;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;

public class NewGame extends AppCompatActivity {

    String name1,name2;
    Button click,loc,start;
    ImageView imgTakenPic;
    Intent intent;
    String format,lastSetFormat;
    private static android.support.v4.app.FragmentManager fragmentManager;
    private Spinner spinner1, spinner2;
    private static final int CAM_REQUEST=1313;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2= (Spinner) findViewById(R.id.spinner2);
        name1=(((EditText)findViewById(R.id.p1)).getText()).toString();
        name2=(((EditText)findViewById(R.id.p2)).getText()).toString();
        click=(Button)findViewById(R.id.click);
        start=(Button)findViewById(R.id.start);
        loc=(Button)findViewById(R.id.loc);
        format=String.valueOf(spinner1.getSelectedItem());
        lastSetFormat=String.valueOf(spinner2.getSelectedItem());
        imgTakenPic = (ImageView)findViewById(R.id.img);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAM_REQUEST);

            }
        });

         loc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 Intent i = new Intent(getApplicationContext(),GetLocation.class);
                 startActivity(i);

             }
         });
         start.setOnClickListener((new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 Intent i = new Intent(getApplicationContext(),game.class);
                 i.putExtra("NAME1", name1);
                 i.putExtra("NAME2", name2);
                i.putExtra("FORMAT", format);
                 i.putExtra("LASTSET", lastSetFormat);
                 startActivity(i);




             }
         }));
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTakenPic.setImageBitmap(bitmap);
            File imagesFolder = new File(Environment.getExternalStorageState(), "MyImages");
            imagesFolder.mkdirs();
            File image = new File(imagesFolder, "image.jpg");
            Uri uriSavedImage = Uri.fromFile(image);
          intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        }
    }


}
