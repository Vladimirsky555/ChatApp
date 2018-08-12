package ru.rubiconepro.chatapp.Communicator;

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
            socket.connect(new InetSocketAddress("192.168.30.91", 60000));
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

        while (true) {
            InputStreamReader inputReader = new InputStreamReader(istr);
            BufferedReader sReader = new BufferedReader(inputReader);

            String str = "";
            try {
                str = sReader.readLine();
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
        //TODO отправить строки в наше приложение
        //TODO создать интерфейс взаимодействия с приложением
    }
}
