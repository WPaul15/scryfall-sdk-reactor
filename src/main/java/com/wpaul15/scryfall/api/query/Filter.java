package com.wpaul15.scryfall.api.query;

import java.util.regex.Pattern;

abstract class Filter<T> {

  protected static final Pattern WHITESPACE = Pattern.compile("\\s");

  protected abstract String toQueryParams(String key);
}
