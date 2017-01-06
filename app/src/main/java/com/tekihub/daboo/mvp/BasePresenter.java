package com.tekihub.daboo.mvp;

public abstract class BasePresenter<T extends BasePresenter.View> {
  protected T view;

  public void attachView(T view) {
    this.view = view;
  }

  public interface View {

  }
}
