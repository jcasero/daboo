package com.tekihub.daboo.products.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.tekihub.daboo.products.adapter.ProductItem;
import com.tekihub.daboo.products.adapter.ProductsAdapter;
import com.tekihub.daboo.products.di.DaggerProductsComponent;
import com.tekihub.daboo.products.presenter.ProductsPresenter;
import com.tekihub.daboo.transactions.TransactionsActivity;
import java.util.List;
import javax.inject.Inject;

public class ProductsFragment extends BaseFragment implements ProductsPresenter.View, ProductsAdapter.OnItemClicked {
  @BindView(R.id.rvProducts) RecyclerView rvProducts;
  @BindView(R.id.tvInfo) TextView tvInfo;
  @BindView(R.id.pbLoading) ProgressBar pbLoading;

  @Inject ProductsPresenter presenter;

  private ProductsAdapter productsAdapter = new ProductsAdapter(this);

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    inject();
    presenter.attachView(this);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.products_fragment, container, false);
    ButterKnife.bind(this, view);
    initViews();
    presenter.fetchProducts();
    return view;
  }

  @Override public void showNoProductsInfo() {
    pbLoading.setVisibility(View.GONE);
    rvProducts.setVisibility(View.GONE);
    tvInfo.setVisibility(View.VISIBLE);
  }

  @Override public void showProducts(List<ProductItem> items) {
    productsAdapter.setItems(items);
    tvInfo.setVisibility(View.GONE);
    pbLoading.setVisibility(View.GONE);
    rvProducts.setVisibility(View.VISIBLE);
  }

  @Override public void showLoading() {
    tvInfo.setVisibility(View.GONE);
    rvProducts.setVisibility(View.GONE);
    pbLoading.setVisibility(View.VISIBLE);
  }

  @Override public void showProductDetail(ProductModel productModel) {
    Intent intent = TransactionsActivity.getCallIntent(getContext(), productModel);
    startActivity(intent);
  }

  @Override public void onItemClicked(int position) {
    presenter.onItemClicked(position);
  }

  private void initViews() {
    rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
    rvProducts.setAdapter(productsAdapter);
  }

  private void inject() {
    DaggerProductsComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
  }
}
