package ru.startandroid.develop.korusant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static ru.startandroid.develop.korusant.GlobalVariables.certainss;
import static ru.startandroid.develop.korusant.GlobalVariables.storiess;

public class CertainStoryActivity extends ActionBarActivity {

    protected static final String LOG_TAG = "my_tag";
    TabHost.TabSpec tabSpec;
    ListView sellLIst;
    ListView madeList;
    int pos;

    Certain check=new Certain();
    Locale locale=new Locale("ru", "RU");
    Date today=new Date();
    Date yesterday=new Date();
    DateFormat dateFormat;
    MyAdapter1 adapter1;
    MyAdapter1 adapter2;
    ArrayList<Story> sellStory=new ArrayList<>();
    ArrayList<Story> madeStory=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_story);
        sellLIst=(ListView)findViewById(R.id.sell_list_story);
        madeList=(ListView)findViewById(R.id.made_list_story);
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        // инициализация
        tabHost.setup();

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Продажа");
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Произ-во");
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator("Поиск");
        tabSpec.setContent(R.id.tab3);
        tabHost.addTab(tabSpec);

        sellStory.clear();
        addToList();

        registerForContextMenu(sellLIst);
        registerForContextMenu(madeList);
        adapter1=new MyAdapter1(this, sellStory);
        adapter2=new MyAdapter1(this, madeStory);
        sellLIst.setAdapter(adapter1);
        madeList.setAdapter(adapter2);

        dateFormat=DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        Long time=today.getTime();
        long anotherDate=-1;
        time=time+(60*60*24*1000*anotherDate);
        yesterday=new Date(time);
    }

    class MyAdapter1 extends BaseAdapter
    {
        ArrayList<Story> stories;
        Context context;

        MyAdapter1(Context context, ArrayList<Story>stories)
        {
            this.context=context;
            this.stories=stories;
        }


        @Override
        public int getCount() {
            return stories.size();
        }

        @Override
        public Object getItem(int position) {
            return stories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View con=convertView;
            pos=position;
            if (con==null)
            {
                LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                con=inflater.inflate(R.layout.story_list_item, parent, false);

            }

            if ((position & 1)!=0)
            {
                con.setBackgroundResource(R.color.back);
            }

            ImageView ImageView=(ImageView)con.findViewById(R.id.avatar_image_story_item);
            TextView NameTxt=(TextView)con.findViewById(R.id.name_txt_story_item);
            TextView ColorTxt=(TextView)con.findViewById(R.id.color_txt_story_item);
            TextView NumberTxt=(TextView)con.findViewById(R.id.number_txt_story_item);
            TextView TimeTxt=(TextView)con.findViewById(R.id.time_txt_story_item);

            for (Certain certain: certainss)
            {
                if (certain.id==stories.get(position).id)
                {
                    if (stories.get(position).component)
                    {
                        try
                        {
                            String stringImg=certain.tulleAvatar;
                            Bitmap bitmapImg=convertToBitmap(stringImg);
                            ImageView.setImageBitmap(bitmapImg);
                        }
                        catch (Exception ex)
                        {
                            ImageView.setImageResource(R.drawable.ic_default_avatar);
                        }
                        if (certain.name.length()<=9)
                        {
                            NameTxt.setText(certain.name+" (Тюль)");
                        }
                        else
                        {
                            NameTxt.setText(cutWord(certain.name, 8)+"(.. Тюль)");
                        }
                        if (dateFormat.format(stories.get(position).sellDate).equals(dateFormat.format(today)))
                        {
                            TimeTxt.setText("Сегодня");
                        }
                        else if (dateFormat.format(stories.get(position).sellDate).equals(dateFormat.format(yesterday)))
                        {
                            TimeTxt.setText("Вчера");
                        }
                        else
                        {
                            TimeTxt.setText(dateFormat.format(stories.get(position).sellDate));
                        }
                        ColorTxt.setText(certain.tulleColor);
                        NumberTxt.setText(String.valueOf(stories.get(position).number)+" штук");
                    }
                    else
                    {
                        try
                        {
                            String stringImg=certain.parterAvatar;
                            Bitmap bitmapImg=convertToBitmap(stringImg);
                            ImageView.setImageBitmap(bitmapImg);
                        }
                        catch (Exception ex)
                        {
                            ImageView.setImageResource(R.drawable.ic_default_avatar);
                        }
                        if (certain.name.length()<=9)
                        {
                            NameTxt.setText(certain.name+" (Портьер)");
                        }
                        else
                        {
                            NameTxt.setText(cutWord(certain.name, 8)+".. (Порт)");
                        }

                        if (dateFormat.format(stories.get(position).sellDate).equals(dateFormat.format(today)))
                        {
                            TimeTxt.setText("Сегодня");
                        }
                        else if (dateFormat.format(stories.get(position).sellDate).equals(dateFormat.format(yesterday)))
                        {
                            TimeTxt.setText("Вчера");
                        }
                        else
                        {
                            TimeTxt.setText(dateFormat.format(stories.get(position).sellDate));
                        }
                        ColorTxt.setText(certain.parterColor);
                        NumberTxt.setText(String.valueOf(stories.get(position).number)+" штук");
                    }

                }
            }

            return con;
        }
    }

    public Bitmap convertToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmapResult;
    }

    public void addToList()
    {
        for (Story story:storiess)
            if (story.sell==true)
            {
                sellStory.add(story);
            }
            else
            {
                madeStory.add(story);
            }
    }

    public String cutWord(String wordForCut, int symbolNumber)
    {
        String st=wordForCut.substring(0, symbolNumber);
        return st;
    }
}
