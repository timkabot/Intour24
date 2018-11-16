package com.innopolis.intour24.view.Impl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.innopolis.intour24.App;
import com.innopolis.intour24.model.Constants;
import com.innopolis.intour24.model.MerchantParams;
import com.innopolis.intour24.model.PaymentResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.tinkoff.acquiring.sdk.AcquiringSdk;
import ru.tinkoff.acquiring.sdk.Money;
import ru.tinkoff.acquiring.sdk.OnPaymentListener;
import ru.tinkoff.acquiring.sdk.PayFormActivity;

public abstract class TinkoffActivity extends AppCompatActivity implements OnPaymentListener {

    private static final String TERMINAL_KEY = "1496395016617DEMO";
    public static final int REQUEST_CODE_PAYMENT = 2;
    private static final int REQUEST_CODE_PAY = 1;

    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initPayment(int money,int bookingId)
    {
        bookId = bookingId;
        AcquiringSdk.setDebug(false);
        PayFormActivity.dispatchResult(1,null,this);
        PayFormActivity
                .init(TERMINAL_KEY, MerchantParams.PASSWORD, MerchantParams.PUBLIC_KEY) // данные продавца
                .prepare(
                        String.valueOf(bookingId),                  // ID заказа в вашей системе
                        Money.ofRubles(money),                       // сумма для оплаты
                        "You want to buy smth?",         // название платежа, видимое пользователю
                        "need a lot of money",         // описание платежа, видимое пользователю
                        null,                  // ID карточки
                        "batman@gotham.co",         // E-mail клиента для отправки уведомления об оплате
                        false,                      // флаг определяющий является ли платеж рекуррентным [1]
                        false                        // флаг использования безопасной клавиатуры [2]
                )
                .setCustomerKey("user-key")
                .startActivityForResult(this, 1);
    }
    @Override
    public void onSuccess(long paymentId) {
        String date = (String) android.text.format.DateFormat.format("yyyy-MM-dd kk:mm:ss", new java.util.Date());
        Observable<PaymentResponse> addPayment = App.getAPI().addPayment(bookId, date,String.valueOf(paymentId));
        addPayment.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(a -> onNext());
    }
    public void onNext()
    {
        Toast.makeText(getApplicationContext(),"Succesfull pay",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(Constants.SCREEN_INTENT,Constants.EXCURSIONS_SCREEN);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAY) {
            PayFormActivity.dispatchResult(resultCode, data, this);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onCancelled() {
        System.out.println("неудачник");
        Toast.makeText(this, "Cancelled, try later please", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onError(Exception e) {
        Toast.makeText(this, "Bad try", Toast.LENGTH_SHORT).show();
        Log.e("SAMPLE", e.getMessage(), e);
    }
}