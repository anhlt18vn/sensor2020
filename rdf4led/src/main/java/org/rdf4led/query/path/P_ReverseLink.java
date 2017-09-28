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

package org.rdf4led.query.path;

public class P_ReverseLink<Node> extends P_Path0<Node> {
  public P_ReverseLink(Node n) {
    super(n);
  }

  @Override
  public boolean isForward() {
    return false;
  }

  @Override
  public void visit(PathVisitor<Node> visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equalTo(Path<Node> path2) {
    if (!(path2 instanceof P_ReverseLink)) {
      return false;
    }

    P_ReverseLink<Node> other = (P_ReverseLink<Node>) path2;

    return node.equals(other.node);
  }

  @Override
  public int hashCode() {
    return node.hashCode() ^ hashRevLink;
  }
}
