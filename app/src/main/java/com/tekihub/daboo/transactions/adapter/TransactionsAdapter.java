package com.tekihub.daboo.transactions.adapter;

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

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {
  private static final int HEADER_TYPE = 0;
  private static final int CONVERSION_TYPE = 1;

  private List<TransactionItem> transactionItems = new ArrayList<>();

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewHolder viewHolder;

    int resLayoutId = viewType == HEADER_TYPE ? R.layout.header_item : R.layout.conversion_item;
    View view = LayoutInflater.from(parent.getContext()).inflate(resLayoutId, parent, false);
    viewHolder = viewType == HEADER_TYPE ? new HeaderViewHolder(view) : new ConversionViewHolder(view);

    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    TransactionItem item = transactionItems.get(position);
    holder.bind(item);
  }

  @Override public int getItemCount() {
    return transactionItems.size();
  }

  @Override public int getItemViewType(int position) {
    TransactionItem item = transactionItems.get(position);
    return item instanceof HeaderItem ? HEADER_TYPE : CONVERSION_TYPE;
  }

  public void setItems(List<TransactionItem> transactionItems) {
    this.transactionItems.clear();
    this.transactionItems.addAll(transactionItems);
    notifyDataSetChanged();
  }

  abstract class ViewHolder extends RecyclerView.ViewHolder {
    ViewHolder(View itemView) {
      super(itemView);
    }

    abstract void bind(TransactionItem item);
  }

  class HeaderViewHolder extends ViewHolder {
    @BindView(R.id.tvTitle) TextView tvTitle;

    HeaderViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bind(final TransactionItem item) {
      HeaderItem headerItem = (HeaderItem) item;
      tvTitle.setText(headerItem.getTitle());
    }
  }

  class ConversionViewHolder extends ViewHolder {
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvSubtitle) TextView tvSubtitle;

    ConversionViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bind(final TransactionItem item) {
      ConversionItem conversionItem = (ConversionItem) item;
      tvTitle.setText(conversionItem.getTitle());
      tvSubtitle.setText(conversionItem.getSubtitle());
    }
  }
}
