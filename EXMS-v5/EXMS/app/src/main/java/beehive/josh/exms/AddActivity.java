package beehive.josh.exms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AddActivity extends AppCompatActivity {
    DbOpenHelper instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        instance = DbOpenHelper.getInstance();
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentIntegrator.REQUEST_CODE) {

            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            final LinearLayout layout = (LinearLayout) View.inflate(AddActivity.this, R.layout.dialog_add, null);
            final EditText et = (EditText) layout.findViewById(R.id.et_code);
            et.setText(result.getContents());
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("상품 정보 입력");
            dialog.setView(layout);
            dialog.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText etCode = (EditText) layout.findViewById(R.id.et_code);
                    EditText etName = (EditText) layout.findViewById(R.id.et_name);
                    EditText etExp = (EditText) layout.findViewById(R.id.et_exp);
                    String code = etCode.getText().toString();
                    String name = etName.getText().toString();
                    String exp = etExp.getText().toString();
                    Data data = new Data(code, name, exp);

                    Gson gson = new Gson();
                    String msg = gson.toJson(data);

                    DbOpenHelper.getInstance().getSocket().emit("InsertData", msg);
                    DbOpenHelper.getInstance().dataArrayList.add(data);

                    finish();
                }
            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).show();
        }
    }
}
