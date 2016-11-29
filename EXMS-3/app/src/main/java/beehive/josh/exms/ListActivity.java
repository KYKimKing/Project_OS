package beehive.josh.exms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    DbOpenHelper instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        instance = DbOpenHelper.getInstance();

        ArrayList<Data> list = instance.loadData();
        Data data = list.get(0);

        String str = data.getCode() + "-" + data.getName() + "-" + data.getExp();
        Toast.makeText(ListActivity.this, str, Toast.LENGTH_LONG).show();
    }
}
