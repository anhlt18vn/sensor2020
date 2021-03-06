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
import org.rdf4led.query.expr.aggs.acc.AccDistinctAll;
import org.rdf4led.query.QueryContext;
import org.rdf4led.common.mapping.Mapping;

import java.math.BigInteger;

/**
 * AggCountDistinct.java
 *
 * <p>TODO Modified from Jena
 *
 * <p>Author : Anh Le-Tuan Contact: anh.le@deri.org anh.letuan@insight-centre.org
 *
 * <p>27 Oct 2015
 */
public class AggCountDistinct<Node> extends AggregatorBase<Node> {
  // ---- COUNT(DISTINCT *)
  private QueryContext<Node> queryContext;

  public AggCountDistinct(QueryContext<Node> queryContext) {
    this.queryContext = queryContext;
  }

  @Override
  public Aggregator<Node> copy(Expr<Node> expr) {
    if (expr != null) {}

    return new AggCountDistinct<Node>(queryContext);
  }

  @Override
  public String toString() {
    return "count(distinct *)";
  }

  @Override
  public Expr<Node> getExpr() {
    return null;
  }

  @Override
  public Acc<Node> createAccumulator() {
    return new AccCountDistinct();
  }

  @Override
  public Node getValueEmpty() {
    return queryContext.getnvZero();
  }

  @Override
  public int hashCode() {
    return HC_AggCountDistinct;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof AggCountDistinct)) return false;
    return true;
  }

  class AccCountDistinct extends AccDistinctAll<Node> {
    private long count = 0;

    public AccCountDistinct() {
      count = 0;
    }

    @Override
    public Node getValue() {
      return queryContext.dictionary().createIntegerNode(BigInteger.valueOf(count));
    }

    @Override
    protected void updateArrivalDistinct(Mapping<Node> mapping) {
      count++;
    }

    @Override
    protected void updateExpiryDistinct(Mapping<Node> mapping) {
      count--;
    }

    @Override
    protected boolean isUpdate() {
      return true;
    }
  }
}
