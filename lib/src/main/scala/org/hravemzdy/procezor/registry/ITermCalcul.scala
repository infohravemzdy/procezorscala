package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResultList, ResultFunc}
import org.hravemzdy.procezor.interfaces.ITermSymbol
import org.hravemzdy.procezor.interfaces.ITermTarget

trait ITermCalcul extends ITermSymbol {
    val target: ITermTarget
    val resultDelegate: Option[ResultFunc]
    def getResults(period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList
}