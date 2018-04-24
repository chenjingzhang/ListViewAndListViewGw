package com.dxt2.listviewandgw;

import android.content.Context;
import android.provider.ContactsContract;

import com.dxt2.listviewandgw.bean.DataResult;
import com.dxt2.listviewandgw.bean.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class Parser {

    public static List<Product> getCateProductList(Context context) {
        //获取raw，通过文件六的方式，再通过引用的Gson解析
        InputStream is = context.getResources().openRawResource(R.raw.food_json);
        try {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            String json = new String(buffer, "utf-8");
            DataResult dataResult = new Gson().fromJson(json, DataResult.class);
            List<Product> products = new ArrayList<>();
            //通过遍历，为了更好的操作，把产品类型和产品类型下的商品进行合并，
            // 此处是为了adapter更好的去操作
            //把每一个商品实体里都标注商品类型和类型编号
            for (int i = 0; i < dataResult.getResults().size(); i++) {
                for (int j = 0; j < dataResult.getResults().get(i).getFoodList().size(); j++) {
                    DataResult.ResultsBean.FoodListBean food = dataResult.getResults().get(i).getFoodList().get(j);
                    Product item = new Product();
                    item.setID(food.getID());
                    item.setTitle(dataResult.getResults().get(i).getTitle());
                    item.setFoodName(food.getFoodName());
                    item.setFoodPrice(food.getFoodPrice());
                    item.setSalesCount(food.getSalesCount());
                    item.setImageUrl(food.getImageUrl());
                    item.setSeleteId(i);
                    products.add(item);
                }
            }
            return products;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
























