package com.kson.ksonmvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kson.ksonmvp.R;
import com.kson.ksonmvp.adapter.ProductAdapter;
import com.kson.ksonmvp.api.ProductApi;
import com.kson.ksonmvp.entity.product.ProductPojo;
import com.kson.ksonmvp.net.OkhttpCallback;
import com.kson.ksonmvp.net.OkhttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductActivity extends AppCompatActivity {

    private int page = 1;
    @BindView(R.id.rv_product)
    RecyclerView mRecyclerView;
    @BindView(R.id.requestProductBtn)
    Button requestProductBtn;

    private ProductAdapter productAdapter;
    private List<ProductPojo.Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        productAdapter = new ProductAdapter(this, list);
        mRecyclerView.setAdapter(productAdapter);
        productAdapter.setItemListener(new ProductAdapter.ItemListener() {
            @Override
            public void onItemClickListener(int pos, View view) {
                Toast.makeText(ProductActivity.this,"pos:",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(int pos, View view) {
                Toast.makeText(ProductActivity.this,"pos:"+pos,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        ButterKnife.bind(this);//绑定当前实例

        //需要配置布局管理器才能运行，layoutmanager：三种：线性，网格，瀑布流布局管理器
        //ori方向1，垂直 0 水平
        //reverselayout fase正常显示，true倒置
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,1));


//        requestProductBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick(R.id.requestProductBtn)
    public void click(View view) {

        HashMap<String, String> params = new HashMap<>();
        params.put("keywords", "手机");
        params.put("page", page + "");
        OkhttpUtils.getInstance().doGet(ProductApi.PRODUCT_URL, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {

            }

            @Override
            public void success(String result) {

                parseResult(result);


            }
        });

    }

    /**
     * 解析商品列表数据
     *
     * @param result
     */
    private void parseResult(String result) {

        ProductPojo productPojo = new Gson().fromJson(result, ProductPojo.class);
        if (productPojo != null) {
//            if (productAdapter == null) {
                productAdapter = new ProductAdapter(this, productPojo.data);
                mRecyclerView.setAdapter(productAdapter);
//            } else {
//
//                productAdapter.notifyDataSetChanged();
//
//            }


        }


    }


}
