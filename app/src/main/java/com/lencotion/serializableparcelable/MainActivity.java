package com.lencotion.serializableparcelable;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        saveAsSerializable(new User(0, "lisheny"));
//        saveParce(new Human("CN", false, new Woman(18, "Nacy")));

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readSerializable();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadParce();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 序列化保存 Serializable 数据
     *
     * @param user
     */
    private void saveAsSerializable(User user) {
        Log.d("saveAsSerializable", "User:" + user.getUserName() + " " + user.getUserId());
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = getApplicationContext().openFileOutput("cache1.txt",
                    Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.close();
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 反序列化读取 Serializable 数据
     *
     * @return
     */
    private User readSerializable() {
        User obj = null;
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = getApplicationContext().openFileInput("cache1.txt");
            ois = new ObjectInputStream(fis);
            obj = (User) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (obj != null) {
            Log.d("readSerializable", "User:" + obj.getUserName() + " " + obj.getUserId());
            return obj;
        } else {
            return null;
        }

    }

    /**
     * 序列化保存 Parcalable 数据
     *
     * @return
     */
    private void saveParce(Human human) {
        Log.d("loadParce", "Human:" + human.getAddress() + " " + human.getWoman().getName());
        FileOutputStream fos;
        try {
            fos = getApplicationContext().openFileOutput("cache2.txt",
                    Context.MODE_PRIVATE);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            Parcel parcel = Parcel.obtain();
            parcel.writeParcelable(human, 0);

            bos.write(parcel.marshall());
            bos.flush();
            bos.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化读取 Parcalable 数据
     *
     * @return
     */
    private void loadParce() {
        FileInputStream fis;
        try {
            fis = getApplicationContext().openFileInput("cache2.txt");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);

            Human data = parcel.readParcelable(Human.class.getClassLoader());
            Log.d("loadParce", "Human:" + data.getAddress() + " " + data.getWoman().getName()+"isMan: "+ data.isMan());
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
