package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleDefine

case class ArticleDefine(override val code: ArticleCode, override val role: ConceptCode) extends IArticleDefine {
    def this() = this(ArticleCode.getNew(), ConceptCode.getNew())
    def this(code: ArticleCode) = this(code, ConceptCode.getNew())
}

object ArticleDefine {
    def getNew() = new ArticleDefine()
    def fromInt(code: Int, role: Int) = ArticleDefine(ArticleCode.get(code), ConceptCode.get(role))
    def fromDefs(defs: IArticleDefine) = ArticleDefine(defs.code, defs.role)
}