package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleSeqs, ArticleTerm, ConceptCode}

trait IArticleDefine extends ISpecDefine[ArticleCode] {
    val seqs: ArticleSeqs
    val role: ConceptCode
    def term(): ArticleTerm
}