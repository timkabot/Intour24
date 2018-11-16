package com.innopolis.intour24.model;

import com.innopolis.intour24.model.entity.Booking;
import com.innopolis.intour24.model.entity.BookingResponse;
import com.innopolis.intour24.model.entity.Group;
import com.innopolis.intour24.model.entity.Sight;

import java.util.ArrayList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Timkabor on 5/24/2017.
 */

public interface APIService {
    /**
     * Список всех достопримечательностей
     * @return
     */
    @GET("/api/sights/")
    Observable<List<Sight>> getSights();

    /**
     * Достопримечательность по id
     * @param id
     * @return
     */
    @GET("/api/sight/{id}")
    Observable<Sight> getSightInfo(@Path("id") int id);

    /**
     * Экскурсия по id
     * @param id
     * @return
     */
    @GET("/api/group/{id}")
    Observable<Group> getGroupById(@Path("id") int id);

    /**
     * Проверяет, есть ли телефонный номер в базе данных
     * Возвращает статус для данного номера
     * "FREE" - номер свободен, можно использовать
     * @param phone
     * @return
     */
    @GET("/api/checkPhone/{phone}")
    Observable<LoginResponse> checkPhone(@Path("phone") String phone);

    /**
     * Регистрация нового пользователя. Если пользователя с таким номером не существует, регистрация успешна
     * Если пользователь есть в базе данных возвращает статус ошибки
     * @param name
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("/api/registration")
    Observable<RegisterResponse> registration(@Field("name") String name, @Field("phone") String phone);

    /**
     * Получить все эксурсии на определенную достопримечательность и дату
     * @param date
     * @param sightId
     * @return
     */
    @GET("/api/groups/{date}/sight/{sightId}")
    Observable<ArrayList<Group>> getGroupsByDate(@Path("date") String date, @Path("sightId") int sightId);

    /**
     * Получить список бронирований по айди туриста
     * @return
     */
    @GET("/api/bookingsByTouristId/{userId}")
    Observable<ArrayList<Booking>> getBookingsById(@Path("userId") int usedId);

    /**
     * Регистрация нового бронирования
     * @return
     */
    @FormUrlEncoded
    @POST("/api/bookings/")
    Observable<BookingResponse> booking(@Field("groupId") int groupId, @Field("touristId") int touristId,
                                        @Field("adults") int adults, @Field("children") int children,
                                        @Field("enfants") int enfants, @Field("totalPrice") int totalPrice,
                                        @Field("createDatetime") String createDatetime);
    /**
     * Регистрация нового платежа
     * @return
     */
    @FormUrlEncoded
    @POST("/api/payments/")
    Observable<PaymentResponse> addPayment(@Field("bookingId") int bookingId,
                                           @Field("paymentTime") String paymentTime,
                                           @Field("identifier") String paymentIdentifier
                                           );
}