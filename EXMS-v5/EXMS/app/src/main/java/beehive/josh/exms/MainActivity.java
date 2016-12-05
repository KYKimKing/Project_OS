package beehive.josh.exms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAdd, btnList;
    private DbOpenHelper instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnList = (Button) findViewById(R.id.btn_list);
        instance = DbOpenHelper.getInstance();
        btnAdd.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_add) {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            startActivity(it);
        } else if (id == R.id.btn_list) {

            Intent it = new Intent(MainActivity.this, ListActivity.class);
            startActivity(it);
        }
    }
}
