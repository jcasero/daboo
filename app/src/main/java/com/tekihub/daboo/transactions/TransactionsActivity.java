package com.tekihub.daboo.transactions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tekihub.daboo.R;
import com.tekihub.daboo.model.ProductModel;
import com.tekihub.daboo.transactions.fragment.TransactionsFragment;

public class TransactionsActivity extends AppCompatActivity {
  public static final String EXTRA_PRODUCT = "extra_product";

  public static Intent getCallIntent(Context context, ProductModel productModel) {
    Intent intent = new Intent(context, TransactionsActivity.class);
    intent.putExtra(EXTRA_PRODUCT, productModel);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.transactions_activity);

    ProductModel productModel = null;
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      productModel = (ProductModel) bundle.get(EXTRA_PRODUCT);
      setActivityTitle(productModel);
    }
    attachFragment(productModel);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  public void setActivityTitle(ProductModel productModel) {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar == null) {
      return;
    }

    actionBar.setTitle(getString(R.string.transactions_for, productModel.sku()));
    actionBar.setHomeButtonEnabled(true);
    actionBar.setDisplayHomeAsUpEnabled(true);
  }

  private void attachFragment(ProductModel productModel) {
    getSupportFragmentManager().beginTransaction()
        .add(R.id.flTransactionsContainer, TransactionsFragment.create(productModel))
        .commit();
  }
}
