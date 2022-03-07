package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResultList, ResultFunc}
import org.hravemzdy.procezor.interfaces.{IArticleSpec, ITermSymbol, ITermTarget}
import org.hravemzdy.procezor.registry.providers.ArticleSpec

trait ITermCalcul extends ITermSymbol {
    val target: ITermTarget
    val spec: Option[IArticleSpec]
    val resultDelegate: Option[ResultFunc]
    def getResults(period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList
}

object TermCalculTypes {
    type ITermCalculList = Array[ITermCalcul]
}

