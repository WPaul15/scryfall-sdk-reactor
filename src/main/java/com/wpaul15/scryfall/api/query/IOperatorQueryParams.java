package com.wpaul15.scryfall.api.query;

interface IOperatorQueryParams extends IQueryParams {

  String toQueryParams(String operator);
}
