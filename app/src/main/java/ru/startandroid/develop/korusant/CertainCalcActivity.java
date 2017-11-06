package ru.startandroid.develop.korusant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static ru.startandroid.develop.korusant.GlobalVariables.certainss;

public class CertainCalcActivity extends AppCompatActivity {

    ImageView AvatarCertain;
    TextView NameCertain;
    TextView KindCertain;
    TextView NumberCertain;
    EditText NumberCalc;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button plusBtn;
    Button minusBtn;
    Button multiBtn;
    Button devBtn;
    Button clearBtn;
    Button backBtn;
    Button update;
    RadioButton sellCertain;
    RadioButton madeCertain;
    Switch typeSwitch;

    int data;
    String FN;
    String st;
    int backParter;
    int backTulle;
    Certain check;
    Calendar calendar=Calendar.getInstance();
    Date currentDate=new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_calc);
        AvatarCertain=(ImageView)findViewById(R.id.avatar_image_calc);
        NameCertain=(TextView)findViewById(R.id.name_txt_calc);
        KindCertain =(TextView)findViewById(R.id.kind_txt_calc);
        NumberCertain=(TextView)findViewById(R.id.number_txt_calc);
        NumberCalc=(EditText)findViewById(R.id.number_edit_calc);
        sellCertain=(RadioButton)findViewById(R.id.sell_radio_calc);
        madeCertain=(RadioButton)findViewById(R.id.made_radio_calc);
        typeSwitch=(Switch)findViewById(R.id.type_switch_calc);
        update=(Button)findViewById(R.id.update_btn_calc);
        one=(Button)findViewById(R.id.one_btn_calc);
        two=(Button)findViewById(R.id.two_btn_calc);
        three=(Button)findViewById(R.id.three_btn_calc);
        four=(Button)findViewById(R.id.four_btn_calc);
        five=(Button)findViewById(R.id.five_btn_calc);
        six=(Button)findViewById(R.id.six_btn_calc);
        seven=(Button)findViewById(R.id.seven_btn_calc);
        eight=(Button)findViewById(R.id.eight_btn_calc);
        nine=(Button)findViewById(R.id.nine_btn_calc);
        zero=(Button)findViewById(R.id.zero_btn_calc);
        plusBtn=(Button)findViewById(R.id.plus_btn_calc);
        minusBtn=(Button)findViewById(R.id.minus_btn_calc);
        multiBtn=(Button)findViewById(R.id.multi_btn_calc);
        devBtn=(Button)findViewById(R.id.dev_btn_calc);
        clearBtn=(Button)findViewById(R.id.delete_btn_calc);
        backBtn=(Button)findViewById(R.id.back_btn_calc);
        data=getIntent().getIntExtra("name", 0);
        check=setCalcCertain(data);
        backParter=check.parterNumber;
        backTulle=check.tulleNumber;

        if (check.complect==1||check.complect==3)
        {
            NameCertain.setText(check.name+" ("+check.parterColor+")");
            typeSwitch.setChecked(false);
        }
        else if (check.complect==2)
        {
            NameCertain.setText(check.name+" ("+check.tulleColor+")");
            typeSwitch.setChecked(true);
        }

        if (typeSwitch.isChecked())
        {
            NumberCertain.setText("Количество: "+String.valueOf(check.tulleNumber)+" шт.");
            KindCertain.setText(check.tulleKind);
            try
            {
                String stringImg=check.tulleAvatar;
                Bitmap bitmapImg=convertToBitmap(stringImg);
                AvatarCertain.setImageBitmap(bitmapImg);
            }
            catch (Exception ex)
            {
                AvatarCertain.setImageResource(R.drawable.ic_default_avatar);
            }
        }
        else
        {
            NumberCertain.setText("Количество: "+String.valueOf(check.parterNumber)+" шт.");
            KindCertain.setText(check.parterKind);
            try
            {
                String stringImg=check.parterAvatar;
                Bitmap bitmapImg=convertToBitmap(stringImg);
                AvatarCertain.setImageBitmap(bitmapImg);
            }
            catch (Exception ex)
            {
                AvatarCertain.setImageResource(R.drawable.ic_default_avatar);
            }
        }

        if (typeSwitch!=null)
        {
            typeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (typeSwitch.isChecked())
                    {
                        if (check.complect==3 || check.complect==2)
                        {
                            NameCertain.setText(check.name+" ("+check.tulleColor+")");
                            NumberCertain.setText("Количество: "+String.valueOf(check.tulleNumber)+" шт.");
                            KindCertain.setText(check.tulleKind);
                            try
                            {
                                String stringImg=check.tulleAvatar;
                                Bitmap bitmapImg=convertToBitmap(stringImg);
                                AvatarCertain.setImageBitmap(bitmapImg);
                            }
                            catch (Exception ex)
                            {
                                AvatarCertain.setImageResource(R.drawable.ic_default_avatar);
                            }
                        }
                        else if (check.complect==1)
                        {
                            typeSwitch.setChecked(false);
                            Toast.makeText(CertainCalcActivity.this,"Парный экземпляр отсутствует", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        if (check.complect==3 || check.complect==1)
                        {
                            NameCertain.setText(check.name+" ("+check.parterColor+")");
                            NumberCertain.setText("Количество: "+String.valueOf(check.parterNumber)+" шт.");
                            KindCertain.setText(check.parterKind);
                            try
                            {
                                String stringImg=check.parterAvatar;
                                Bitmap bitmapImg=convertToBitmap(stringImg);
                                AvatarCertain.setImageBitmap(bitmapImg);
                            }
                            catch (Exception ex)
                            {
                                AvatarCertain.setImageResource(R.drawable.ic_default_avatar);
                            }
                        }
                        else if (check.complect==2)
                        {
                            typeSwitch.setChecked(true);
                            Toast.makeText(CertainCalcActivity.this,"Парный экземпляр отсутствует", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });
        }


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(1));
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(2));
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(3));
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(4));
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(5));
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(6));
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(7));
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(8));
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(9));
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberCalc.setText(makeCalc(0));
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m=NumberCalc.getText().toString();
                NumberCalc.setText(deleteLastChar(m));
            }
        });
        clearBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                NumberCalc.setText("");
                return true;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeSwitch.isChecked())
                {
                    check.tulleNumber=backTulle;
                    NumberCertain.setText("Количество: "+String.valueOf(check.tulleNumber)+" шт.");
                }
                else
                {
                    check.parterNumber=backParter;
                    NumberCertain.setText("Количество: "+String.valueOf(check.parterNumber)+" шт.");
                }

            }
        });
        backBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                check.tulleNumber=backTulle;
                check.parterNumber=backParter;
                NumberCertain.setText("Кол-во: "+String.valueOf(check.parterNumber)+" и "+String.valueOf(check.tulleNumber)+" шт.");
                return false;
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Story story=new Story();
                    int google=Integer.parseInt(NumberCalc.getText().toString());
                    if (typeSwitch.isChecked())
                    {
                        if (sellCertain.isChecked())
                        {
                            check.tulleNumber=check.tulleNumber-google;
                            NumberCertain.setText("Количество: "+String.valueOf(check.tulleNumber)+" шт.");
                            story.sell=true;
                        }
                        else if (madeCertain.isChecked())
                        {
                            check.tulleNumber=check.tulleNumber+google;
                            NumberCertain.setText("Количество: "+String.valueOf(check.tulleNumber)+" шт.");
                            story.sell=false;
                        }
                        story.id=check.id;
                        story.number=google;
                        story.component=true;
                        story.sellDate=currentDate;
                        GlobalVariables.storiess.add(story);
                        NumberCalc.setText("");

                    }
                    else
                    {
                        if (sellCertain.isChecked())
                        {
                            check.parterNumber=check.parterNumber-google;
                            NumberCertain.setText("Количество: "+String.valueOf(check.parterNumber)+" шт.");
                            story.sell=true;
                        }
                        else if (madeCertain.isChecked())
                        {
                            check.parterNumber=check.parterNumber+google;
                            NumberCertain.setText("Количество: "+String.valueOf(check.parterNumber)+" шт.");
                            story.sell=false;

                        }
                        story.id=data;
                        story.number=google;
                        story.component=false;
                        story.sellDate=currentDate;
                        GlobalVariables.storiess.add(story);
                        NumberCalc.setText("");
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(CertainCalcActivity.this,"Введите значение", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public String makeCalc(int Zahl)
    {
        FN=NumberCalc.getText().toString();
        FN=FN+String.valueOf(Zahl);
        return FN;
    }

    public Certain setCalcCertain(int image)
    {
        for (Certain certain: certainss)
        {
            if (image==certain.id)
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

    public String deleteLastChar(String s)
    {
        try
        {
            int g=s.length()-1;
            st=s.substring(0, g);
            return st;
        }
        catch (Exception ex)
        {

        }
        return st;
    }
}