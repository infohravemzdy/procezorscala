package org.hravemzdy.procezor.interfaces

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.service.types.ArticleCode

object BuilderType {
    type BuilderResult = Either[ITermResultError, ITermResult]
    type BuilderResultList = Array[BuilderResult]
    type ResultFunc = (ITermTarget, IPeriod, IBundleProps, BuilderResultList) => BuilderResultList
}

trait IConceptSpec extends IConceptDefine {
    val path : Array[ArticleCode]
    val resultDelegate : Option[BuilderType.ResultFunc]
}

