package beehive.josh.exms;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class DbOpenHelper {

    private static final String SERVER_ADDRESS = "http://116.32.62.3:3000";
    private Context context;
    private Socket socket;
    public ArrayList<Data> dataArrayList = new ArrayList<Data>();
    private static DbOpenHelper instance;

    public static DbOpenHelper getInstance() {
        if (instance == null) {
            instance = new DbOpenHelper();
        }
        return instance;
    }

    public DbOpenHelper() {
        this.context = MyApplication.getContext();
        try {
            socket = IO.socket(SERVER_ADDRESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        socket.connect();

        socket.on("GetDataListRes", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String resMsg = args[0].toString();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Data>>() {
                }.getType();
                instance.dataArrayList = gson.fromJson(resMsg, type);
            }
        });

        socket.emit("GetDataList");
    }

    public Socket getSocket() {
        return socket;
    }
}

