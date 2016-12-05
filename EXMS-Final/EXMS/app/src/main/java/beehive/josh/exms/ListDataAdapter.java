package beehive.josh.exms;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

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

        long nowTime = System.currentTimeMillis();

        boolean expCheck = false;  // 이부분 유통기한 비교값!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String[] str_date = data.exp.split("-");
        int exp_year = Integer.parseInt(str_date[0]);
        int exp_month = Integer.parseInt(str_date[1]);
        int exp_dayOfMonth = Integer.parseInt(str_date[2]);

        Calendar calendar = Calendar.getInstance();
        int today_year = calendar.get(Calendar.YEAR);
        int today_month = calendar.get(Calendar.MONTH) + 1;
        int today_day = calendar.get(Calendar.DAY_OF_MONTH);
        // 2016.12.05 ....... 2016.12.06
        if(today_year >= exp_year){
            if(today_month >= exp_month){
                if(today_day > exp_dayOfMonth){
                    expCheck = true;
                } else{
                    expCheck = false;
                }
            } else{
                expCheck = false;
            }
        } else{
            expCheck = false;
        }

        if(expCheck){
            txtExp.setTextColor(Color.RED);
        }
        else {
            txtExp.setTextColor(Color.GREEN);
        }

        return convertView;
    }
}
