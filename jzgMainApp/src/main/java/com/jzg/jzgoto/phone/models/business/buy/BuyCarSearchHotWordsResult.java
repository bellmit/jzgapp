package com.jzg.jzgoto.phone.models.business.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.ArrayList;

/**
 * Created by WY on 2016/9/26.
 * Function :
 */
public class BuyCarSearchHotWordsResult extends BaseResultModels{
    private static final long serialVersionUID = 1L;

    private ArrayList<HotWordsModel> SearchHistoryList = new ArrayList<>();
    @Override
    public void setResult(Object obj) {
        System.out.println(obj.toString());
        Gson gson = new Gson();
        BuyCarSearchHotWordsResult result = gson.fromJson(obj.toString(), BuyCarSearchHotWordsResult.class);
        setStatus(result.getStatus());
        setMsg(result.getMsg());
        if(result.getStatus()== Constant.SUCCESS){
            SearchHistoryList.addAll(result.getSearchHistoryList());
        }
    }

    @Override
    public String toResultString() {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<HotWordsModel> getSearchHistoryList() {
        return SearchHistoryList;
    }

    public void setSearchHistoryList(ArrayList<HotWordsModel> searchHistoryList) {
        SearchHistoryList = searchHistoryList;
    }

    @Override
    public String toString() {
        return "BuyCarSearchHotWordsResult{" +
                "SearchHistoryList=" + SearchHistoryList +
                '}';
    }

    public class HotWordsModel{
        private String Name;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        @Override
        public String toString() {
            return "HotWordsModel{" +
                    "Name='" + Name + '\'' +
                    '}';
        }
    }
}
