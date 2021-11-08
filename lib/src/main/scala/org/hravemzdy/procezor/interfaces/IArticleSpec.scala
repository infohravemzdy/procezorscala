package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ArticleCode

trait IArticleSpec extends IArticleDefine {
    val sums: Array[ArticleCode]
    def defs() : IArticleDefine
}