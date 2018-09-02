package ru.rubiconepro.chatapp.Communicator;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import ru.rubiconepro.chatapp.Model.IUser;

public class TCPConnector extends AsyncTask<Void, String, Void> implements ICommunicator {
    InputStream istr;
    OutputStream ostr;

    Context context;

    public TCPConnector(Context context) {
        this.context = context;
    }

    @Override
    public void sendMessage(IUser user, String message) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                for (String s:strings
                     ) {
                    try {
                        ostr.write(s.getBytes());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                return null;
            }
        }.execute(message);
    }

    @Override
    public void subscribe(IResiver reciver) {
    }

    @Override
    public void unsubscribe(IResiver reciver) {
    }

    @Override
    public void stopConnector() {
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("192.168.30.85", 60000));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            istr = socket.getInputStream();
            ostr = socket.getOutputStream();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        InputStreamReader inputReader = new InputStreamReader(istr);

        while (true) {
            String str = "";
            try {
                char[] buffer = new char[1024];
                int readed = inputReader.read(buffer);

                str = String.valueOf(buffer, 0, readed);
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }

            publishProgress(str);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {

        for (String value : values) {
            Intent i = new Intent();
            i.setAction("ru.rubiconepro.chatapp.Communicator.resive");
            i.putExtra("message", value);
            context.sendBroadcast(i);
        }
    }
}
