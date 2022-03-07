package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResult, BuilderResultList, ResultFunc}
import org.hravemzdy.procezor.interfaces.{IArticleSpec, ITermTarget}
import org.hravemzdy.procezor.service.errors.NoResultFuncError
import org.hravemzdy.procezor.service.types.TermSymbol

class TermCalcul(override val target: ITermTarget, override val spec: Option[IArticleSpec], override val resultDelegate: Option[ResultFunc])
    extends TermSymbol(target.monthCode, target.contract, target.position, target.variant, target.article) with ITermCalcul {

    override def getResults(period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultTarget = resultDelegate match  {
            case Some(func) => func(target, spec, period, ruleset, results)
            case None => Array[BuilderResult](Left(NoResultFuncError(period, target)))
        }
        resultTarget.clone()
    }
}