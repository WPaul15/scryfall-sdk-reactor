package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;
import com.wpaul15.scryfall.api.query.IMonoFilter;
import com.wpaul15.scryfall.api.query.IMultiFilter;

final class Not<T> extends NegatingFilter<T> {

  Not(IFilter<T> filter) {
    super(filter);
  }

  static <T> Not<T> not(IFilter<T> filter) {
    return new Not<>(filter);
  }

  static final class NotMono<T> extends NegatingMonoFilter<T> {

    NotMono(IMonoFilter<T> filter) {
      super(filter);
    }

    static <T> NotMono<T> not(T value) {
      return not(Exactly.ExactlyMono.exactly(value));
    }

    static <T> NotMono<T> not(IMonoFilter<T> filter) {
      return new NotMono<>(filter);
    }
  }

  static final class NotMulti<T> extends NegatingMultiFilter<T> {

    NotMulti(IMultiFilter<T> filter) {
      super(filter);
    }

    static <T> NotMulti<T> not(IMultiFilter<T> filter) {
      return new NotMulti<>(filter);
    }
  }
}
