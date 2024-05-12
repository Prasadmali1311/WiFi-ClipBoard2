package com.mywork.tileactivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText edTxt;
    MyThread myThread;
    MyThread thread2;
    public String msg1="p1";
    ClipboardManager clipboardManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        edTxt = findViewById(R.id.edTxt);
        myThread = new MyThread();
        thread2 = new MyThread();
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);


    }
    public class MyThread implements Runnable {
        private volatile String msg = "";
        Socket socket;


        @Override
        public void run(){

//            ClipboardManager clipboardManager;
//            clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//            ClipData clipData = clipboardManager.getPrimaryClip();
//            ClipData.Item item = clipData.getItemAt(0);
//            msg1 = item.getText().toString();
//            //msg1= name;
//            edTxt.setText(msg1);

            //prasad();
            edTxt.setText("Processing...");
            ClipboardManager clipboardManager;
            clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = clipboardManager.getPrimaryClip();
            ClipData.Item item = clipData.getItemAt(0);
            msg1 = item.getText().toString();
            //msg1= name;

            try {
                //String msg = name;
                ServerSocket ss = new ServerSocket(1311);
                //dos = new DataOutputStream(socket.getOutputStream());
                Socket s = ss.accept();
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(msg1);
                dos.close();
                dos.flush();
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            edTxt.setText("Done");
            //prasad();
        }

        public void run3() {

            ClipboardManager clipboardManager;
            clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = clipboardManager.getPrimaryClip();
            ClipData.Item item = clipData.getItemAt(0);
            msg1 = item.getText().toString();
            //prasad();

        }


        public void run2(){

            edTxt.setText("");

            prasad();
        }
        public void sendMsgParam(String msg){
            this.msg = msg;
            run3();
        }
        public void receiveMsgParam(){
            run2();
        }
    }


    public String prasad(){

        ClipData clipData = clipboardManager.getPrimaryClip();
        ClipData.Item item = clipData.getItemAt(0);
        msg1 = item.getText().toString();
//            try {
//                edTxt.setText("Processing...");
//                //String msg = name;
//                ServerSocket ss = new ServerSocket(1311);
//                //dos = new DataOutputStream(socket.getOutputStream());
//                Socket s = ss.accept();
//                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//                dos.writeUTF(msg1);
//                dos.close();
//                dos.flush();
//                ss.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        edTxt.setText("Done");

        return msg1;
    }



    public void btnclickSnd(View v){



        myThread.run();
        //prasad();
        //thread2.run3();
    }
}