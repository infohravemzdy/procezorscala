package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResult, BuilderResultList, ResultFunc}
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.service.errors.NoResultFuncError
import org.hravemzdy.procezor.service.types.TermSymbol

class TermCalcul(override val target: ITermTarget, override val resultDelegate: Option[ResultFunc])
    extends TermSymbol(target.monthCode, target.contract, target.position, target.variant, target.article) with ITermCalcul {

    override def getResults(period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultTarget = callResultDelegate(target, period, ruleset, results)
        resultTarget.clone()
    }
    def callResultDelegate(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val result = resultDelegate match  {
            case Some(func) => func(target, period, ruleset, results)
            case None => Array[BuilderResult](Left(NoResultFuncError(period, target)))
        }
        result.clone()
    }
}