package com.example.myapplication.mvp.model.api;



import android.widget.ScrollView;

import com.example.myapplication.mvp.model.entity.AddAdressEntity;
import com.example.myapplication.mvp.model.entity.AddressEntity;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.model.entity.DeteteEntity;
import com.example.myapplication.mvp.model.entity.FootEntity;
import com.example.myapplication.mvp.model.entity.GifiEntity;
import com.example.myapplication.mvp.model.entity.GoodsEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.example.myapplication.mvp.model.entity.RegisterEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.example.myapplication.mvp.model.entity.ShopCarEntity;
import com.example.myapplication.mvp.model.entity.TouXiangEntity;
import com.example.myapplication.mvp.model.entity.UpdateUserEntity;
import com.example.myapplication.mvp.model.entity.UserEntity;
import com.example.myapplication.mvp.model.entity.YouHuiEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by MVPArmsTemplate on 11/29/2019 14:20
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    String APP_DOMAIN = "http://169.254.190.240/yoho/";




    //首页菜单请求
    @GET("home_menu.php")
    Observable<MenuEntity> getmenulist();

    //banner
    @GET("home_banner.php")
    Observable<BannerEntity> getBannerList();


    //首页推荐接口
    @FormUrlEncoded
    @POST("home_recommend.php")
    Observable<RecommendEntity> postRecommendList(@Field("request")String request);

    //首页列表接口
    @FormUrlEncoded
    @POST("home_goods.php")
    Observable<GoodsListEntity> postGoodList(@Field("request")String request);

    @FormUrlEncoded
    @POST("category_goods.php")
    Observable<CategoryGoodsEntity> postCategoryGoodsList(@Field("request")String request);


    @FormUrlEncoded
    @POST("Brand_list.php")
    Observable<BrandListEntity> postBrandList(@Field("request")String request);


    @FormUrlEncoded
    @POST("shoes_list.php")
    Observable<ShoesEntity> postShoesList(@Field("request")String request);


    @FormUrlEncoded
    @POST("community.php")
    Observable<GifiEntity> postGifiList(@Field("request")String request);


    @FormUrlEncoded
    @POST("Login.php")
    Observable<LoginEntity> postLoginList(@Field("request")String request);

    @FormUrlEncoded
    @POST("Register.php")
    Observable<RegisterEntity> postRegisterList(@Field("request")String request);

    @FormUrlEncoded
    @POST("add_car.php")
    Observable<GoodsEntity> postCarList(@Field("request")String request);


    @FormUrlEncoded
    @POST("car_list.php")
    Observable<ShopCarEntity> postShopCarList(@Field("request")String request);

//    @FormUrlEncoded
//    @POST("upload_head.php")
//    Observable<TouXiangEntity> postTouXiangList(@Field("request")String request);

    @Multipart
    @POST("upload_head.php")
    Observable<TouXiangEntity>postHeadImg(@Part List<MultipartBody.Part> list);

    @FormUrlEncoded
    @POST("sel_user.php")
    Observable<UserEntity> postUserList(@Field("request")String request);



    //收货地址列表
    @FormUrlEncoded
    @POST("address_list.php")
    Observable<AddressEntity> postAddressData(@Field("request") String goods);

    //新增收货地址
    @FormUrlEncoded
    @POST("add_address.php")
    Observable<AddAdressEntity> postAddData(@Field("request") String goods);


    //删除地址
    @FormUrlEncoded
    @POST("del_address.php")
    Observable<DeteteEntity> postDeleteCarData(@Field("request") String goods);


    //修改user
    @FormUrlEncoded
    @POST("update_user.php")
    Observable<UpdateUserEntity> postUpdate(@Field("request") String goods);


    //足迹
    @FormUrlEncoded
    @POST("footprint.php")
    Observable<FootEntity> postFootList(@Field("request") String goods);


    //优惠券
    @FormUrlEncoded
    @POST("coupon_list.php")
    Observable<YouHuiEntity> postYouHuiList(@Field("request") String goods);

}
