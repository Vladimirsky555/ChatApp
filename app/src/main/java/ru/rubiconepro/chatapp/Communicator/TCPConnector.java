package ru.rubiconepro.chatapp.Communicator;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import ru.rubiconepro.chatapp.Model.IUser;

public class TCPConnector extends AsyncTask<Void, byte[], Void> implements ICommunicator {
    InputStream istr;
    OutputStream ostr;

    @Override
    public void sendMessage(IUser user, String message) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                for (String s:strings
                     ) {
                    ostr.write(s.getBytes());
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
        socket.connect(InetAddress.getAllByName("192.168.71.2"));

        istr = socket.getInputStream();
        ostr = socket.getOutputStream();

        while (true) {
            byte[] input = new byte[10];
            istr.read(input);

            publishProgress(input);
        }

        return null;
    }
}
