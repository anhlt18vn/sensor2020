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

package org.rdf4led.query.sparql.algebra.op;

import org.rdf4led.query.sparql.algebra.Op;
import org.rdf4led.query.sparql.algebra.OpVisitor;
import org.rdf4led.query.sparql.algebra.transform.Transform;
import org.rdf4led.query.expr.ExprList;

public class OpFilter<Node> extends Op1<Node> {
  ExprList<Node> expressions;

  public OpFilter(Op<Node> sub) {
    super(sub);

    expressions = new ExprList<>();
  }

  public OpFilter(ExprList<Node> exprs, Op<Node> sub) {
    super(sub);

    expressions = exprs;
  }

  /** Compress multiple filters: (filter (filter (filter org.rdf4led.sparql.algebra.op)))) into one (filter org.rdf4led.sparql.algebra.op) */
  // static
  public OpFilter<Node> tidy(OpFilter<Node> base) {
    ExprList<Node> exprs = new ExprList<Node>();

    Op<Node> op = base;

    while (op instanceof OpFilter) {
      OpFilter<Node> f = (OpFilter<Node>) op;

      exprs.addAll(f.getExprs());

      op = f.getSubOp();
    }

    return new OpFilter<Node>(exprs, op);
  }

  public ExprList<Node> getExprs() {
    return expressions;
  }

  @Override
  public Op<Node> apply(Transform<Node> transform, Op<Node> subOp) {
    return transform.transform(this, subOp);
  }

  @Override
  public void visit(OpVisitor<Node> opVisitor) {
    opVisitor.visit(this);
  }

  @Override
  public Op<Node> copy(Op<Node> subOp) {
    return new OpFilter<Node>(expressions, subOp);
  }

  @Override
  public int hashCode() {
    return expressions.hashCode();
  }

  @Override
  public boolean equalTo(Op<Node> other) {
    if (!(other instanceof OpFilter)) {
      return false;
    }

    OpFilter<Node> opFilter = (OpFilter<Node>) other;

    if (!expressions.equals(opFilter.expressions)) {
      return false;
    }

    return getSubOp().equalTo(opFilter.getSubOp());
  }
}
