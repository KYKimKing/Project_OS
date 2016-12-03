package beehive.josh.exms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListDataAdapter extends BaseAdapter {
    ArrayList<Data> dataArrayList;
    Context context;
    LayoutInflater layoutInflater;

    public ListDataAdapter(ArrayList<Data> dataArrayList, Context context) {
        this.dataArrayList = dataArrayList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Data data = dataArrayList.get(position);

        int res = R.layout.item_data;
        convertView = layoutInflater.inflate(res, parent, false);

        TextView txtCode = (TextView) convertView.findViewById(R.id.txt_code);
        txtCode.setText(data.getCode());

        TextView txtName = (TextView) convertView.findViewById(R.id.txt_name);
        txtName.setText(data.getName());

        TextView txtExp = (TextView) convertView.findViewById(R.id.txt_exp);
        txtExp.setText(data.getExp());

        return convertView;
    }
}
