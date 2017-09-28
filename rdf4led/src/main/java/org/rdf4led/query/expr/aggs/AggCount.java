/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rdf4led.query.expr.aggs;

import org.rdf4led.query.expr.Expr;
import org.rdf4led.query.expr.aggs.acc.Acc;
import org.rdf4led.query.QueryContext;
import org.rdf4led.common.mapping.Mapping;

import java.math.BigInteger;

/**
 * AggCount.java
 *
 * <p>TODO Modified from Jena
 *
 * <p>Author : Anh Le-Tuan Contact: anh.le@deri.org anh.letuan@insight-centre.org
 *
 * <p>26 Oct 2015
 */
public class AggCount<Node> extends AggregatorBase<Node> {
  // ---- COUNT(*)
  private QueryContext<Node> queryContext;

  public AggCount(QueryContext<Node> queryContext) {
    this.queryContext = queryContext;
  }

  @Override
  public Aggregator<Node> copy(Expr<Node> expr) {
    if (expr != null) {}

    return new AggCount<Node>(queryContext);
  }

  @Override
  public Expr<Node> getExpr() {
    return null;
  }

  @Override
  public Acc<Node> createAccumulator() {
    return new AccCount();
  }

  public String toString() {
    return "count(*)";
  }

  @Override
  public Node getValueEmpty() {
    return queryContext.getnvZero();
  }

  @Override
  public int hashCode() {
    return HC_AggCount;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof AggCount)) return false;
    return true;
  }

  class AccCount implements Acc<Node> {
    private long count;

    public AccCount() {
      count = 0;
    }

    @Override
    public Node getValue() {
      return queryContext.dictionary().createIntegerNode(BigInteger.valueOf(count));
    }

    @Override
    public boolean updateArrival(Mapping<Node> mapping) {
      count++;

      return true;
    }

    @Override
    public boolean updateExpiry(Mapping<Node> mapping) {
      count--;

      return true;
    }
  }
}
