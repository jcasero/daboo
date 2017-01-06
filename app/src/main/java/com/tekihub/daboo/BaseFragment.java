package com.tekihub.daboo;

import android.support.v4.app.Fragment;
import com.tekihub.daboo.di.ApplicationComponent;

public class BaseFragment extends Fragment {
  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getActivity().getApplication()).getApplicationComponent();
  }
}
