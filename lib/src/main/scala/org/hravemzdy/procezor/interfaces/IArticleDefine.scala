package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode

trait IArticleDefine extends ISpecDefine[ArticleCode] {
    val role: ConceptCode
}