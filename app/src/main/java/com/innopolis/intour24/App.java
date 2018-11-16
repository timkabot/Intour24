package com.innopolis.intour24;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.Toast;

import com.innopolis.intour24.model.APIService;
import com.innopolis.intour24.model.entity.Months;
import com.innopolis.intour24.model.entity.User;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sergey Pinkevich on 01.06.2017.
 */

public class App extends Application {

    private static Retrofit mRetrofit;
    private static APIService sService;
    public static final String BASE_URL = "http://188.130.155.89/";
    private static Resources resources;
    private static ColorDrawable placeholder ;

    @Override
    public void onCreate() {
        super.onCreate();
        User.getInstance();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        resources = this.getResources();
        sService = mRetrofit.create(APIService.class);
        placeholder = new ColorDrawable(resources.getColor(R.color.white));
    }
    /**
     * Загрузка изображений с помощью Picasso
     * @param url ссылка на изображение
     * @param imageView компонент, в который будет загружено изображение
     */
    public static void loadImageFromURL(String url, ImageView imageView, Context context) {
           System.out.println(url + " my url");
        Picasso.with(context).
                load(url)
                .fit()
                .placeholder(placeholder)
                .error(placeholder)
                 .into(imageView);
    }

    public static int getDrawableIdByName(String name,Context context) {
        name = name.split("[.]")[0];
        int picId = context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
        return picId;
    }

    public static String makeTextFromDate(String d,long duration) {
        String[] res = d.split("-| ");
        int month = Integer.valueOf(res[1]);
        String day = res[2];
        String startTime = res[3].substring(0,5);
        return day + " of " + Months.getMonth(month) + ", " + startTime + ", " + String.valueOf(duration/60) + "h";
    }

    public static void setDrawableByName(String name, ImageView container, Context context) {
        name = name.split("\\.")[0];//Расширение убираем
        final int resourceId = resources.getIdentifier(name, "drawable", context.getPackageName());
        if(resourceId!=0) container.setImageDrawable(resources.getDrawable(resourceId));
    }
    public static boolean checkNumberFormat(String phone,String wrongText,Context context) {
        if (phone == null) {
            wrongPhone(context,wrongText);
            return false;
        }
        if(phone.length() == 12){
            if(phone.charAt(0) == '+' && (phone.charAt(1) == '7' || phone.charAt(1) =='8')) {
                wrongPhone(context,wrongText);
                return false;
            }
        }
        if (phone.length() == 11 ) {
            if(phone.charAt(0)!='8' && phone.charAt(0)!='7') {
                wrongPhone(context,wrongText);
                return false;
            }
        }
        return true;
    }
    public static String transformPhone(String phone)
    {
        switch (phone.length()) {
            case 12 :
                return '7' + phone.substring(2);
            case 11 :
                return '7' + phone.substring(1);
        }
        return phone;
    }
    public static void wrongPhone(Context context,String wrongText) {
        Toast.makeText(context, wrongText, Toast.LENGTH_LONG).show();
    }
    public static APIService getAPI() {
        return sService;
    }

    public static Retrofit getmRetrofit() {
        return mRetrofit;
    }

    public void setmRetrofit(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    public static APIService getsService() {
        return sService;
    }

    public static void setsService(APIService sService) {
        App.sService = sService;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setResources(Resources resources) {
        App.resources = resources;
    }
}
