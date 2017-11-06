package ru.startandroid.develop.korusant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;

import java.io.ByteArrayOutputStream;

import static ru.startandroid.develop.korusant.GlobalVariables.certainss;

public class CertainChangeActivity extends AppCompatActivity {

    TabHost.TabSpec tabSpec;

    private static final int SELECT_PICTURE=1;
    private static final int CAMERA_REQUEST=2;
    private static final int CROP_IMAGE=3;

    EditText nameCertain;
    ImageView avatarCertain;
    ImageButton photoCertain;
    ImageButton galleryCertain;
    ImageView avatarCertain1;
    ImageButton photoCertain1;
    ImageButton galleryCertain1;
    CheckBox parterOnly;
    EditText colorParter;
    EditText kindParter;
    EditText numberParter;
    Button nextBtn;

    CheckBox tulleOnly;
    EditText colorTulle;
    EditText kindTulle;
    EditText numberTulle;
    Button saveBtn;

    Certain check=new Certain();
    int data;
    boolean avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_change);
        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        nameCertain=(EditText)findViewById(R.id.name_edit_change);

        avatarCertain=(ImageView)findViewById(R.id.avatar_choice_change);
        photoCertain=(ImageButton)findViewById(R.id.avatar_photo_change);
        galleryCertain=(ImageButton)findViewById(R.id.avatar_gallery_change);
        avatarCertain1=(ImageView)findViewById(R.id.avatar_choice_change1);
        photoCertain1=(ImageButton)findViewById(R.id.avatar_photo_change1);
        galleryCertain1=(ImageButton)findViewById(R.id.avatar_gallery_change1);
        parterOnly=(CheckBox)findViewById(R.id.onlyParter_check_change);
        colorParter=(EditText)findViewById(R.id.parterColor_edit_change);
        kindParter=(EditText)findViewById(R.id.parterKind_edit_change);
        numberParter=(EditText)findViewById(R.id.parterNumber_edit_change);
        nextBtn=(Button)findViewById(R.id.next_btn_change);

        tulleOnly=(CheckBox)findViewById(R.id.onlyTulle_check_change);
        colorTulle=(EditText)findViewById(R.id.tulleColor_edit_change);
        kindTulle=(EditText)findViewById(R.id.tulleKind_edit_change);
        numberTulle=(EditText)findViewById(R.id.tulleNumber_edit_change);
        saveBtn=(Button)findViewById(R.id.save_btn_change);
        tabHost.setup();

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Портьер");
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Тюль");
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        data=getIntent().getIntExtra("id", -1);
        check=findCertainForChange(data);

        if (check.complect==3)
        {
            nameCertain.setText(check.name);
            colorParter.setText(check.parterColor);
            kindParter.setText(check.parterKind);
            numberParter.setText(String.valueOf(check.parterNumber));
            colorTulle.setText(check.tulleColor);
            kindTulle.setText(check.tulleKind);
            numberTulle.setText(String.valueOf(check.tulleNumber));
            tulleOnly.setText("Удалить портьер");
            parterOnly.setText("Удалить тюль");

            try
            {
                String stringImg=check.tulleAvatar;
                Bitmap bitmapImg=convertToBitmap(stringImg);
                avatarCertain1.setImageBitmap(bitmapImg);
            }
            catch (Exception ex)
            {
                avatarCertain1.setImageResource(R.drawable.ic_default_avatar);
            }

            try
            {
                String stringImg=check.parterAvatar;
                Bitmap bitmapImg=convertToBitmap(stringImg);
                avatarCertain.setImageBitmap(bitmapImg);
            }
            catch (Exception ex)
            {
                avatarCertain.setImageResource(R.drawable.ic_default_avatar);
            }
        }
        else if (check.complect==1)
        {
            nextBtn.setText("Сохранить");
            nameCertain.setText(check.name);
            colorParter.setText(check.parterColor);
            kindParter.setText(check.parterKind);
            numberParter.setText(String.valueOf(check.parterNumber));
            parterOnly.setText("Добавить тюль");
            tulleOnly.setText("Удалить портьер");

            try
            {
                String stringImg=check.parterAvatar;
                Bitmap bitmapImg=convertToBitmap(stringImg);
                avatarCertain.setImageBitmap(bitmapImg);
            }
            catch (Exception ex)
            {
                avatarCertain.setImageResource(R.drawable.ic_default_avatar);
            }
        }
        else if (check.complect==2)
        {
            tabHost.setCurrentTabByTag("tag2");
            colorTulle.setText(check.tulleColor);
            kindTulle.setText(check.tulleKind);
            numberTulle.setText(check.tulleNumber);
            tulleOnly.setText("Добавить портьер");
            parterOnly.setText("Удалить тюль");

            try
            {
                String stringImg=check.tulleAvatar;
                Bitmap bitmapImg=convertToBitmap(stringImg);
                avatarCertain1.setImageBitmap(bitmapImg);
            }
            catch (Exception ex)
            {
                avatarCertain1.setImageResource(R.drawable.ic_default_avatar);
            }
        }

        galleryCertain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gallery();
                avatar=true;
            }
        });

        galleryCertain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gallery();
                avatar=false;
            }
        });

        photoCertain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
                avatar=true;
            }
        });

        photoCertain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
                avatar=false;
            }
        });

        parterOnly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (parterOnly.isChecked())
                {
                    nextBtn.setText("Сохранить");
                }
                else
                {
                    nextBtn.setText("Далее");
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.complect==3 || parterOnly.isChecked())
                {
                    tabHost.setCurrentTabByTag("tag2");
                }
                else if (check.complect==1 && !parterOnly.isChecked())
                {
                    check.name=nameCertain.getText().toString();
                    check.parterColor=colorParter.getText().toString();
                    check.parterKind=kindParter.getText().toString();
                    check.parterNumber=Integer.parseInt(numberParter.getText().toString());
                    CertainChangeActivity.this.finish();
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (check.complect==1 && !parterOnly.isChecked())
                {
                    tabHost.setCurrentTabByTag("tag1");
                }
                else if (check.complect==2 && !tulleOnly.isChecked())
                {
                    tabHost.setCurrentTabByTag("tag2");
                }
            }
        });
    }

    public Certain findCertainForChange(int idOfCertain)
    {
        for (Certain certain:certainss)
        {
            if (certain.id==idOfCertain)
            {
                return certain;
            }
        }
        return null;
    }

    public Bitmap convertToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmapResult;
    }

    public void Gallery()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==SELECT_PICTURE)
        {
            try
            {
                Uri selectedImage=data.getData();
                CropIntent(selectedImage);
            }
            catch (Exception ex)
            {

            }
        }

        if (requestCode==CROP_IMAGE)
        {
            if (data!=null)
            {
                Bundle extras=data.getExtras();
                Bitmap image=extras.getParcelable("data");

                String StringImg=convertToBase64(image);

                if (avatar)
                {
                    avatarCertain.setImageBitmap(image);
                    check.parterAvatar=StringImg;
                }
                else
                {
                    avatarCertain1.setImageBitmap(image);
                    check.tulleAvatar=StringImg;
                }

            }
        }

        if (requestCode==CAMERA_REQUEST)
        {
            try
            {
                Uri selectedImage=data.getData();
                CropIntent(selectedImage);
            }
            catch (Exception ex)
            {

            }
        }
    }

    public String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        byte[] byteArray = os.toByteArray();
        return Base64.encodeToString(byteArray, 0);
    }

    public void CropIntent(Uri uri)
    {
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setData(uri);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_IMAGE);
    }
}
