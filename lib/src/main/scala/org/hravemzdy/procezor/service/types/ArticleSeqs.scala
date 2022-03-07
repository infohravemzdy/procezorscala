package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ISpecSeqs

case class ArticleSeqs(override val value: Int) extends ISpecSeqs {
}

object ArticleSeqs {
  def zero: ArticleSeqs = getNew
  def getNew: ArticleSeqs = ArticleSeqs(0)
  def get(value: Int): ArticleSeqs = ArticleSeqs(value)
}

