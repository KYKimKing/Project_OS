package beehive.josh.exms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    DbOpenHelper instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        instance = DbOpenHelper.getInstance();

        ArrayList<Data> dataArrayList = instance.dataArrayList;

        ListDataAdapter listDataAdapter = new ListDataAdapter(dataArrayList, getApplicationContext());
        ListView listData = (ListView) findViewById(R.id.list_data);
        listData.setAdapter(listDataAdapter);

        listDataAdapter.notifyDataSetChanged();
    }
}
