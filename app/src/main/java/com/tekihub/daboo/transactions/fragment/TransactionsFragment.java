package com.tekihub.daboo.transactions.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tekihub.daboo.BaseFragment;
import com.tekihub.daboo.R;
import com.tekihub.daboo.model.ProductModel;
import com.tekihub.daboo.transactions.adapter.TransactionItem;
import com.tekihub.daboo.transactions.adapter.TransactionsAdapter;
import com.tekihub.daboo.transactions.di.DaggerTransactionsComponent;
import com.tekihub.daboo.transactions.presenter.TransactionsPresenter;
import java.util.List;
import javax.inject.Inject;

public class TransactionsFragment extends BaseFragment implements TransactionsPresenter.View {
  public static final String EXTRA_PRODUCT = "extra_product";

  @BindView(R.id.rvTransactions) RecyclerView rvTransactions;
  @BindView(R.id.tvInfo) TextView tvInfo;
  @BindView(R.id.pbLoading) ProgressBar pbLoading;

  @Inject TransactionsPresenter presenter;

  private TransactionsAdapter transactionsAdapter = new TransactionsAdapter();
  private ProductModel productModel;

  public static Fragment create(ProductModel productModel) {
    Bundle arguments = new Bundle();
    arguments.putParcelable(EXTRA_PRODUCT, productModel);
    Fragment fragment = new TransactionsFragment();
    fragment.setArguments(arguments);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    inject();
    presenter.attachView(this);

    Bundle arguments = getArguments();
    if (arguments != null && arguments.containsKey(EXTRA_PRODUCT)) {
      productModel = arguments.getParcelable(EXTRA_PRODUCT);
    } else {
      showNoTransactionsInfo();
    }
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.transactions_fragment, container, false);
    ButterKnife.bind(this, view);
    initViews();
    presenter.initialize(productModel);
    return view;
  }

  @Override public void showNoTransactionsInfo() {
    pbLoading.setVisibility(View.GONE);
    rvTransactions.setVisibility(View.GONE);
    tvInfo.setVisibility(View.VISIBLE);
  }

  @Override public void showTransactions(List<TransactionItem> items) {
    transactionsAdapter.setItems(items);
    tvInfo.setVisibility(View.GONE);
    pbLoading.setVisibility(View.GONE);
    rvTransactions.setVisibility(View.VISIBLE);
  }

  @Override public void showLoading() {
    tvInfo.setVisibility(View.GONE);
    rvTransactions.setVisibility(View.GONE);
    pbLoading.setVisibility(View.VISIBLE);
  }

  private void initViews() {
    rvTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
    rvTransactions.setAdapter(transactionsAdapter);
  }

  private void inject() {
    DaggerTransactionsComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
  }
}
