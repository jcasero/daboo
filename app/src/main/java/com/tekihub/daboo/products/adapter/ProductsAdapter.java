package com.tekihub.daboo.products.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tekihub.daboo.R;
import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
  private List<ProductItem> productItems = new ArrayList<>();
  private OnItemClicked onItemClickedListener;

  public ProductsAdapter(OnItemClicked onItemClickedListener) {
    this.onItemClickedListener = onItemClickedListener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    ProductItem item = productItems.get(position);
    holder.bind(item);
  }

  @Override public int getItemCount() {
    return productItems.size();
  }

  public void setItems(List<ProductItem> productItems) {
    this.productItems.clear();
    this.productItems.addAll(productItems);
    notifyDataSetChanged();
  }

  public interface OnItemClicked {
    void onItemClicked(int position);
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvSubtitle) TextView tvSubtitle;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bind(final ProductItem item) {
      tvTitle.setText(item.getTitle());
      tvSubtitle.setText(item.getSubtitle());
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          onItemClickedListener.onItemClicked(productItems.indexOf(item));
        }
      });
    }
  }
}
