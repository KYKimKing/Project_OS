package beehive.josh.exms;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }


}
