package com.tekihub.daboo.products;

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
  private List<AdapterItem> adapterItems = new ArrayList<>();

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    AdapterItem item = adapterItems.get(position);
    holder.bind(item);
  }

  @Override public int getItemCount() {
    return adapterItems.size();
  }

  public void setItems(List<AdapterItem> adapterItems) {
    this.adapterItems.clear();
    this.adapterItems.addAll(adapterItems);
    notifyDataSetChanged();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvSubtitle) TextView tvSubtitle;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bind(AdapterItem item) {
      tvTitle.setText(item.getTitle());
      tvSubtitle.setText(item.getSubtitle());
    }
  }
}
