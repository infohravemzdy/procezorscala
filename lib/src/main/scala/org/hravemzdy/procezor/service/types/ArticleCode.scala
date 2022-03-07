package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ISpecCode

case class ArticleCode(override val value: Int) extends ISpecCode {
}

object ArticleCode {
    def zero: ArticleCode = getNew
    def getNew: ArticleCode = ArticleCode(0)
    def get(value: Int): ArticleCode = ArticleCode(value)
}


