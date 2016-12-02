package beehive.josh.exms;

import android.app.Application;
import android.content.Context;

/**
 * Created by Josh on 2016-11-28.
 */
public class MyApplication extends Application {

    private static Context context;


    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this;
    }

    public static Context getContext()
    {
        return context;
    }


}
