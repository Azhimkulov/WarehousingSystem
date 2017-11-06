package ru.startandroid.develop.korusant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static ru.startandroid.develop.korusant.GlobalVariables.certainss;

public class MainActivity extends AppCompatActivity {

    ListView CertainsList;
    ListView LvOptions;
    MyAdapter adapter;
    Intent intent;
    DrawerLayout drawerLayout;

    int pos;
    String colorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CertainsList=(ListView)findViewById(R.id.certains_lv_main);
        LvOptions=(ListView)findViewById(R.id.lv_options);
        drawerLayout=(DrawerLayout)findViewById(R.id.activity_main);
        final String[] options=new String[3];
        options[0]="Добавить";
        options[1]="Показать";
        options[2]="Зарплата";

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        LvOptions.setAdapter(adapter1);

        certainss.clear();
        CreateCertains();

        registerForContextMenu(CertainsList);
        adapter=new MyAdapter(this, certainss);
        CertainsList.setAdapter(adapter);

        CertainsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent=new Intent(MainActivity.this, CertainCalcActivity.class);
                intent.putExtra("name", certainss.get(position).id);
                startActivityForResult(intent, 10);
            }
        });

        LvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (options[position].equals("Добавить"))
                {
                    Intent intent=new Intent(MainActivity.this, CertainAddActivity.class);
                    startActivityForResult(intent, 10);
                    drawerLayout.closeDrawers();
                }
                if (options[position].equals("Показать"))
                {
                    Intent intent=new Intent(MainActivity.this, CertainStoryActivity.class);
                    startActivityForResult(intent, 10);
                    drawerLayout.closeDrawers();
                }
            }
        });
    }

    public void CreateCertains()
    {
        Certain certain=new Certain();
        certain.id=0;
        certain.name="Сакура";
        certain.parterColor="Зеленый";
        certain.parterKind="Лён";
        certain.tulleKind="Органза";
        certain.parterNumber=128;
        certain.tulleNumber=58;
        certain.complect=3;
        certainss.add(certain);

        Certain certain1=new Certain();
        certain1.parterAvatar="iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAABHNCSVQICAgIfAhkiAAAIABJREFU\n" +
                "eJx8vcmvZdeV5vfbe5/2du++NhpSDIqk+kwlstIouADDRsGTtOGB/yUDNamR/wnDE8+MMjxzGi5X\n" +
                "SUpJTJEUJXZBMpoXr7/d6c/uPNjnnhdBUb7EY+DduM05e+29mu/71grhvffWWm5XO/6X//V/4/L8\n" +
                "Of/u3/1PnL98wXvvvcv+0fc9v/7Nh/zi5z9jNsvpuo4oihHCU5Y1JydHGCH49//+f+atx0/42c9+\n" +
                "wm67RgnPv/23/xUC8N4DIIRACAHDc9778bnXf7+6LfkP/8f/ibaK/+G//29568GCm5s7zs5O2O5q\n" +
                "Xpxf8flXzyl3a/7xH/8R6Vt+/duPKVv4H/+7f0OaKK6vVjx4eIqU4T60sfz2oxdkmeSD9x4wz1ME\n" +
                "f/nwHtZFT1mUPDidEyUxv/3tJ3zzouLnP/2AxSymbVtmsxlpZDg6OkBKx2ZdMJ9P+Q//+++Rque/\n" +
                "/m9+yeHhAc47qqrl5mbNW28/4PZ6xYMHZ+zqkqdfP+PJu084Wc6QAFJK1HDFvdYIcb94AB749tlz\n" +
                "vvjiS+qmQQjBblcwmWRIqVBK4ZxHAFmaURQlm80GKSXT2ZymsXjvkftVee2xN8z9QnisdcE4gFIq\n" +
                "PGcsUkqSJMEYC4C1Fmctf/d3vwQ8SimiKEJrjfee3a5gOs0Rwo/3o6TkcLnA6D6s+v/Pw7n7dTDG\n" +
                "473AGIN1ljiOSZKUsizJ85z9OsZxPH6stQbnPftvMcZy/uoVq9WWKFJ4bzHGY4xFCocQcL9Cw+7s\n" +
                "tcY4yPMMa+341x999EcePnhEmqQIAc45AJIkJooUWveDQVKMc2hrEAqMdaw229EY3zXA/jn/fYvj\n" +
                "PUpJrAMzXMtiMaOqKoSQ5FnGyfGS45NDinJDniVEkcQYA96zWe+YzSdvGFsIwXIR463Bub9uEA9o\n" +
                "C84Hwzx9ekHfh02wX/GiKFgsDt54Xz5J6XqNE47emHGdhJBIqZAy5fnLFbPFlLIq6XqLdxApFYy6\n" +
                "XxAhQEhw3uGcY7FYUNf1eHXWWPI8YT7L/2Ix8zzDDF8exTEeR5qmCCGo6grn3Ruv/65Rvs+FQbge\n" +
                "IQXOuXFzKKVIkpimaZhOcx4+OKZtG+IoIooUUsjxhOzf87rBw0JLPHJcrO+3iB9PYt8bvn12xfnF\n" +
                "CuMs1jn6vsdai1ISXnN6UaSQUqCNDid4NLonz2MePDhmvV4jAK0Nbdvhufce4wmRSg4fHo5WnmdI\n" +
                "GXabAB6cnfHuk3eIIoUQ4o2biaIIpRR970mTBO89WRbe33YtcZL89Rt/zSh7g4wLKQXyOwYBSNOU\n" +
                "pmlQSrJYzKjrmnySB9erFNZaPJAkKVrr19bYU5YlX3z5LQj5vaf19cf+NHjvmS2WtJ3BWIuxhu12\n" +
                "y2SS46z9C8PGSYSKItqupa71uBmiSDGdTphOJhjrUEpxeXmFkgoGx3ZvECFQSoEXGG1RSiGlHG7O\n" +
                "8977Tzg9O8H54Kv3cWO/mHmeUxQlURTjnBuM6wFB33ev3aTHWv/Gwv8148QqAu+xzqONef0VQHCb\n" +
                "RVGwmM2JpEIIUJGk73uccyyXM3bbCnzw785L1puSi1VN1Rmk/H6D+MHvG2PwePI8QTdbHj1YYoxB\n" +
                "a0PXadIkAhwWjxsWve8swoP3FgS0bYe19wabTRPOjifUZUNVtaRpjLV6XMvXomwwiBCCru8BSJKE\n" +
                "vu/x3vP2228Rx/G4YCcnx6xWqzduZDadYK3BWhtOlhAkSUxVVeNrjNHsihLnhw3w1xbEhyQhUhHG\n" +
                "GLqueyNLy/OQ6XVdR5KEQCqEQA6n13vHdDYN77v/5MGlQF3X3x+3hu931obN6D1CwHvvP+H6+jIE\n" +
                "9eFUWOfGa/XDBvnd7/5A05ox4ZjPc9brNc6FTTHJJ5yeniCEoO+6sKbDdX/HIJBlGUII6josoFIq\n" +
                "7JIhGDZNRRxHQAj6IZjfu4TpJGaSpeje0HcdeZb8RWallKLXnl3Rfu9iSCmRUg436QCHMYambkKw\n" +
                "hnASlMIYO2RCYbHD30nssBmcc0xnE/phgwkcx0cHZKnEWY3WBj8s6nevwTlH13W4wShvvXVInIRT\n" +
                "ba1BKYHH44VHIZBAXbe8eHnLb3/3GXVXY5xByuBRmqYJn60ECEdVlTx8eErf9yghUPKvGAQpqJp6\n" +
                "fG5/SiAEodcXeD6fjydo/zhYLu4XbUjjDhYL6roZDCJJkgijgxt7PYjvFzSOo2G3K6zzeAEyiui6\n" +
                "/rWs5T7evFHfSDG6QikleZ4NLixsqjiOeefxGd6E53r9uit80yhlWWNsMFik4NHZSTj91pBPUoqi\n" +
                "wDmPww8puuDgYMbd7TV3q23I0IDZbMZmswFCPZemCW3bkmUpve6Gjcf3G0RKycuX5+NzeZ7Tti3e\n" +
                "8xfBa593760PMJ1MX1uo4Dom+X1mJoRgOsk4OJgH1/CdzxTi9dRY4J0f4pYMO3qfLXmPlGLM0IQY\n" +
                "0mpAG4MxZgjyIXjfnxKY5RlKhnRW/pXgLoTA+fvYZQ1MJiGbtMYwmeSUZRmuZbjwLMvIs5Qf//hH\n" +
                "aG2w1vPs+cUbn2uMGWsr5zx93yHkfZYp7y/Ak6YhRsRxxGq1Hhed4Wv1d3ZTUVR4D9a6cWGzPME5\n" +
                "hzYGB6PbybJ0dDlJLIkjiXP3ReBfLIZzIBwehzGOuu6IVBLSRO8xvSaLU7Bu8On3xaezluvr6/Gz\n" +
                "QrZoX7tpSxwptHZE0ffHMYb31VWJd46+19R1jfMG6wzgODk6YrO6G9dHCME//MPfUBY3HC5nbDZb\n" +
                "vvzyFcaEJKnrOtq2JY4jkiSmbXXwMM4hhs3ymkHkWDsEH+rH4LUPvt9duI8++oivv/4WKeUYS7xz\n" +
                "WOuwxgZzuPsM5M0CMOT5fy2wQjC09x5jDE+ffk1ZFeNpDG7Jk6Upfde99q5gTK01RVGETZJl1HU1\n" +
                "ujJjLG4I1t/37SFAe/q+R2tDXbeURUkURW8YP8tSnHM0TTNW57NZxnJ5gBDBdSZpjJBiSMfdULso\n" +
                "4jgZT25Y8++cECkVSRKC+ovnL8jznLpu6PuePM8RQhBFaiyWIATTDz/8hJubDSDxzoMPNxN8XFg0\n" +
                "o/UbWNX+vfsM6S8WZNjpfa8RQwGnjeX88hbnQiwLGZ9nuZxTFAW618N9BIPEcUzXdTSt4eZ2NS6Y\n" +
                "956qaUIwH67vuw9jHJeXt9R1jTGWrtcY40jibNykAkB4Fgfh+8eNJeDtHzwcDGs5PJyhhkTF2eEF\n" +
                "wOnpMbe3N+A8SnyPy4oiRRJHICR//NOfubq8xjvGEyKEYDabvZHCfvDBezjnuL6+QmtNVdUsl0uc\n" +
                "tzhvx0JTG4NHvLEo+xMivgfaa5uOttV0fY9QEpwljSOassI7T1nUJGkc7k0Ijo6OuL29BcTo7pSK\n" +
                "USrm4z9+ybNnr0bX2/eauiqRww7+viOyXhVcXFzRti1t27HdFCyXM6ztccbircX7cFKkFEyynHqI\n" +
                "JxDirrOGSArefusUrXum0wld1yNl8DbTaY7H0bQtQsq/NIhSkjiKAMHBwZLnz1+8lmaGG82ybNhB\n" +
                "ZrDyCe+99x5ZltG2LXVdM5lMsC5kImka0l7da7799gVV3YxHGQRKRlj75op477m9u2O92qKiaNyN\n" +
                "s+mUs9NTlFS0Q5UupEAqSZbnLA8P2e0KxLDw0+mU6+sbPvnkz3gf0AQhBK9eXbA8OIAhdf7ufuj7\n" +
                "nr43LA8O0VpjbYgfUSSwzo5p8HC1eO+IBnRg/9hsNiOUM52m9H1Hmib3J4uA8RljKKtyQCS+C51I\n" +
                "gYrCy4ui4MHDU7q+GwsuCME+TZOxNomimL/7u5+SpQlVUbNYzEmzBG8dVodswjjJb37/EX/881fU\n" +
                "rQ4XNRg4nyQ07X2K7T30naZtNEmSkKUBTfXec3h0wFtvn1EUW05OjsF5vDV4Z/C2J1YCrfVwwx7n\n" +
                "FF988Q1PnjwiigUQ3Gnb9AP6a1HqTVQbD11rSBPJ6ekSbTrAgg+L7ZzDOYOzAbczVvDFl98gIoUc\n" +
                "4stuVxLHKUJ4nLNUVc90Ngtg6CTF2LB2L1++5OXLl8MGt395QsIpCTsmTmKWyyXeB0j49UeWZXRd\n" +
                "N1Ti4feHDx+M0HcSxwHA02HxV+sN19c3vPvuE/IswzmHlDJAzcPOeH3H9b3BWkOWx8RJPCyCG/xw\n" +
                "uJgkjUNRNoQqhERIyWQyoWkahACte9q2Ic9S3nr8eEyLQzboh2r/fnO9bhVrQ4zq+444jsakxg1B\n" +
                "2Q+b5/z8gl/96p/xzhFFEX3fs91umc0mwWBGo3VPpKLhfsPnN01NWZaUZUEcRSHL+uuVOqRpglIB\n" +
                "AU6+BxjM8/scXClJmsY4FyDvKI6RStLrkPdvNjsePnoM3jKdpAOs4ccNkAyLfl/cSebzWYD1lSJJ\n" +
                "Eqy1tF0bYH88DMi0EALvwDrPs+cvB0OH01CVBU/e+QGnRwdM84wkiRFCYozGeUso2UKhOLobEYw9\n" +
                "nU0wpsf0PXmWoZQk5CnDCXGO29s7/vCHPyFVhvChWi93FcuDxbBKwXjTaTa6oziOkVLQtQ1pmrBc\n" +
                "LhGEGmt///dpLzDJcqJIsdttx/T3u07WD1nU/gQYYzHWIpUEEUDKLE2HCh5m0wys5v0f/gClxAhY\n" +
                "hsUXA0jpxqtI04jl4YI4jpBCcLCYB19b3sM53oPwctjPju2u4f/5f39N3/eB+HHBgO+9/x4np8d0\n" +
                "fTsY5P77gz/3b/wOYQNOp3kg2IQnTRQHyxmruzUgBpzO8vnnT5nPJxwt50glcT5A8vs4G5IL+wYH\n" +
                "FkURIZkx5FnGwXKBikRw8981CASSJEszurZjPp+/UV/sH+v1mj/+8VOyLKMsy7F6lnugUAiyLKVt\n" +
                "W/pec7Q84Pj4kEmejkf79QC4T4X3LjCKFXkebkpKwWI+D27LOpSKmE6ntO2+7gjZ2n/6z7+iqprB\n" +
                "IBHeO6yzHB0fDDFPD5kiga/Z+7phEfYp8nD5GONw3iFF8ACHh0vKohpd1m5XEEUJm/WKf/2v/wv2\n" +
                "wf0NlnWoxF9P5IQQTPIJWmtOz05hqIX2m+XNE+LDz3w+H93NHlzcP6y1fPrpn/nooz+jVPCZu82W\n" +
                "2WTKwZCPh7iSsN0VXNys0KZnOskodg3e3aelr198kiQ0zT3YuMe3VCSIYgXCoyJJFClOTo65ubkh\n" +
                "uBzJ+fkNxbbg8GBJnudEUpGoPY3r2Gw2HB0dhe8T8PjxGbc3q5ECeH1T7H/qugoMnnBIFZDlx289\n" +
                "4urqEmst2vSkWcRPfvQOj84OAh+T54jX4fwBZZAyIMf7+0qShEglA3/Uo0SoQ/b7azSIJ7CFIZD6\n" +
                "QcQQDdYPGcpms+H29paT4+NxZ1kXcP/pdBqq6OFLjTFUVY1SEev1Bq07lBLj+75boUdDihvc4P0m\n" +
                "2GdlAfZ2g8HvY9HV1SVnZye884O3g48e0uG+72lbTZLEo4/23jOfz4nj6I3vGO9l78qExNg3dQBJ\n" +
                "HPP40UOstaRJwsFiwY9+9COaph0Jutf9055UE8KjdQBgb29XfP7FV8RxgtYBitnjbX/psgQ4b4mT\n" +
                "kMG0bR9qitf8q9GWvtN88MG7eO9CATTsPIDlckldN2RxgmQIuNZzcXnJ8vBgfN3+5l83SpalIypr\n" +
                "rRsXIkD/fqRMw/ccDHyGYz6bopTnX/3DL4NvzjOEcLRtx+XlNScnx29kciHdzjCmZ39B+1gWTkc9\n" +
                "MIFu/DutNdb1CBViSCQFbz86Q/c9u92OySRHCJhMcpq2Ga7b4YYUV0mJ7jTPn1/ym9/8nqbrKMsK\n" +
                "NdAXr9/vGzFESjkyfn0fTkiWZSN+NJnkvPPO2zw4OwX4C4JpOp2OuyrcpCCO1eBf3yyKpVT0vX7t\n" +
                "9+FSvA8QzP7iBgPtiaG9QY2xWOt49Oghbz9+jPAeY3qyoRhtm5ambonj+A3KuarqYQeH9HZv9P3i\n" +
                "h8UJAVkM13V1dc1//I+/ZjpZ4GxAkuM45vr6moODg/FkLJYH41oFuEcPME5E39bEsaKuSp6/eEnf\n" +
                "t+i+Gwmwv1qHpGmI+Psgl6ZpEDsImEwn/PwXPyNNE5IkGfmF1/Gt6XQ6WN0QRxFZloZT9tppuLm5\n" +
                "GW7av5GF7LO6/cUJoG1bmiYkCK9nZ1mWU1UlaZry+NFDri4vmEwm4ybp2v4NyiDUCYZ//u1vef78\n" +
                "+RsG3i/gHiIKCLbFO48Sks1mwyeffMpHH30SsiytOT8/J8sCHN+1HfiwfkdHR9zd3eF9yES1djRV\n" +
                "zW63I4kD1/7q1QWb9ZqiKAZeJCQxiO+kvUkckaoI4fwYZIUQHB4ecnt7i1KKxWJOb/rxFBwczNnt\n" +
                "tm/UFqdnxzR1RSwVcRTRdy16SBQAvvz6Jc9eXhNFctxRXacDAjrAIftH2zY4o7m7W3F9V9B3Bmcd\n" +
                "3lt034K37HY78nwS0lolEcJTtyWTaTpurMCpC7755jmf/PErPCporYbTuI9VWZZSFOWgogm8e5Kk\n" +
                "vPvkCU+/+gpjenrTc7dZI6WnrnbstmtevnzBy5evuLq64etvvuXm5pa+1zx79owvv/6S5+fP6Lo1\n" +
                "Z2cHrFa3XN3ecHl9hYgEXtwnFBJ6oAPfspgnPH7rAd5pVqtb2rai7yq866nLLV1Tslnd4GyP6Rt0\n" +
                "V+Ndj7M9VbmlqQvapkDgMUZjnaFta6zV3N7eoPsWa1v6vuWf/umfuFtt6HWoZnebW9pmR9/VeB+E\n" +
                "FW7YZWmasF6v+fVv/plPPv6Yzz/7nMuLV3RdR13XPH/2jMkkfyMIN1XN6ckplxcXA7Ib0N3j42Oi\n" +
                "KGK73Q7CAj+SRnuPoI2hadrhZDveeedt6rriF3/zc+wAt1dVSVnuKIotm/WKi4sLnj59yrNnz9G9\n" +
                "ZrVa0fc9r1694u72lu12Q1WWZGnCYjFju93QNA1xrDCmR5sWbVqivrtBKg3O4YxGUGFswedf/JEs\n" +
                "tUzzCIlgvVpx/eopddMSxTGzSToGy7bp6HsT3JNz3FyeY01HWW4RwpMl8Lvf/5pf/uLHHC1z5osp\n" +
                "SPj9v3zML37+Iy4uNrS7G5I4Ip8dslie4IWgN46qKcnziGK3409/+hzXbHj7rUdICc6HdLVta3bb\n" +
                "NetpzK4I9cjWdKzu7tjcrXi5p3KN48nbj/jNh39Ga0PTNINSRjGdTsdTOZ/PuLq+oWkazl++II0c\n" +
                "bz0+4w8ffYKxhqoqqYqYMpcYndJJhVQxX379DdZ5njx5lyhSOBtO79V1RJ4lCG8RWPJUcnVzzcHB\n" +
                "HJymKG+5WQWKIqrK52RpgEesc2Qp4BrKYsPlxUvm0wQlgzLx5vYK70GphHYW/J41IWAXRclsNkUI\n" +
                "RdfV6L6jbTuSSCKBYrfixfOvicQjTg8PiSjo24hvv/kcazoWOVjjSbOU7faa1TZwLLqr8N6h+46u\n" +
                "a7i5uWJ5MB0Fal3X0nc1q7trqt0dm7IN9ZDXvDg/p69WrFY3LBYLrA8o68FBjveGXVliTM0HP/oA\n" +
                "47rguH0oTtu+oaxKXl1cYHWNc1DXW6y1dF1LU5fUlcLbHi0VXkZcXl3w7fMrvAg4mLGaKJbcXF/z\n" +
                "+PEjuq5BSIV1bkgaIry39E1FtbUIKYnaZoMUE4QMeE2SSLw3g8/39F1LmqYEBNXgHUgRdKkhTWy4\n" +
                "u1txenrGdrtleXhEFCu00RhjSaNAqUYqpm1qymLLlIgfvvOIsjbcXF8wn8/weRCLea8xPbx49ZI8\n" +
                "n+BsKPD29KcQAUebTvKBa7c0TcVut0XiKGo9gJ8t6+2WxPdUVUXXtcgoRijFdJLRNg3n5+f84ufv\n" +
                "U5Q7YM/NCBzh+pu2HcRuIVM7WC6w9py+7+j7hK5tkMJjhAQVoY0mSTN++7sPybIMazXGaKSw1HWF\n" +
                "nKRo24y0grGGSDi07ugahwci27WYWI3uJ44EWnf8+ekFDx4+5mgWcvSyLAfeowP2eiToDfzpy+f8\n" +
                "0M+ZJxprDEqCNRqtNXKWoFTgsb1zFEVNmiUcHsQYXbMzJdInOHMvlNa6JhY15y/usDqAeb2x3KwL\n" +
                "fvLuGUVRkCYx3lki4dFtQ9dU4A1tY7Cmp25aVus1i8TRtB1Nr5mkCXGSIIUjTRVFuUH3FZvVfcIR\n" +
                "xA2Svi2IpKIqSw6myZBmhx/ddyTxgtVmy4lSJEognOXkYMpm11I3hqJu6Izn6nrNzz54zM3NLfHD\n" +
                "E5qmYT6fo3VIjOLI4UxH1/V4r5CBN+4xWmOMYZJnSAHFruCbb76lqhuMsSNwl6UpXd9hTVCVKCVI\n" +
                "05QvvvgC5xxt14ULdw6tDUkcYa2h7w1RHLPdbmmaZiS04ijCOTtUtQE2r+oGY6EqqyCWGKrepmlY\n" +
                "rdY4H163586FgLqu6LpuSLktWmu22x1104AQbDYbOt3Tdh3dUGQaoynLgrIsKIodZTH8WZaDgMOj\n" +
                "+566rqmqiqoKeJYesDGj+7FgtUYHLt0bHp4d473DaM3t3R2677HGUBQlTdOQZcmgHdsjyA7da5qm\n" +
                "RVpjcCZwEM454kgShSYF2s6wKzvKqiIZNLtqkNG3bYO3jjQRPHp4OuT7grpqBpG2oOl6hPfgPNtd\n" +
                "y6uLG/q+p2t7rq9uyNMEZzRmL1IQgr5veXl+wWdfPCdOU3a7HRB8rhKSu/UOa6FratSgwZrNctar\n" +
                "28D2DRur73qcd6x3FRdX11jnqOqapm3Zbnc4a9FdS1UWlMWOqtxSllvqqmS7Lfnmm2d0fYuxlqZp\n" +
                "ub6+ZrFY4J3D9OH051nKdrMexXPO9nzwzkNyZcgShXOGXlu8s3hnqJoeGSm816RpTNc2CB/IPN1p\n" +
                "2rZHGqNxxuCMxRmLcIY0kkTSMZ+mRMKz225J03RUXARNUoU2gaF7eHrI22dzqqqirCqmWYqSIqSO\n" +
                "zuBxPH12xedfPguCZeMGnAucN2gTsB5nHZttw5dfPefp1y+wxoC3tHUfxADSkQ83std0WRt47ShS\n" +
                "VFWFHU6UNpq+17y6WvPNt+eAYLsr6HtNU9dICVq3NE1D0zR0bUvXtLR1zR8//ZJnL66CctE5qroJ\n" +
                "5Jj34N2weS14R6QkRVGGFNo5JpOU5cEspNnWspjm7PHApi7DJnSW+XzGen1HIMQ0vQ6CDBnkmIEr\n" +
                "3v8kcUQcSR6cndK1DXmej4XLvqKdTCfcrVbgPXka8+jBMUma0mtNliYBv9EGZy1lWfLq8gaHxDrP\n" +
                "erMhy4IQII4UXdfifFjE9brg9m6NR6L70HPS1A3WhNdOJjl1XZIkyQgwuqEtoK5rzJ5e1SG4n7+6\n" +
                "QhuL1uHUrNfr0HNigztdrVZBI9y29F1HsSv48MOPyPP5cGglZVmRZfuFDUyg2cMiUURV1TgbKAK8\n" +
                "xZieST4J2t7ZlLYNidHeTYEnjqJBZFhjrcEYTdN0SKNDDeKdHbjiniRTCCz7gjlLEnAuqLStCXy5\n" +
                "kFht6Loe6yzzRU6WRFijxwxMa43xktWmYDbNiJQjSwTbzWbIQizT2ZS+H/y6sxjb40zH6XKCwGH0\n" +
                "vb+XwuOsJomDO7BWD9dkwTniOKHclUE8N5BnZVmSpWng3pFstsWoOJ/Pcu7uVnRdcEGd1lze3HJ4\n" +
                "tKSqC9JYYE2LkIOkyTmEtyOnbp3HOIeU0LY1zjnu7jbk+RQlHM5qjNVU9Y40UyNQaYxBm5Z8kgQe\n" +
                "Z+CUstghzeCGwk9wAZM8Q+vA0llnx8CzPyHee/peM18csF6v8d5RlgFXcoOATQhomhajewSe2STl\n" +
                "8aMHo1JwjyV55zg9PaYsS6yxZGnM8dGSo6MD+q7l+PhwjBVBWyzeuJaQPgYWzxgzNAcFkV9dVSwP\n" +
                "ZmRZgtE9k0k2egOG95+dnXJzcz0mA3hPHEsWs3slf5oktF2H835MQPre8NXXz7i8uhv4nIaqroeT\n" +
                "F48BuywK0jjGWUeSxOx2BVXV0HVdoBLSlM16UIkqkOFL7yX23msmeUpTB1giTeOx38IPC2Ct5cuv\n" +
                "vuX6eo1QkrbrBgVeoFSl8ERKst3tsH3L6dGC5VSymMZEccDGbm6uQ13jNVkCwoUiM0sE7z55QJ46\n" +
                "lPIoJZhOQ05fNy1RFL/REhD6NSzPX55TluXQvdSHRRewnKccLSdI4VFYJmlEuSvGRptIwTRP2G4K\n" +
                "vHUsF3OW04Tj5QRvLUmkmGYJRVEM7QeWXhtenF/x9NkV60KPp2W92TGbZOA1kQJnNThPnqYIF+Sr\n" +
                "ZVmxK+qx5SMeKGetNVIY5J5/8M7jrcNbw2yaB5ZOCeaznM12O1SXoSpfb3b8yyef8+z8hiSK2a63\n" +
                "KBmBCNKaYG1FVdVstluSNGU6ndK1LXGkmE0SmmqH1ntCSJAMeBXeMZ/NAgqaRCjhiGIZVIY++Gjh\n" +
                "3Ws7tef51YqPP/uGXVGGwm74O7zn5ChA5gGrcuR5QK+tGTyCscF9eijKEqUER8dLEA7rNFkaOo2T\n" +
                "JGa33eK9pShLXl3dMEljJqlCeBfinZADxeJRUoQ6KZbjqVYqEG112+MHN6siFRCHAZ2WIUjZ+xPg\n" +
                "HZNJPmQShigKChI3cMbWOr7++hsm0wXaBKS06wPs4L1jsZhT1/UguxGsNxvqAajTuh/qDsPDh2fc\n" +
                "3NywVzFmaTa4rdBbEgA/OQZSP7hBKQLHonWPtaGR5+M//plsugg64tfcWa97pIC+a+/FFCJA8fua\n" +
                "pW3boPOVks12gzYabTSbzZo8z1AD0nB0eMhms8Z5R900TKZzrOl5+OCMvu+GBQ9ynyB1ElhnBi/g\n" +
                "xzgYJwl13Q6YwCC0i6KxV0UaY0M1PBD42JbZNMYaT9eF/riTo2Nurq7Hm23bHt/t+OCdB4HqVQEK\n" +
                "8NYxzTN016CEx+oeHFgddmwSxwgc1vaoKCghq6oePrdHCkunDWVVEccxURwFg7kewRAjXM98PqUo\n" +
                "KoyFq5s1kzSlrQoOFjOs0URKjGK93gQXMzKf3pOkkqapsL3G9JqubnC6R3easmjpO0tT93jrcNYG\n" +
                "PZU3nJ0e0fc9Eg+m5+/+5n1i1Y1FsxB+7FtJYxlqjKHg3ae+aaxQXg+MZMiasjynbXuM9ci99fZB\n" +
                "3XvHZDIZiZf9Tk3TJFSqQyA8PT1hebjAuSABuo9Bnul0Qts0Yx4fuPk+ysMpAAAgAElEQVTQzRTq\n" +
                "gx5nHVGcYLQZxHGWxWLBZrMZ2+HwDFL98N9egrOHOHTfYY0mjhVvPX6IihTG6pHx26e/QUmihwIu\n" +
                "9D86Z9kVBWVZBgrAGLq+pygKrq+v7/Vo/l4EkaYpkzxHKclsOmE+n1OV5dBzHhpK27YbFJ9+2Lzt\n" +
                "kCLbUf1+dHTE9c0tzgW6QA5EX5qmRNZatDVEg+7U4ZhO8tBG1oRq3EtNlkYUZYsUHSfHB6RZRN1s\n" +
                "SeKYyHvatsO5ZMBnBJNJRt+vcc4wnWZU9QYhPF0fAm5VVby63PDo4QPqqiPN9oBlGBCAEIOrYuyE\n" +
                "DSljuLEoknRdw2yaMU0FDx8c0fUdSkXgQ+FmjcbooQfcObQO7KG1BqkU1zd3pIkkSTO2RUnTtkgh\n" +
                "2e4KxHDqrbNYK+i15eLiCoFHeMvyYEJTBXBR5RHCi5Ep3XPle5Vm13VIAVWtubi84L33fzgaK8vg\n" +
                "5vYGZwyrlQ1B3engbqwzeOvI0xRPwPLbTo+ChDiOg0QmVhzMFxTbQEsmScjEQiwKx1ZFATH1OFTA\n" +
                "YujaDt2F3r7VZsM/f/gZN3cFvQkQw2a7Y5KnQafkCeJmZ8a0vNP92A+/z/aSJOHs+BirNbrX4252\n" +
                "PrSrWefojcbjx9T2blVRVeH0NnUbOPu+xxodEhMp0V0bNoJ1eGO5uFrz4Ud/puvD9AVtLNvdhmhQ\n" +
                "XuIt81lOXTUwUsBuPGF9r2najucvL/ni6UuyLIjxGGK3UiJsRmdDoN5j9NaGC1JKoU1wJ3tXtFf5\n" +
                "GaNZr9dM8hznAvc8m83Y7Xaj65JSYK1hOp0M0EZEWVWBG3c2jKWoNd88e0FZVgFsrGqUUuRZhvce\n" +
                "o/sAUeBH7Wyv7/u+92lvmiRs12uyNB3EZ4Gv77seT+Dl95Mguq7n5mbFs2cvgk55UFgmcTL2kc+m\n" +
                "k1AnDAhwgE9aqrrl9vYObS03N7fk08lwgsI950OX2L7ZyXs/iAPjQScGaZrz9OvnlIP7N0MwT7M0\n" +
                "aJ29dxgXdnYoRkMKnKcpSoghGwltZVKEANV3hqLYkY76KEukQEaKbpghIjxgDcp7pHekUUy5K9Hm\n" +
                "Pvg/OJohvGW9Kbi93ZCk00HuqYJWiUER4g1xJJGIUKxaPyr79hW2E6CER3pPLAV4S9c3KCECQqvD\n" +
                "PXZ9R5Iq7jYbnl/cUbct2lmMD5CQtxakQ0g3tmIKITg+mpNnkjhRQURtHJGQCOdxjjHDOzw85Pbm\n" +
                "Jpxi7+nNXvEPeaaYzzMW85yX55cYN/RN4hBi2MivS2z2lnbOMckzqroG1MA/vzlNwQ4V8sgRaMN0\n" +
                "MmG9Xg8itCGodfdiib2LcM6hooi//dufESlB0zTc3d2RZWl4bQghNEMv+V43FWBuSzekmXvt1Gq9\n" +
                "Yj6fs5eWIsICdV0X+l7iaEgwAgk1n03J84zV6i4E864b4Zy9KPDk+CRIZQd3t5hNOVwuyLNs5EZC\n" +
                "eHMDkjG06BGCf1XXOOdHugERBvwcHx1yfHQ4np7dbsdsOhukRB65X2hr7FAYhoJqNku4uLjgxas7\n" +
                "eu1oO42290YABsgidJu+urji8uqKNE3ZbHdhIQmCgL3RhFAURT2M6/CcHqU8fHCIMcG1hfaHkFpk\n" +
                "eUzdNKPKfg+3xEnMbrsb3WiAcfoBkvHjAoUkwCIJ7ijwPsFdTvOI06MDYiUByfmrCyZZet+z7lxw\n" +
                "f4QBA9YaBD0/+/F7A+ZnMSY0s+6/03nPy1dXrFYr5os5RdlgHcRRyvXNCo9EAMv5jMU04+RwSdfU\n" +
                "Q9//Hrj1SGsZ07E9seScYzbNqJuWjz75bATljLY465FCMJ/N2a43oRPIhT6Q//yrP2BdSl237FWB\n" +
                "/QC3B2ggzAFZbwqMgVhJ5rNpiDWTDCFCnPIuAHPTyWRsoRNC0BtNUdZY9lx+aLwJYul+NJISEueH\n" +
                "jM57okiiZKCbjQ7BdpJnnB0fIrynKqpBDD7UKvuGnkhSNzVNGxKWJEk4PV7i3b6uuW/Rdt5zdX3D\n" +
                "b37/CVXnmWRJoJ9x5JNBTS9D3+FyGRp4uk6HItztPdM+yxrcz/ind6EHzjsckucvXg7g3T3UMZlM\n" +
                "KIoiPGcdaZohZMIfPvp0vEgIsz76PlSrvQ41QlGUaBMEZ1rrUde7n9m1H83R9R2Hh4dBtDBQzF98\n" +
                "9SXJUNV7H6YkLOYLttvtG/SA96ExZo8yxHHEZrMN1+ZcoAgGaeh8NhtPm9/XHUPfhnOeumnH2mh5\n" +
                "MA+asOHaX5fDChlIvU8+/Sz0vfiQAOVZfi+F9T4UF27fkSvHeOO9Q+4zK2MMZniR845JFjOfZizn\n" +
                "GcWuwlrBZrNFSTU2zy8WiyHIWh4+OA0M2naHsZ6mDRVs0/UYF272+mbLp59dMJksuLy8Zlt01HXD\n" +
                "g9MDNtsSIYMIWxvHZtfyf/3fv0XboG5RUuCtwXrJi/PL0DIw7NAkVli9L8bcGAd0H1i4rjN0fRg5\n" +
                "tR6Q1X1BHMR1r0tiPd4JrPEY7XBOUDearutRSoVU1Xsmk4z1dofxg/7YW06Wc05PT3j+/Dm9sTgn\n" +
                "ub5asV6vOTs9ZbstSJKM1d2G2TwHYcYsLvwQgvo4TOW1wB4gY81iPuH45IS27Wg7jfPBlxtriYd0\n" +
                "rus6pJS8/fZjjg7DGI26romUHGKIo9eG81cXfPnlV3R9T1M39DqIIsKkh7C4AV/q+eSTP7G6W3F7\n" +
                "d0ccReP8kjzLaNtmLLz2gTVsjhJr3QCthxO4LyhfvDwPhN+wo5umIc1SFovF2M++b5kLqazh6uaO\n" +
                "7S7w7CBYrdZkWTq2HexnmOxd1ltvPcZZwzs/eCtMv3COuq558fIlUZyMxWCe50ixl+k2YVrFYBRp\n" +
                "jRtPyN4gRmtiHB88eYuDWcTN7Zrn55dcXG1Yb0qMDviXHVDUqiyx2vDo7IDT4ym6DwmCEI6uC833\n" +
                "Vd1TVkGKo/swNKbvTdBBScFsOgmLaHrKsma9qZlMpzjrwugoGfRd1moW84AG712d927QJAv6tgvh\n" +
                "0zuMCQ2e623Bl1+/5Ob2djzVxhriODCQgV7Yjw0MbWvb3ZYvnr5gtSkGWkFyc7sKmjAJ1him0ylV\n" +
                "VYWmJu+RCn78wTvMJzHCu9HAoUANMy3qpiFJgrtcHsxZrTZY43DDqL/IOo+xoI3E+5jbdcfzZ+fY\n" +
                "b0vezR9zd1tQ312w3RVE8SHbyxV/8zc/JpuHxn1tHV2nEbIjikMfn5QQDVODyqoJGZrWKAUnJwco\n" +
                "BT0udKriQErmsxl1VSFE4JgPlxPapuL4cMaryzvSNAmS/zQlVop9b4wfUl0hQq9903Yw7nSH9bBa\n" +
                "bQKP0nckcTjVSgoiKQHHyfGCtu0GYXjIGl+8OCdWcoTuvfeYvYsbOsuctUzzCWVZhXYLYHmwQOC5\n" +
                "utlijcGjeHB2jPc9zlvEUNyETRRT7qph3EZQ3kehgnV0fcHF+Te8uulY3ax4sJaQxNRtw3tvv8XL\n" +
                "V+e8OK9wWvPi2Tlvv/8QqQRFWSOlwrl6DPgypBODCzKsVmsmecbJ8THHR0usDX0cURzhXOj8TdOM\n" +
                "i4sL5ospkVI8eHBKHCUksRp22n11HrgGP1AC+4Doh0EzYVABQ93Ttl0oahCcnZ2FTC+OcdaEwTPS\n" +
                "cXJyxPpuFZ4f+gK3mx0Hh2ccLpejGt+ae4PsA3SkIipjMdoSReqeB4kijDUslktOTo8pih15no2z\n" +
                "WvZrNZvN2BU7DpfzICicn1vWdy3FakfSOf7UFJTa83K1xnlDkinOXvbcXWwpNLjScPfihqNHR/RG\n" +
                "8/GnT/nJj98niTRFUfLg7AF934QpN95hB2hG4TmYTZhkgbF78uQdhAxyyrCbO6azlKvLO+bzGUcH\n" +
                "U+aTKXerFXmeUlQtECbITSZTquIOv5/lOBhmP+HT2ntMq29bJIbDgwBN3NzckMYRRVvTdR3TSRLm\n" +
                "VcURdduMdcHR0ZI49hwfzdFdg8CFyaHeE6sAHvbG0/ctk8mEsiqIk8XQwDPh6rYY9oHD2aB/Pj1e\n" +
                "0g7jSmKvkEownwZauW40aRITffH0Ob3x5CqhbnZ0uw2z+Zwk8dxtCja7noqAsBLnaKeZLKZY5/j0\n" +
                "T3+m6xxt20CiBqjZEEUq7DbvsTrk2rd3N2FUnjYs5tMweUiEZp7Q+xE4GSUl2+2WxWKOdW7sL/fO\n" +
                "IoUYuep95R8wo1CkfvPsJYv5PKSswymKo4iHDx5QNyF4KqVIhsRgP74wTL5L2G63zOcznLWcnZ3i\n" +
                "PbRNM4zy8xwdHXJ3d4eQkrpp+PiTTzlYzPnJB+8AUFd14ODTdCDiAvl3e3vLo4dn43C2um5IszDN\n" +
                "1eUxWZaxXt8xn82QT1/tuLQl16LAngiezD0/O1S8/yDm7dMJi8Wc6XKJU5IoFizPDlj84JC6M2x2\n" +
                "FWmasJf0a93T9S0+TCGk6wM6q6RCxRFSxTRNz8HBFBUFitg5GxR9ZUPf9uRpMtwM3K3WLA4OsaYf\n" +
                "WqpBD58phBjHXDgk3z475+sXl1Rtj4ShId9gdM98mnO0nFEUWw4ODohjRZzG6AFKsTZU9EpKdrsC\n" +
                "3bekcUQk5TgK0FvD0TCczXnYlhW324pdo0eAdbMpmE4nQCg8I6lomw7hIZJDk6eK2BUVvQ4I9745\n" +
                "NZ9krDc75FWkIFqwaxVPryperBv++OySj87XXDcGJwU+irFCkpuaM+k5qmvMasNCxiymGd7oUSix\n" +
                "p0Uh0K1aa4y1REqCD+RQiDlgeo0zsFmXrDcF1gXMKonjwf+39H1HWzeDUE6grUEPEMqeCOq15cuv\n" +
                "nrKY5kRDvNl/X1U3OA911ZAkEWkWI6VkNpngTPgsPTCKWZrRt93Y6m1MQGKTSA2nPuH4aIHEIRHk\n" +
                "qeLwYPoG+hz+tEgFcaIQEpbLOXtpVOjEVex2BX7QMwBM80lIiJpI8fSbF1zd7bjZ1FytdxiZcFf3\n" +
                "XG92rDZrXl1cYqzjyaMz3jo+pLh4xfXLc1IpUQLquhyh+1FNOKSqe9xrPxojzwOAV1cVu+2Wly9e\n" +
                "UlUNddPRdv3oirpBydJ1HUrKYZBNOFHGmvsK1zmapmU+X+CdZTGfDSM7wmvXm02ooNuO6WyKEH6Y\n" +
                "VxWjpOD66oq6rofJPw1Shha2fQzKBvdjjAEBeZ4ynU6GIZmaH/zgrZGa8EMVHma5DGDhmJDsuX5L\n" +
                "lqbj9zjnkCK4spOTY6LixTe8Hy2Y4tnd1dwKxeZmTVO1RALSSPHD6YzFdMp6Z/hPl88p8Cxawyyf\n" +
                "oNs16YMjbOaZTCasVisODxZ4wpS6fX2z95/GGDbrEu9DoL+9q/nsq1f8m//yl2x3DXEcU7WhQ/at\n" +
                "x4+4uLgkn8yJnEApBhG3RogwidQaxyRVLKYJs9kCJT1eCaIo0LRt34+7cH8NSjp6q4kiRddpttuC\n" +
                "NI1w1jDJYpzTeB8KOakkUazG05IlMUkc4sNP3/sBCZqm7zk4OGC32+F9ACzH3vchRnk8Qnqk8mRR\n" +
                "RJIccHNzE6gD9t3GgqhuW66dA2sR1tGIHotnkcaoKGZ5fMyhSoiEQG23SBPEw9Z5mq6l95LYGqI+\n" +
                "4FRd11I3EUk2RYhQaDVti+2CZqrvW9p2wuHyAJVEnJ6dcX615aOPPuZnP/8pZRWE2vthNkqpkP/L\n" +
                "OIyAtXboMRxGufoQ1B8/foj3cjR+pELCsJ8OpJQkUnKEfZqmoe9DX6O1wT0eLObkWcp0MkVFEVoH\n" +
                "eU8URSOEX1Utm812MFYUCr3Xxufu9WBKymFq6zD+0AcWdTKZsN2VtK3l7MFDqmqL0YcILzAWol5J\n" +
                "ylnGdrtFW40VjsVsyj88XPJiV/Oq3OBdzOFsTt5Zpr3DC2i9xQuFyBNmkUJahwSWiwU+pP0h2DvP\n" +
                "Hz76hFgEZd5kknB9Kzg42DLNE6SKQRjqznK7Kthtd0wnGbPZBKQgThMuLlcsFodESuGspygqlrME\n" +
                "7zx10wSx2ySl60Orc5alg1IkQOVKhokQzllcZymLkrKoOL+4pus6fvrTn5LEQY2ZDEoXKcKAz/2U\n" +
                "0/1A/6Lu2JWhtXpXVmFYzTTHOkOWB+ohyyeoYayfrgN3E8cxaRbjkby6uOajj7/k7//+X7GYZGw3\n" +
                "OxYHU6z1RFnf0WnPFEcce9JkTiRjnq07XO94aByPjxRNc8ultWxNg0ozOu/R3jCXEtv1SAlymhFF\n" +
                "CSoKrdLL+Zyu6SjWt1jr0YO4YJJ6Xr4INw0iIMwWtus1UgkiFZPESai4haDpHFF0RVnWWDzb3ZaH\n" +
                "Z3ME0HUNRkryfILWHZNpPgyaEyNya60lzxKuLq7GEU5d1zHJM1abDZ9+9hk//uETjLZD2i6JIoH3\n" +
                "Ams0282OfJIghCSJBIvZhF254u72jnefvEPolHIoBZtNwWoV2gDxYW7K3WrLfD4nskEjILyn7Tp+\n" +
                "9+GH/P3f/oisjbDOhEFp0lqSVPGz99/jdJ5xfbHl1cUlr7YdB1JyohRC1yjXYpOMDktkDTKJmc5m\n" +
                "WGPZrje0W09+fMh6u2EyCeOVZrMppz//KW3bUbeGb5+/wCH4xc+fkGcp+3kjxhh0H2KDdqHq1Tr8\n" +
                "Cwt937PdBRQgTVPSNGMym1IUBbPplDxLWa83GBNEEln2KPj9KBogCh+M1gdAct/sr6Tk9OQE7QO+\n" +
                "dHV1zQ/eeYv1ZkMURaxWd1jrSKKYq6ri8OiAJIlJooijw0POr9ZEURzGIIoQX4zWYxxdHhyOBGbf\n" +
                "a8qqwTlJ1/XgHUmWYq3jq6+e8uMfvcd2IPWiHx89ZFJbbp6+5ObRMQfO0LYliYwp+o4uSZCNQJDQ\n" +
                "GY9XMdEkY5kkTNKMpm5ptOau7+iaG5z3VJevWC7m5GlBnoWMIh/EDt57Hh7PWMynCPaCAIfjHvHc\n" +
                "ixeMcbRtz28+bplPU375i5/QdzaMGe9rmrYOinijIY6JVUTXtGRpjCR0vF7dbvnVP38c6FsZWuaS\n" +
                "ZD1KdKSM2Gw2pGnKuuwwRr9hzD11nLxaDaCgYrcbWExn6NsKQ4uUYeqo9548Ubx4cT7AK5ZdpTn/\n" +
                "8FOODw8HOElzcnTKt99+yyQ74vK2RmvNowcnRMVqjZYJpe24/fopmQ0pXDcIy7zR7BqLMwYfZ5we\n" +
                "LoniCN2FiW1BmdJTNQ1VHaZQg2S1XiMH0C9EvPC/NMt4/vKKxWJKJMOIDSEkjMOEh0H9LkAO2u5n\n" +
                "dklUFFNva55+8y0fvP9uQEeVwLiAxgqp2G532GlOHEVMsoyq6ykKh+T+H1ZpG0c78BtKKaQQdG1N\n" +
                "14Y2NoZhN86Ff+1BSsbZ7HsSK0sUdjjBsbKhtvJhmulsNkNrTdM0SDyf//lPeO959eLF+Bl2GGfb\n" +
                "NBWXl68QOL763BHNjOBp1JLGgkMiXtXDMBThidKI2lrWNmI2WXKUgLeGuHf01tINE6S1NdjBEOND\n" +
                "yDCzbT8mY/iz7Qz/8qcXIf3ktZuUw79h8vqEo+GXugkCuT999jV1q3l1cYn1gkmWDjPXa6p6z494\n" +
                "yrrBOcfp8RI2NXmSsJwFdCDPc8pW8+XTb4jjlGkWhWJ1SE+DSE8GVWNjeHB6zPFBThonQRM8SJPi\n" +
                "KGJXFkh1P6xASMntpuHl5Zaz4wWPHpzgvCCSQYqUJAkeQVnVtMZzd7diNvn/CjuTHkmy7Dp/b7TB\n" +
                "zYcYMiszq7qrq0mCEElJEAQuCFAr/bH+e1pJECgRFAhCVayunGLwycY3aXEtokhpoVwGEsgMN3Oz\n" +
                "++455zs133//HqMCKSnsExOlKJqmw+uMWxI5RFAGqzX7pmHjRLWbEX4uOTJFqfNJFLRV7L3n1sov\n" +
                "J3f+S6OMej3FKrUiLtbSlXGZsVqjimgFaf1AX+DLL/aaSsMynPnxn55JRaFK4eef/vmV3ZjXD0Mr\n" +
                "82odermYS4LLtefSDygtolBMogRO00xYJKchX+A1GL3imVIuPD6f1+3C8srBd86hQ2RJlp/++Ei3\n" +
                "qdHrih7juI5n0oNYbr0xOGdJyrDtOlCaqt3w6cszKEXTGH77m3dYvRCXjH0cLuR1/3J8fsLoBmsM\n" +
                "VdOybWoqoyAJ9Dir1SEf07r4U/imom481mn8ipr1Vhzm3vtX3yvw2kkyhMj5fMG1b4RXqISbqJRe\n" +
                "H1eFgiKt8JiUf6X5hFReLTcvwlpYnSc5S+lXTmJAkwssd+91XedcBzHAlRUS8ALb/3/+KHlMXq79\n" +
                "2hZR/tW3l/XC/d80n7Syjqd55ny9ivV0PST+S2DykuRs4L2lbT1WQVAB9f72tuy1Ba3IFO7rlqau\n" +
                "SQ4cGhUSzyWSSiathypywThFu2nZ1lagNV5yidZarBMnnnUWs8qmWStSludzWjLH44l227Ld7jkd\n" +
                "L0yT3HmkTEgL3W4j7pdtR1zhAVXlsdpxuVyY55nDYb9GEn5tQwghrVq4WJCWIP/3OUViTBLb/per\n" +
                "jiKHxRcZNqZMyi+YcyUNB+Xl3SHvyPJi/1LqZSnCr6Do/3/x2b/8ubWapq6onUNZg93WDRXQbbco\n" +
                "DY0SRodVSjarc2Qu4fVFZ7TCeUtVW1m6GYV3hrp21LWn8h7n1RpEKVI5kTJjWJiXIAzbkri9u5Vz\n" +
                "xgtCaSWvaaXYbKT05Hg8SulJZdlstqQUOR3PGK05HPZrO1phGkVcurm9YRoXvn59RinY7bdCGfKO\n" +
                "dtcRFpFdL0P/ar7LKXO5XFFKUTcNMSXmOb667GPKzEugrODPREarF+S60IRieXG2v7h35Ocvj8/C\n" +
                "r46Wl0fyr+j1TFpGhnkgK1D/+d/8RSlKtpc5RsJ6Nau80IfImCGV9JrV8M7QVJZN7WiqirYyVLWl\n" +
                "aStc5V6l2xfd4jxGzpcry5Jf70pvpHknxCxzfMr4SlYTSusVzSp3j+gqYqiQndTqTFynQeccMSxU\n" +
                "a3wgJnh8PNO0bmXHW+racXd/4Ph0JMWErSzWWZx3XI5nKIVUCrvdFl95vn45EkKgbb2sOk4nrqdB\n" +
                "NHyVyQmMkXieNB1YCf6sj3FrLcMgkIKqqoj8ajAsZQ2CraphRsnjNyZCSVjjHecwQwyoGMjr83oI\n" +
                "C5FMMRpjCt5C7Rxt42lrT+0NlffUjUDKfCW8Q60VSRnmEHg+DRxPV5YlUNcbitKEuDDPgaaAzqCs\n" +
                "ZcmJFDQhFqwp9OPCHBZ8Zam8J2e5Y/X6ggwhoDH4ypH7kZIi1lhKURLMtxBSgpW9lYAvD0/kJAjD\n" +
                "mDJFJUIMoLT8v5VUPRUKd3eH9WwiG+ZNt+F67l+N3+M4st0deHN/IOe0chc1Tbuh23SM04gqhaY2\n" +
                "kinZdsSU+PLlK1VVsd/tuFyvhGVh0+xeuTIpJex16Jm1QqWETomSyqt2rLTBV56q0rRtw6YSzFDt\n" +
                "DN6JyuZqWWVba8WZVwrn65VPXx+IWZFTwTpJsdZ1JVaYRSAtcYnkEIglQxQHvnde+v/QjHMAbbBG\n" +
                "k1YKaUahjGWeArGIyOWMZZwXjHEs0wQaolphAsO4ahBynrj01/XbVNYPQspgXjCySv4FtDYUEozy\n" +
                "btq07evvuU0J5ysUhbapySnjKsc0T2u3iBSJLcuCs5qhv/Du3TtxSfZXvn75yNu3b7m9/ZZ/+B//\n" +
                "yMPDV3b7Tia8v/3hd+WaMrpkyrKQnbjOnYGqdrStZ1972rbBVxbnDMZq/CrTGiMXzljLgOXLwyOX\n" +
                "cSaHiLeOTSsj8x+fniAlDtsOY6QobI4BozS18+Bkc9oYi9IKXzkObQtKMa8bZqMVcwxopUTc0po5\n" +
                "RmyRcfVm20q7TopcFuEi6iVhrGWz27Ks2srpeqKrGzbeo9eb5TxNyNStsGZtltMKZzUqJ2KRkXlT\n" +
                "iQydsrzOtVYYVfC+prBKBKs7xXsnkJzVj2Wtpa5qUgg0bcXpdMJVDfM4cXo60bYt6q/fvy+LMTij\n" +
                "0CUTkDz1pvG0bUVVWTZeWIzWGbxfL8qa+SirZ2mYJr6crpwuF+ZY8NbS1o2c8IEhRdzqrZqD3EV1\n" +
                "u6H2Hqs147IwLwteGyov6PK8muGqtsVoLZtYJQdBbxwhZ7RzWKXor1cOXSs5C6N5vvSgoMaIM1Fr\n" +
                "wjLLKgSRG/abjsdn6YaaEeyUUppu09D3PVNYcEZTOfu6aq+sIaWC0sITTilyu99yvlwJSWLWRmsZ\n" +
                "uUuRPi2tVhW0rCEe0fqVVuv3XmO0nPtszgVtxJmuvWVfCdS+ayyVs7h1ivLe4tZviDVSvpVL4jQm\n" +
                "LsPIGCKVsey7LZd+pK481sBSZJLprKWqauZpIXslcYYQucwzbVNJ1sIYYirkFNk2nmE1ODhvRV8w\n" +
                "8uK32uCsx5TCuMxoXWhrR1Fwc3NDDAuHbYszhnkY12x7ouvuOJ1ONNsOvb5861p0l2GOLCFgneX0\n" +
                "fMRryM5ilMJZoertmmbN0sgktaSArzzTuEgNoK/YNJ6XfGM/jBjtiHEhlkhaJD7YdA05asFxqAVt\n" +
                "hTVcaYtVXlHVirbxbJqatnZUlccZqXJz1ogpwFmsNyhjKFpzXcTx/nwdAdn5DC/7L60Juaz1ozJ5\n" +
                "pFLQVkIwTsGmrQmpULIobJWz4AzeWuZ54rzafYwxjPOE8jUSbdEsIbPMUo58e9jAsnA4HGSDnCWX\n" +
                "mJLAbypbgXFoXTheJ3yzg2JJJZNjphRD2+4J6UjtHW1ds1vdjGEdT/u+5/ncy1Bh9CtjeJxmfFsz\n" +
                "R6FEqBUvBbI92O+2qKKwm4bL9SLxPiPTbNSKcRlBi8XXGk3KCbvd79hsLZumeZ2eQFwWRkvYRSob\n" +
                "fm2BeT6d+PHTs4xsReGdldDnqvTFLJ24yyJeI63UOmGJFn53e2AaRwqJaY164Q3zNNIddtTVFvY7\n" +
                "Uohst1uGaaQf5SLVdcM0L7S1p2lqDrsOm6Gpay6Xk+zQCvhmQ0Hx+PBMVddc+qtAc9qW4TJgrKLb\n" +
                "tZyPZ3b7A01dE+aZ4XphmCc+fPjANIvJbhwHilbMIZBCYb/f05iaupGKKGXkW1xigDUDH3NmmSa8\n" +
                "tuRRAkpuPTrEEKl2G+Yg6qw1hrgszNOE+Zu/+v4PN9uabeuprF5LUhLWygHQVb/iu8eieDpdmWOm\n" +
                "z4o5JTovor8yho01bDcNS07M00ztq9dUkpwVLHXjuFwHrsMIS+5PQI0AABESSURBVBR0RVPhnMBZ\n" +
                "qqYRw9hqGy058/Uy8HS+MOfMOPTs9h1/8t037DcN27pGW8f5emUIkcPtPdMS+eXjEynC88MXQogo\n" +
                "NL5xnC4nlkUyi/35zPPXK5fTwJI050vPdrcn5SSLv7qma1t0zmybGpUWkvHMITLOgevpTFM1ZJXR\n" +
                "1lKMEzdLToz9RFs33N4eWFIhF8V1mZlCRPmKm66jaxs2dU2Iics046zB/O2//7M/eGcgC9oVCsZa\n" +
                "+VC8R1tLLHA8X/j0+ERY1w9zTJg1D3i5XCRSXKR1Z0oRZ+zqNpdx1lUV8zJJ9amRaaOtavwK4HxZ\n" +
                "9fZ9T1wjciUXSobTMGGMvNCdEplYr1Kt0ZqHxyfGYeBwe0tOwuIKIXO9XNnUNdvdVgKnMciUE6Vp\n" +
                "c7/d4l2D0YaH5yfu7m9Y5ontfot3jsvpxGazkXdCKXTbLad+ZBhGtLFs2gYU3N0dmKeJaZzQSk7p\n" +
                "IUol3zJP9OMo2w/ncKu3+Pj0tNpKBay2xAQpYq0pUnSVpUvJOoPzTqYRpTiNE89D4Hy+EkphU1do\n" +
                "ZegqyKVw7AUo761Da8P5OjKrtLaPGdE5UByHAQPUGTSFEGeM85KuWtf3VV3hfMV1WrBVhcma4/kq\n" +
                "A0CW80HrHV1TYXImxQyVEkTSWoMRQ2AaFrS2hJC4DjMFhasd4zBjdUZbTa1q5iliMvR9vxK5F+5u\n" +
                "t2hjuZ7OdI30qTRNjbJWPMMly+bCKC6z1IbfhIWurqjX3qnT0K+7Qdlg36wdKFJGo7CqcAqeiARI\n" +
                "lVLUvqLEhNVKozxoZVd3oYyJlzFwPJ2YlsCSwTjpuTXasMwz45r53jU13aYT+PA0yeRRV9we9uQY\n" +
                "cE4wfXURt/x1kCHg7Zs3zNMiZE5juFl72i/DgLHy7RrGCaU0u8ZxnSb6eWbGch0Xap2ompqcFHe3\n" +
                "t3x9+MrxeGK33dE0nrbraFvDzz99RDmZGMNSKEUOtFOc6KeZTdMQjea7tzerlwpyCIzXKzfv36K0\n" +
                "YR4m+rEnl8IURIxKs6iLBtFQnHEMy8Dt4QA5c9+0DNPE4/MzoRSGeWaJsu8y2lAZIcDKrizSdh2V\n" +
                "15i/+csf/uC8w9Ue4wwoxZcvDzxfBuYQyUWutGxKJ1k3r0jZeZ5p166mEALGO/KL0KMgx/BqxYxB\n" +
                "IJgZKTYexoFhmjBKYgHeCOSl3mx4eHxkCQmdpTXBO8MSAtNKhVuWwG+/e4e1juPziW674ebmBpTh\n" +
                "fD6jlUIbZPXSbHnz9i2n0xlnLT/88HuOxxPWWn7zm99gncNYi3VQecewYgLf3N9TNzWPj4/kVKjb\n" +
                "hl8+fuQ0hVVikBf0/f09pIUYosAOYuTx8eG1Di/mgvNyhgmpoNCUDN++e4M1mmGKInwtC4SI+U9/\n" +
                "/ed/0FaRleEyBP7h50+cl0hZMs44vHEsa8hx01SYAuE6U2vDhzdvebxeQQkcf5peKiAUJSZcVhTv\n" +
                "OV977ruW282GBsXNfgM5MUd5twzzjDaWmAvny0Bdb6RvMExgJE4cloBeS+G3247nYebLl8d1LB6I\n" +
                "KXG724mrcMXsxWVhGS9sNhVv3h6onOLjx5/Y7Wru73bEOHI9fmXTGJS1DEPP27dvOdwcKBQej0em\n" +
                "JfF8HvhyHViUQeVCSQVnHTplvrl/wzUs/PLwyKINoSiezj1zzChtUKXglUK/SC4KtLcczwPnfsJk\n" +
                "ebKgNU4rrDKGJSycL1eeT9fX7LerKqzz0illKoyR5s1dt4OssEbz9Cx96rkUFGrdF0kC9no6kVdE\n" +
                "rFKK6zhS+YpUCsMghY2+qqWjJCb6a0/bNAgDS6CS9TplvQRDD/s9YV1nPzwfUSlxSomsEttuoRRB\n" +
                "ot/c3tJtd2ImuH8jj9Ilsu06Ict1W+ZpQqH5/Z/9GZfzGeUcu92Or49P8qK+vePL4zPTNBNDIa1Y\n" +
                "p23XoVAMw0C33XI8nzG1ZbMCe5Kx+LWwZRqEhaKyx1hL6yvQAnybJ0kZ2xzBOYrWbHyFPV9Hzv0o\n" +
                "O3/tqIyk9gNlrZYu1EZjtSIqw7gsKKvAaryvGMdpvYgKo+Q03U8jpvJkZ9G5sGkahrDw+Xym8x4b\n" +
                "FXW94fPlwjIvqFTQgguRxrdkSTGjc8IZS1gSdnV7TDHIEKEtMUn6K1nPU7/wNHxGl8IYHilF1t3v\n" +
                "375ZcR6FSsnK5jzOGGUxGObyzMPDg8jEGZa1bGxMiuOciBGMMtxuavH4rhiSyjmerhdQcFO2kAuH\n" +
                "bUeJhWuMlChUpaZ2HIdRGC+50FTCgI8p83g8403BOc8SZ5YxYENccLqQlDTZOKdXdiGvfMQwi52y\n" +
                "c0YmhaIwSEiytZ6s0uveKcSIU2CsZVmJceM0k1UhrokjVYKknnImk6UzSmXO00S7qclF0qkhFdCJ\n" +
                "fprEHV4K2jmOa4ucNyITt85LLj2J0/6SZ/I0S+iyGRlD4Pl6xa+SwamfMEqzaRqWJ9HUi9YMYSEZ\n" +
                "y5vthjj03NU1uSoM0yQXKyT6cSDHxM3hgNXSzkYu1L4iLoHny5WsFNlanvsebEdShjHMWKV5Pp6E\n" +
                "Y6kMXdeRhivey0bkOEzYbrtl7K/s9jvAyNg4TXKytFYM04n1cJUZp4WUxRajSiEHQZ9qpXDeS79T\n" +
                "CqAMJogjZVvXpCJeK2MtGUk21VWNM5Y4L4xr7mOaRxTQVPVKcTuCEYZKZZ1kzquKwnpxtWa36RjG\n" +
                "kWEayRS0s9zd3jOPI2M/EpSQ3a/TgnIVWUmMLgLzEpmniVx58I4lRPrzhcNOMLEJaWr79HSiqmri\n" +
                "2iI9jCPWyrg/jSM5CzFvQapgl3Gm8o45Bs79BAUOuw3GGpQ1XK5SU9ttO0JYaOoK1yVsWgp1XZOT\n" +
                "9KN/+PCBszozTQOXcaDb7oBFSAclsenEd7vMiZdaoyUE5iXIMwepiosx45QUtXg087Jgcqa2so53\n" +
                "JLz1lJIwjeNmJzmLEmdigoKUEVulaataoDZaYXKhQpFWG+p1GhkWIWPf3+7Fm3W+MlOoDoLPUMtC\n" +
                "XTL9lDhfrry9eUNZIchqbzlPomPoSc5Pf+wDpyTToqOwqys645mHiU3XoHImlkzqM5fliqkM2hpu\n" +
                "33xDVTf89ONP/ObuQFwC3lp2NxUhRR5WAlBZFiqlmK4Xqv2GZA2Pg/RZ2ZQSh8MNv/zyiWmc+PHH\n" +
                "H3l4eBADQRYO7Z/+6fe8e/+OL58+ybNuFo65sZbbmwPXfmCeF25v9sILoeCsVCLNi2TY40rjkeFg\n" +
                "YbvdorQ4UuZpZpxncQZWnjQJi95Zu7YgZJyXb5ZZI2H1WrmUkhUxKWfOxyPGOpZpYez7Vzd60zbS\n" +
                "FW8M4zhxPB0l754Tw7K8PgmaukGj2K4Tl0YRKVzHiWkOHA43JFUwNlBVNakE4jRxf3tLPwycjkfC\n" +
                "8iBb5tX1UlJiWqZXE0SKicqvw8oSOZ+vxAzDtMj6/eZ+zz/+r5/5+MsfOex3tF1L27ZM08IwTHhX\n" +
                "M8wLGcU8R87nnut5wlWWm9sDrqqo2w3KOL5+ehRLhtHUtUepgq1q9t2W4+MTm023UnoCxojWvYTE\n" +
                "brcnpyAIQRLdVv6e0KwdyypKeeeZ55nKgNKi3eeUKFkOZ3rjGacFW9Vk0mpsW4t/swwOjfeEnAlJ\n" +
                "xDGnFJttR8xSHzGnRBhGfOXZes8cAn0MYDXnFZYcYsR6z3mcKNbx+emZEiOHbcem9VDgvESO5xPt\n" +
                "ZoNK0A9XsoLGesZTz6KKbAeiImZpS91UHptSRNvMzd0OKCt8fqRpN9zf39LUNW1bE8LC4/NRYJlx\n" +
                "4qbaYazmeOmZhp64zGirGfpRmhDsDdoqGltDNmjtOZ8ksD9PE9//7neri+SRaexZpp7j8zP339wx\n" +
                "XgdShg8fvqFpGz59/BnjLNvNhnkKtE2HazwpJh6+PnB3dyuMxBLpOlm/hxRXyqqSiU0biipr44Ig\n" +
                "Z1VJkpOPgdpqlLFMU6Kt6tV4V6jWcuKxv2KtZQxyc+QQ2bWCnF0WqfO8jCOjFuxrQqhzl+vAYbvF\n" +
                "VxWNr4RxjES5q7rCKUNMkX0nFRZ2HAYOhy03NzK3ay2ludO8SGjeKm5vbuiHnk3XcTlfuLm94/5e\n" +
                "DmHnfiJGoWGnpFDK4OaEMY67N7d8/vjAp49fyDGxLLMQ27qOpm748uUL//RPP1Iw3O42eFfz/HQi\n" +
                "xsL5NHDtr/zVX/4Fb99+wzSOnI4nQOOc53DYczmvWvWK+la8GKg1bVOtDXKFz5++oLXh/s0dh/2W\n" +
                "/jIIm6tkliBJ37AIzW6aFvp+ebWlztOEsZ668rRNjXGr+6XApqloKk8fAsYaOeAZIzUcTgJPL6x7\n" +
                "BZgsFehpNaCP48SS5ZuSlsDT8Yj96ZcLaf0lpNJaKo1s59i2gvD7cpLWsnq7wdcVw7WX/VRd0VjF\n" +
                "54cn/nge+Xf/8S/QtvDttwdiSpxPz/RzT9ttsCjq6g7vHZf+ysPxkS+fnxmHiW+++YbDjdQYnS4T\n" +
                "IUxCHGoaqrWp5uvDR07HM3VdczpdWVLh3bu3PDx+4esvD7Rty/Hp/GoN6g4d5/Mj77/9lvfffQc5\n" +
                "UjdbTscjyzBjgP3NltPHJ5yvoFiOxx5XVXz3/QdJf4W4FgW8YJsU7SL9iKVobBGrj40KHTX3dYPR\n" +
                "mZmaOSh2rWTjxUFTce0nSirU2qKdJabEkiPD8xPaGpTR2P/23/8OwYkJQYfV52qMjHsvqVljNJVz\n" +
                "NFVN7SuaxvJ0HnDa0Gxv2N+84zrMaOP5+vC0tiTU3N3esiyBb9+/53I58+XLZ6Z5pmkaum3LN9/c\n" +
                "A4nj6Zmu6zidnqmqht//ye8wTvHjTz/y4f03GKtfwZpaa3KOXC4XQkwrU/0sYVAMRJjniRdIsVYw\n" +
                "h8Q4PgtAxliUNtJvoo3wFo2hHybOpwtaF8mftB1V7em6LcfTM23bcnvYM89S6+qcROcuVwFFK62h\n" +
                "rDu767QGYYVkNE/DOjysgVQgx0jtPcU5jNWEmLBj9qRXoz6YolbHHTKoI+tyGWkTIHevGJzBKkXl\n" +
                "PY33VF56DTeVovEVqVYUerx3fPr4M7kUSThpCGFGm8j7b29f/b9aG7b7LSElljAzXGbu7u6Yw0i3\n" +
                "71DOsozCnepaT1U5jhfWJd8d1+tIXVfM88R2u+Fw2OGM4cf//RNxSWuHlSJVFVXXcX/7lhSP/P3/\n" +
                "/HvCLC0HTVMRxsjUzzx+7nn7zT1NvaWtN+SYmKeFvh+EjbJp2e9b5umRVDJ39/css0ABmsYB8iE3\n" +
                "VcU4DuRiBH5ZQBWNdWWl2mWMVQQM6vfv35SMXsdVuRKxZAov3lfZUxWQC1cKar00KIVamVUKBD3x\n" +
                "Yoqwjtp5usbw5nZH11YYYzG64IxiDpHLccE3HmNExiyA9Q5tkNxHVFhjqRqLNUZo28uC0iukv+1Q\n" +
                "ynB6PvHp5weauuHDD+8pJVPXFWEeGObA8/OZtIpn22aD86LTaKXJGf7rf/k7UIq3b7a0Xc11GqiU\n" +
                "5ek4cjmPvHt/x7/9D3+OsZrPP3/keDwL0904docdRSUaX3HuJ+bhSl05hn5CW/kMu03LOC1UbcfT\n" +
                "wyMW6HYblDY8P11oak/beoxz2B/e364Oczk/5JxJaxQgpkxYWU5Cv1ErvqgQFf+qmBikTCtkiHOm\n" +
                "nxaUSphT4Z8fRrracnffsdt49rsWZxz7e7syqzMhSMNOHCPWKa5x4elxpNvuWOJAUzlSzDRtR1YR\n" +
                "bRRxDDTtlrqp+O0Pb9HGolhIOXI9XSXxpQyb/Y5cBPHx7vaeP/78M58+HylW8aff/5YPv/2O48MT\n" +
                "aYmkOdK5mi+fLlz6CWMdt28OjPOELw6jHX0/E3Ki8pZpGGn3e9xNw7IEjpeBdmr4/PGEcpa8BN59\n" +
                "EG+zD5pvP7zjejlzc3tDKHotMRMcyTSO/B/fvjXC68GdvQAAAABJRU5ErkJggg==\n";
        certain1.id=1;
        certain1.name="Кофе";
        certain1.parterColor="Коричневый";
        certain1.parterKind="Мешковина";
        certain1.tulleKind="Органаза";
        certain1.parterNumber=55;
        certain1.tulleNumber=32;
        certain1.complect=3;
        certainss.add(certain1);

        Certain certain2=new Certain();
        certain2.id=2;
        certain2.name="СалютСалют";
        certain2.parterColor="Серый";
        certain2.parterKind="Мешковина";
        certain2.tulleKind="";
        certain2.parterNumber=25;
        certain2.tulleNumber=0;
        certain2.complect=1;
        certainss.add(certain2);
    }

    class MyAdapter extends BaseAdapter
    {
        ArrayList<Certain> certains;
        Context context;

         MyAdapter(Context context, ArrayList<Certain>certains)
        {
            this.certains=certains;
            this.context=context;
        }


        @Override
        public int getCount() {
            return certains.size();
        }

        @Override
        public Object getItem(int position) {
            return certains.get(position);
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
                con=inflater.inflate(R.layout.certain_list_item, parent, false);
            }

            ImageView ImageView=(ImageView)con.findViewById(R.id.avatar_image_item);
            TextView NameTxt=(TextView)con.findViewById(R.id.name_txt_item);
            TextView ColorTxt=(TextView)con.findViewById(R.id.color_txt_item);
            TextView NumberTxt=(TextView)con.findViewById(R.id.number_txt_item);

            try
            {
                String stringImg=certains.get(position).parterAvatar;
                Bitmap bitmapImg=convertToBitmap(stringImg);
                ImageView.setImageBitmap(bitmapImg);
            }
            catch (Exception ex)
            {
                ImageView.setImageResource(R.drawable.ic_default_avatar);
            }

            NameTxt.setText(certains.get(position).name);
            if (certains.get(position).complect==3)
            {
                NumberTxt.setText("Кол-во: "+String.valueOf(certains.get(position).parterNumber)+" и "+String.valueOf(certains.get(position).tulleNumber)+" шт.");
                colorTxt=certains.get(position).parterColor;
                if (colorTxt.length()<=8)
                {
                    ColorTxt.setText(colorTxt);
                }
                else
                {
                    ColorTxt.setText(cutWord(colorTxt, 7)+"..");
                }
                String nameTxt=certains.get(position).name;
                if (nameTxt.length()<=14)
                {
                    NameTxt.setText(nameTxt);
                }
                else
                {
                    NameTxt.setText(cutWord(nameTxt, 12)+"...");
                }

            }
            else if (certains.get(position).complect==1)
            {
                NumberTxt.setText("Кол-во: "+String.valueOf(certains.get(position).parterNumber)+" шт.");
                colorTxt=certains.get(position).parterColor;
                if (colorTxt.length()<=8)
                {
                    ColorTxt.setText(colorTxt);
                }
                else
                {
                    ColorTxt.setText(cutWord(colorTxt, 7)+"..");
                }
                String nameTxt=certains.get(position).name+"(Портьер)";
                if (certains.get(position).name.length()<=9)
                {
                    NameTxt.setText(nameTxt);
                }
                else
                {
                    NameTxt.setText(cutWord(nameTxt, 8)+".. (Порт)");
                }
            }
            else if (certains.get(position).complect==2)
            {
                NumberTxt.setText("Кол-во: "+String.valueOf(certains.get(position).tulleNumber)+" шт.");
                colorTxt=certains.get(position).parterColor;
                if (colorTxt.length()<=8)
                {
                    ColorTxt.setText(colorTxt);
                }
                else
                {
                    ColorTxt.setText(cutWord(colorTxt, 7)+"..");
                }
                String nameTxt=certains.get(position).name+"(Тюль)";
                if (certains.get(position).name.length()<=9)
                {
                    NameTxt.setText(nameTxt+"(Тюль)");
                }
                else
                {
                    NameTxt.setText(cutWord(nameTxt, 9)+".. (Тюль)");
                }
            }



            return con;
        }

        public Bitmap convertToBitmap(String base64String) {
            byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
            Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return bitmapResult;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==10)
        {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,1,"Редактировать");
        menu.add(1,2,1,"Удалить");
        menu.add(1,3,1,"Назначить");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id=item.getItemId();
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if (id==1)
        {
                    Intent intent=new Intent(MainActivity.this, CertainChangeActivity.class);
                    intent.putExtra("id", certainss.get(info.position).id);
                    startActivityForResult(intent, 10);
        }
        if (id==2)
        {
                    certainss.remove(certainss.get(info.position).id);
                    adapter.notifyDataSetChanged();
        }
        return true;
    }

    public String cutWord(String wordForCut, int symbolNumber)
    {
        String st=wordForCut.substring(0, symbolNumber);
        return st;
    }
}
