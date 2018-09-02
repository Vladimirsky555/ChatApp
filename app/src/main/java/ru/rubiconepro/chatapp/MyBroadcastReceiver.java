package ru.rubiconepro.chatapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    IMessage delegate;

    public MyBroadcastReceiver(IMessage delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String str = intent.getStringExtra("message");
        MessageModel m = new MessageModel();
        m.user = "Удаленный";
        m.message = str;
        delegate.addMessage(m);

        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        Log.d(TAG, log);
        Toast.makeText(context, log, Toast.LENGTH_SHORT).show();
    }
}
