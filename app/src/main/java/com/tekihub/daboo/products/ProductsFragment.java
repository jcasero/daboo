package com.tekihub.daboo.products;

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
import com.tekihub.daboo.domain.entity.Product;
import com.tekihub.daboo.products.di.DaggerProductsComponent;
import com.tekihub.daboo.products.mapper.ProductsMapper;
import java.util.List;
import javax.inject.Inject;

public class ProductsFragment extends BaseFragment implements ProductsPresenter.View {
  @BindView(R.id.rvProducts) RecyclerView rvProducts;
  @BindView(R.id.tvInfo) TextView tvInfo;
  @BindView(R.id.pbLoading) ProgressBar pbLoading;

  @Inject ProductsPresenter presenter;

  private ProductsAdapter productsAdapter = new ProductsAdapter();
  private ProductsMapper productsMapper = new ProductsMapper();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    inject();
    presenter.attachView(this);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.products_fragment, container);
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

  @Override public void showProducts(List<Product> products) {
    List<AdapterItem> adapterItems = productsMapper.transformAll(products);
    productsAdapter.setItems(adapterItems);
    tvInfo.setVisibility(View.GONE);
    pbLoading.setVisibility(View.GONE);
    rvProducts.setVisibility(View.VISIBLE);
  }

  @Override public void showLoading() {
    tvInfo.setVisibility(View.GONE);
    rvProducts.setVisibility(View.GONE);
    pbLoading.setVisibility(View.VISIBLE);
  }

  private void initViews() {
    rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
    rvProducts.setAdapter(productsAdapter);
  }

  private void inject() {
    DaggerProductsComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
  }
}
