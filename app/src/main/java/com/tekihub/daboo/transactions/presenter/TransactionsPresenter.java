package com.tekihub.daboo.transactions.presenter;

import com.tekihub.daboo.di.PerActivity;
import com.tekihub.daboo.domain.ConversionParams;
import com.tekihub.daboo.domain.entity.Conversion;
import com.tekihub.daboo.domain.entity.Currency;
import com.tekihub.daboo.domain.interactor.GetConversion;
import com.tekihub.daboo.model.ProductModel;
import com.tekihub.daboo.model.TransactionModel;
import com.tekihub.daboo.mvp.BasePresenter;
import com.tekihub.daboo.transactions.adapter.HeaderItem;
import com.tekihub.daboo.transactions.adapter.TransactionItem;
import com.tekihub.daboo.transactions.mapper.ConversionMapper;
import com.tekihub.daboo.transactions.mapper.HeaderMapper;
import io.reactivex.observers.DisposableObserver;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@PerActivity public class TransactionsPresenter extends BasePresenter<TransactionsPresenter.View> {
  private static final String GBP_CURRENCY = "GBP";
  private final GetConversion getConversion;
  private final ConversionMapper conversionMapper;
  private final HeaderMapper headerMapper;
  private List<Conversion> conversions;
  private ProductModel productModel;

  @Inject public TransactionsPresenter(GetConversion getConversion, ConversionMapper conversionMapper,
      HeaderMapper headerMapper) {
    this.getConversion = getConversion;
    this.conversionMapper = conversionMapper;
    this.headerMapper = headerMapper;
  }

  public void initialize(ProductModel productModel) {
    this.productModel = productModel;
    if (productModel == null) {
      view.showNoTransactionsInfo();
    } else {
      fetchTransactionsConverted();
    }
  }

  public void fetchTransactionsConverted() {
    view.showLoading();
    List<Conversion> conversionsList = new ArrayList<>();
    List<TransactionModel> transactionModels = productModel.transactions();
    for (TransactionModel transactionModel : transactionModels) {
      conversionsList.add(new Conversion(new Currency(transactionModel.currency(), transactionModel.amount())));
    }
    getConversion.execute(new DisposableObserver<List<Conversion>>() {
      @Override public void onNext(List<Conversion> conversions) {
        TransactionsPresenter.this.conversions = conversions;
      }

      @Override public void onError(Throwable e) {
        view.showNoTransactionsInfo();
      }

      @Override public void onComplete() {
        if (conversions != null && conversions.size() > 0) {
          double total = getTransactionTotal(conversions);
          List<TransactionItem> items = conversionMapper.transformAll(conversions);
          HeaderItem headerItem = headerMapper.transform(total, GBP_CURRENCY);
          items.add(0, headerItem);
          view.showTransactions(items);
        } else {
          view.showNoTransactionsInfo();
        }
      }
    }, new ConversionParams(conversionsList, GBP_CURRENCY));
  }

  private double getTransactionTotal(List<Conversion> conversions) {
    double total = 0;
    for (Conversion conversion : conversions) {
      total += conversion.getConverted().getValue();
    }
    return total;
  }

  public interface View extends BasePresenter.View {
    void showNoTransactionsInfo();

    void showTransactions(List<TransactionItem> items);

    void showLoading();
  }
}
