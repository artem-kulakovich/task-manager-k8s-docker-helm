package by.bntu.fitr.authenticationservice.dao.impl;


import org.jooq.*;

import java.util.List;
import java.util.Optional;


public abstract class AbstractDAO<T> {

    public Result<Record> findByConditionWithFetchType(final SelectJoinStep<Record> selectJoinStep,
                                                       final Condition condition) {
        return selectJoinStep
                .where(condition)
                .fetch();
    }

    public Optional<T> doSingleSelectWithFetchType(final Condition condition,
                                                   final String fetchType) {
        return singleExtractWithFetchType(
                findByConditionWithFetchType(getSelectJoinStep(fetchType), condition),
                fetchType
        );
    }

    public List<T> doMultipleSelectWithFetchType(final Condition condition,
                                                 final String fetchType) {
        return multipleExtractWithFetchType(
                findByConditionWithFetchType(getSelectJoinStep(fetchType), condition),
                fetchType
        );
    }

    public abstract Optional<T> singleExtractWithFetchType(final Result<Record> recordList,
                                                           final String fetchType);

    public abstract List<T> multipleExtractWithFetchType(final Result<Record> recordList,
                                                         final String fetchType);

    public abstract SelectJoinStep<Record> getSelectJoinStep(final String fetchType);
}
