package ru.startandroid.develop.korusant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static ru.startandroid.develop.korusant.GlobalVariables.certainss;

public class CertainAddActivity extends ActionBarActivity {

    protected static final String LOG_TAG = "my_tag";
    TabHost.TabSpec tabSpec;

    private static final int SELECT_PICTURE=1;
    private static final int CAMERA_REQUEST=2;
    private static final int CROP_IMAGE=3;

    Certain certain=new Certain();
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

    boolean avatar;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_add);
        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        nameCertain=(EditText)findViewById(R.id.name_edit_add);

        avatarCertain=(ImageView)findViewById(R.id.avatar_choice_add);
        photoCertain=(ImageButton)findViewById(R.id.avatar_photo_add);
        galleryCertain=(ImageButton)findViewById(R.id.avatar_gallery_add);
        avatarCertain1=(ImageView)findViewById(R.id.avatar_choice_add1);
        photoCertain1=(ImageButton)findViewById(R.id.avatar_photo_add1);
        galleryCertain1=(ImageButton)findViewById(R.id.avatar_gallery_add1);
        parterOnly=(CheckBox)findViewById(R.id.onlyParter_check_add);
        colorParter=(EditText)findViewById(R.id.parterColor_edit_add);
        kindParter=(EditText)findViewById(R.id.parterKind_edit_add);
        numberParter=(EditText)findViewById(R.id.parterNumber_edit_add);
        nextBtn=(Button)findViewById(R.id.next_btn_add);

        tulleOnly=(CheckBox)findViewById(R.id.onlyTulle_check_add);
        colorTulle=(EditText)findViewById(R.id.tulleColor_edit_add);
        kindTulle=(EditText)findViewById(R.id.tulleKind_edit_add);
        numberTulle=(EditText)findViewById(R.id.tulleNumber_edit_add);
        saveBtn=(Button)findViewById(R.id.save_btn_add);
        tabHost.setup();

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Портьер");
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Тюль");
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        index=certainss.size();

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
                if (parterOnly.isChecked())
                {
                    if (!nameCertain.getText().toString().isEmpty() && !colorParter.getText().toString().isEmpty()
                             && !kindParter.getText().toString().isEmpty() && !numberParter.getText().toString().isEmpty())
                    {
                        certain.id=index;
                        certain.complect=1;
                        certain.name=nameCertain.getText().toString();
                        certain.tulleColor="";
                        certain.tulleKind="";
                        certain.tulleNumber=0;
                        certain.parterNumber=Integer.parseInt(numberParter.getText().toString());
                        certain.parterColor=colorParter.getText().toString();
                        certain.parterKind=kindParter.getText().toString();
                        certainss.add(certain);
                        CertainAddActivity.this.finish();
                    }
                    else
                    {
                        Toast.makeText(CertainAddActivity.this,"Заполните все данные", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    tabHost.setCurrentTabByTag("tag2");
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tulleOnly.isChecked())
                {
                    if (!colorTulle.getText().toString().isEmpty() && !kindTulle.getText().toString().isEmpty() &&
                            !numberTulle.getText().toString().isEmpty() && !nameCertain.getText().toString().isEmpty())
                    {
                        certain.id=index;
                        certain.parterColor="";
                        certain.parterKind="";
                        certain.parterNumber=0;
                        certain.complect=2;
                        certain.tulleColor=colorTulle.getText().toString();
                        certain.tulleKind=kindTulle.getText().toString();
                        certain.tulleNumber=Integer.parseInt(numberTulle.getText().toString());
                        certain.name=nameCertain.getText().toString();

                        certainss.add(certain);
                        CertainAddActivity.this.finish();
                    }
                    else Toast.makeText(CertainAddActivity.this,"Заполните все данные", Toast.LENGTH_LONG).show();

                }
                else
                {
                    if (!nameCertain.getText().toString().isEmpty() && !colorParter.getText().toString().isEmpty()
                            && !kindParter.getText().toString().isEmpty() && !numberParter.getText().toString().isEmpty() &&
                            !colorTulle.getText().toString().isEmpty() && !kindTulle.getText().toString().isEmpty() &&
                            !numberTulle.getText().toString().isEmpty())
                    {
                        certain.id=index;
                        certain.tulleColor=colorTulle.getText().toString();
                        certain.tulleKind=kindTulle.getText().toString();
                        certain.tulleNumber=Integer.parseInt(numberTulle.getText().toString());
                        certain.name=nameCertain.getText().toString();
                        certain.parterNumber=Integer.parseInt(numberParter.getText().toString());
                        certain.parterColor=colorParter.getText().toString();
                        certain.parterKind=kindParter.getText().toString();
                        certain.complect=3;
                        certainss.add(certain);
                        CertainAddActivity.this.finish();
                    }
                    else Toast.makeText(CertainAddActivity.this,"Заполните все данные", Toast.LENGTH_LONG).show();

                }
            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (parterOnly.isChecked())
                {
                    tabHost.setCurrentTabByTag("tag1");
                }
                else if (tulleOnly.isChecked())
                {
                    tabHost.setCurrentTabByTag("tag2");
                }
            }
        });
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
                    certain.parterAvatar=StringImg;
                }
                else
                {
                    avatarCertain1.setImageBitmap(image);
                    certain.tulleAvatar=StringImg;
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

    public String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        byte[] byteArray = os.toByteArray();
        return Base64.encodeToString(byteArray, 0);
    }

    public void Gallery()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Picture"), SELECT_PICTURE);
    }

}
