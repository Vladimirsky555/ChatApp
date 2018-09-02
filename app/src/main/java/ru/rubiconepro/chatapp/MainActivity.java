package ru.rubiconepro.chatapp;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

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

    }
}
