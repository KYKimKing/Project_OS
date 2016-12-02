package beehive.josh.exms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    DbOpenHelper instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        instance = DbOpenHelper.getInstance();

       // ArrayList<Data> dataArrayList = instance.loadData();
        ArrayList<Data> dataArrayList = instance.dataArrayList;
        //Data data = new Data("01","참치","어제");
        //Data data2 = new Data("02","참2","어제2");

       // dataArrayList.add(data);
       // dataArrayList.add(data2);


        ListDataAdapter listDataAdapter = new ListDataAdapter(dataArrayList, getApplicationContext());
        ListView listData = (ListView) findViewById(R.id.list_data);
        listData.setAdapter(listDataAdapter);

        listDataAdapter.notifyDataSetChanged();

        // Data data = list.get(0);

        //String str = data.getCode() + "-" + data.getName() + "-" + data.getExp();
        // Toast.makeText(ListActivity.this, str, Toast.LENGTH_LONG).show();
    }
}
