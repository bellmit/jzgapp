package com.jzg.jzgoto.phone.activity.choosecity;

import android.content.Context;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jzg on 2016/9/21.
 * Function :
 */
public class ChooseCityJsonObjectImpl {

    public static boolean isSuccess(Context context, String resu) {
        boolean isSuccess = false;
        try {
            JSONObject jsonObject = new JSONObject(resu);
            String status = jsonObject.getString("status");
            if ("100".equals(status)) {
                isSuccess = true;
            } else {
                ShowDialogTool.showCenterToast(context, jsonObject.getString("msg"));
                isSuccess = false;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }
    public static ChooseCity parserChooseCity(String result)
    {
        ChooseCity chooseCity = null;
        Gson gson = new Gson();
        chooseCity = (ChooseCity)gson.fromJson(result, ChooseCity.class);
        return chooseCity;
    }
}
