package com.tekihub.daboo.domain.interactor;

import com.tekihub.daboo.domain.entity.Transaction;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import com.tekihub.daboo.domain.repository.TransactionRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class GetTransactions extends UseCase<List<Transaction>, Void> {
  private final TransactionRepository transactionRepository;

  @Inject GetTransactions(TransactionRepository transactionRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.transactionRepository = transactionRepository;
  }

  @Override Observable<List<Transaction>> buildUseCaseObservable(Void aVoid) {
    return transactionRepository.transactions();
  }
}
