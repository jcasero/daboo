package com.tekihub.daboo.domain.repository;

import com.tekihub.daboo.domain.entity.Transaction;
import io.reactivex.Observable;
import java.util.List;

public interface TransactionRepository {
  Observable<List<Transaction>> transactions();
}
