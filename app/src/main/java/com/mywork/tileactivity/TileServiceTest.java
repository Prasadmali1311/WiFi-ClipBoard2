package com.mywork.tileactivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@RequiresApi(api = Build.VERSION_CODES.N)
public class TileServiceTest extends TileService {
    ClipboardManager clipboardManager;

    public static final String TAG = TileServiceTest.class.getSimpleName();
    private final int STATE_ON = 1;
    private final int STATE_OFF = 0;
    private int toggle = STATE_ON;
    MainActivity m = new MainActivity();
    String msg1= m.msg1;
    MainActivity.MyThread t1;





    @Override
    public void onTileAdded(){
        Log.d(TAG, "onTitleAdded");
    }

    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
        Log.d(TAG, "onStartListening: "+tile.getLabel());
        Toast.makeText(getApplicationContext(),"Listening",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick() {
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        Log.d(TAG,"onClick: " + Integer.toString((getQsTile().getState())));
        ClipData clipData = clipboardManager.getPrimaryClip();

        try {
            CharSequence item = clipData.getItemAt(0).getText();
            msg1 = m.prasad();
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
        //m.prasad();
        //t1.run();
        Icon icon;
        if (toggle == STATE_ON){
            toggle = STATE_OFF;
            icon = Icon.createWithResource(getApplicationContext(), R.drawable.mute);

            Toast.makeText(getApplicationContext(),"OFF",Toast.LENGTH_LONG).show();
        }else{
            toggle = STATE_ON;
            icon = Icon.createWithResource(getApplicationContext(), R.drawable.voice);
            Toast.makeText(getApplicationContext(),"ON",Toast.LENGTH_LONG).show();
        }

        getQsTile().setIcon(icon);
        getQsTile().updateTile();



    }
}
