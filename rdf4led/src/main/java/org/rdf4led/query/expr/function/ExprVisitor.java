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

package org.rdf4led.query.expr.function;

import org.rdf4led.query.expr.ExprVar;
import org.rdf4led.query.expr.aggs.ExprAggregator;

/**
 * ExprVisitor.java
 *
 * <p>TODO Modified from Jena
 *
 * <p>Author : Anh Le-Tuan Contact: anh.le@deri.org anh.letuan@insight-centre.org
 *
 * <p>27 Oct 2015
 */
public interface ExprVisitor<Node> {
  void startVisit();

  void visit(ExprFunction0<Node> func);

  void visit(ExprFunction1<Node> func);

  void visit(ExprFunction2<Node> func);

  void visit(ExprFunction3<Node> func);

  void visit(ExprFunctionN<Node> func);

  void visit(ExprFunctionOp<Node> funcOp);

  void visit(Node nodeValue);

  void visit(ExprVar<Node> exprVar);

  void visit(ExprAggregator<Node> eAgg);

  void finishVisit();
}
