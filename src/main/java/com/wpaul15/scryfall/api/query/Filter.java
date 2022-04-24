package com.wpaul15.scryfall.api.query;

abstract class Filter<T> {

  protected abstract String toQueryParams(String key);
}
