package ru.rubiconepro.chatapp;

        import android.content.BroadcastReceiver;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.net.ConnectivityManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;

        import ru.rubiconepro.chatapp.Communicator.TCPConnector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edittext;
    Button   button;
    ListView listview;

    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = findViewById(R.id.editText);
        button   = findViewById(R.id.button);
        listview = findViewById(R.id.listView);


        adapter = new MainAdapter(this);
        listview.setAdapter(adapter);

        for (int i = 0; i < 10; i++) {
            MessageModel m = new MessageModel();
            m.user = "dasfdasf";
            m.message = "adsfasdfasdf";
            adapter.addMessage(m);
        }

        BroadcastReceiver br = new MyBroadcastReceiver(adapter);

        IntentFilter filter = new IntentFilter("ru.rubiconepro.chatapp.Communicator.resive");
        this.registerReceiver(br, filter);

        button.setOnClickListener(this);

        TCPConnector connector = new TCPConnector(this);
        connector.execute();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i.setAction("ru.rubiconepro.chatapp.Communicator.resive");
        sendBroadcast(i);
    }
}
