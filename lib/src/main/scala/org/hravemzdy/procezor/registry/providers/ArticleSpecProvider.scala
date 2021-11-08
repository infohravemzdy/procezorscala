package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.service.types.ArticleCode

trait IArticleSpecProvider extends ISpecProvider[IArticleSpec, ArticleCode] {
}

abstract class ArticleSpecProvider(override val code: ArticleCode) extends IArticleSpecProvider {
    def this(_code: Int) = this(ArticleCode.get(_code))
}
