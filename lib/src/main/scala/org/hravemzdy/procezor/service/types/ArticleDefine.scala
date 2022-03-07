package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleDefine

case class ArticleDefine(override val code: ArticleCode, override val seqs: ArticleSeqs, override val role: ConceptCode) extends IArticleDefine {
  def this() = this(ArticleCode.getNew, ArticleSeqs.getNew, ConceptCode.getNew)
    def this(code: ArticleCode) = this(code, ArticleSeqs.getNew, ConceptCode.getNew)
    override def term(): ArticleTerm = {
        return ArticleTerm(code, seqs)
    }
}

object ArticleDefine {
    def getNew() = new ArticleDefine()
    def fromInt(code: Int, seqs: Int, role: Int) = ArticleDefine(ArticleCode.get(code), ArticleSeqs.get(seqs), ConceptCode.get(role))
    def fromDefs(defs: IArticleDefine) = ArticleDefine(defs.code, defs.seqs, defs.role)
}