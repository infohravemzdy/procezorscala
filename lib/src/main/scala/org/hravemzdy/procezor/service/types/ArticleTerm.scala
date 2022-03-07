package org.hravemzdy.procezor.service.types

case class ArticleTerm(val code: ArticleCode, val seqs: ArticleSeqs) extends Ordered[ArticleTerm] {
  override def compare(that: ArticleTerm): Int = {
    if (seqs.compare(that.seqs)==0) {
      return this.code.compare(that.code)
    }
    this.seqs.compare(that.seqs)
  }
}

object ArticleTerm {
  def zero: ArticleTerm = getNew
  def getNew: ArticleTerm = ArticleTerm(ArticleCode.getNew, ArticleSeqs.getNew)
  def get(code: Int, seqs: Int): ArticleTerm = ArticleTerm(ArticleCode.get(code), ArticleSeqs.get(seqs))
}

