package com.kson.ksonmvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kson.ksonmvp.R;
import com.kson.ksonmvp.entity.product.ProductPojo;

import java.util.List;

import butterknife.BindView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductVH> {


    private Context context;
    private List<ProductPojo.Product> list;

    public ProductAdapter(Context ctx, List<ProductPojo.Product> list) {

        context = ctx;
        this.list = list;

    }

    /**
     * 创建viewholder实例，
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_layout, viewGroup, false);
        ProductVH productVH = new ProductVH(view);
        return productVH;
    }

    /**
     * 绑定viewholder
     *
     * @param productVH
     */
    @Override
    public void onBindViewHolder(@NonNull final ProductVH productVH, int postion) {


        final ProductPojo.Product product = list.get(postion);
        productVH.title.setText(product.title);
        String images = product.images;//http://.jpg|http://
        String[] imgAttr = images.split("\\|");//转义字符
        if (imgAttr != null && imgAttr.length > 0) {//有图片
            //with 绑定组件的生命周期，load加载资源，into给控件显示
            Glide.with(context).load(imgAttr[0]).into(productVH.iv);
        } else {//没有图片

            productVH.iv.setImageResource(R.mipmap.ic_launcher);
        }

        //点击事件
        productVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.onItemClickListener(productVH.getLayoutPosition(), productVH.itemView);
                }
            }
        });

        //长按事件
        productVH.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (itemListener != null) {
                    itemListener.onItemLongClickListener(productVH.getLayoutPosition(), productVH.itemView);
                }
                return false;
            }
        });


    }


    /**
     * 数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ProductVH extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView title;

        public ProductVH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_productIcon);
            title = itemView.findViewById(R.id.tv_title);
        }
    }


    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener {
        void onItemClickListener(int pos, View view);

        void onItemLongClickListener(int pos, View view);
    }
}
