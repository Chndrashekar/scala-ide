/*
 * Copyright (c) 2015 Contributor. All rights reserved.
*/
package org.scalaide.debug.internal.expression.features

import org.junit.Ignore
import org.junit.Test
import org.scalaide.debug.internal.expression.BaseIntegrationTest
import org.scalaide.debug.internal.expression.Names.Java
import org.scalaide.debug.internal.expression.Names.Scala

trait MethodsAsFunctionsTest { self: BaseIntegrationTest =>

  @Test
  def methodsFromObject(): Unit = {
    eval("List(1, 2).foldLeft(ObjectMethods.zero)(ObjectMethods.sum)", 3, Java.primitives.int)
    eval("List(-1, 1).filter(_ > ObjectMethods.zero)", List(1), Scala.::)
  }

  @Ignore("Potential bug in Toolbox.")
  @Test
  def methodAsMapParam(): Unit = eval("nat.map(inc)", Array(3, 4, 5), Scala.Array(Scala.primitives.Int))

  @Test
  def methodCall(): Unit = eval("zero", 0, Java.primitives.int)

  @Ignore("Potential bug in Toolbox.")
  @Test
  def methodAsFilterParam(): Unit = eval("nat.filter(_ > inc(inc(zero)))", Array(3, 4), Scala.Array(Scala.primitives.Int))

  @Ignore("Potential bug in Toolbox.")
  @Test
  def methodsAsFoldParam(): Unit = eval("nat.foldLeft(zero)(sum)", 9, Java.primitives.int)

  @Test
  def methodAsGetOrElseParam(): Unit = eval("None.getOrElse(zero)", 0, Java.primitives.int)

  @Test
  def andThenMethods(): Unit = eval("(inc _ andThen inc _)(zero)", 2, Java.primitives.int)

  @Ignore("Needs investigation.")
  @Test
  def composeMethods(): Unit = eval("(inc _ compose dec)(zero)", 0, Java.primitives.int)
}
