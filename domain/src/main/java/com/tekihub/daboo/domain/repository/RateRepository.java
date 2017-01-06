package com.tekihub.daboo.domain.repository;

import com.tekihub.daboo.domain.entity.Rate;
import io.reactivex.Observable;
import java.util.List;

public interface RateRepository {
  Observable<List<Rate>> rates();
}
