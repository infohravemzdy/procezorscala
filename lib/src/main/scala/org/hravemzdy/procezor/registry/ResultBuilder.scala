package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.legalios.service.types.Period
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResult, BuilderResultList, ResultFunc}
import org.hravemzdy.procezor.interfaces.{IArticleDefine, IArticleSpec, IConceptSpec, ITermTarget}
import org.hravemzdy.procezor.registry.factories.IArticleSpecFactory
import org.hravemzdy.procezor.registry.factories.IConceptSpecFactory
import org.hravemzdy.procezor.service.errors.{NoResultFuncError, TermResultError}
import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleDefine, ConceptCode, ContractCode, MonthCode, PositionCode, TermTarget, VariantCode, VersionCode}

class ResultBuilder extends IResultBuilder {
    override var version: VersionCode = VersionCode.getNew()
    override var periodInit: IPeriod = Period.getNew()
    override var articleOrder: Array[ArticleCode] = Array[ArticleCode]()
    override var articlePaths: Map[ArticleCode, Array[IArticleDefine]] = Map[ArticleCode, Array[IArticleDefine]]()
    private var articleModel: Array[IArticleSpec] = Array[IArticleSpec]()
    private var conceptModel: Array[IConceptSpec] = Array[IConceptSpec]()

    override def initWithPeriod(
        version: VersionCode,
        period: IPeriod,
        articleFactory: IArticleSpecFactory,
        conceptFactory: IConceptSpecFactory): Boolean = {
        this.version = version
        this.periodInit = period

        articleModel = articleFactory.getSpecList(period, version)

        conceptModel = conceptFactory.getSpecList(period, version)

        val (order, paths) = DependencyGraph.initGraphModel(articleModel, conceptModel)

        articleOrder = order
        articlePaths = paths

        true
    }

    override def getResults(ruleset: IBundleProps, targets: Array[ITermTarget], finDefs: IArticleDefine): BuilderResultList = {
        val calculTargets = buildCalculsList(periodInit, targets, finDefs)

        val calculResults = buildResultsList(periodInit, ruleset, calculTargets)

        calculResults
    }

    private def buildCalculsList(
        period: IPeriod,
        targets: Array[ITermTarget],
        finDefs: IArticleDefine
    ): Array[ITermCalcul] = {
        val finDefine: IArticleDefine = ArticleDefine.fromDefs(finDefs)

        val targetsSpec: Array[ITermTarget] = addFinDefToTargets(period, targets.toArray, finDefine)

        val targetsStep: Array[ITermTarget] = addExternToTargets(period, targetsSpec)

        val calculsList: Array[ITermCalcul] = addTargetToCalculs(targetsStep)

        return calculsList
    }
    private def buildResultsList(period: IPeriod, ruleset: IBundleProps, calculs: Array[ITermCalcul]): BuilderResultList = {
        var resultsInit: BuilderResultList = Array[BuilderResult]()

        calculs.foldLeft(resultsInit)((agr, x) => mergeResults(agr, x.getResults(period, ruleset, agr).toIndexedSeq))
    }
    private def mergeResults(results: BuilderResultList, resultValues: IndexedSeq[BuilderResult]): BuilderResultList = {
        (results ++ resultValues)
    }
    private def addFinDefToTargets(period: IPeriod, targets: Array[ITermTarget], finDefs: IArticleDefine): Array[ITermTarget] = {
        return mergeItemPendings(period, targets, finDefs)
    }
    private def addExternToTargets(period: IPeriod, targets: Array[ITermTarget]): Array[ITermTarget] = {
        var targetsInit: Array[ITermTarget] = targets.toArray

        var targetList = targets.foldLeft(targetsInit)((agr, item) => mergePendings(period, agr, item))

        var targetSort = targetList.sortWith(TargetComparator(articleOrder.toArray))

        targetSort
    }

    private def addTargetToCalculs(targets: Array[ITermTarget]): Array[ITermCalcul] = {
        val targetsRets = targets.map(x => new TermCalcul(x, getCalculFunc(conceptModel, x.concept)))
        return targetsRets.toArray
    }
    private def mergePendings(period: IPeriod, init: Array[ITermTarget], target: ITermTarget): Array[ITermTarget] = {
        var resultList: Array[ITermTarget] = init.toArray

        val pendingsSpec = articlePaths.get(target.article)

        if (pendingsSpec.isDefined) {
            val pendingsPath = pendingsSpec.get
            resultList = pendingsPath.foldLeft(resultList)((agr, defs) => mergeItemPendings(period, agr, defs))
        }
        return resultList
    }
    private def mergeItemPendings(period: IPeriod, init: Array[ITermTarget], articleDefs: IArticleDefine): Array[ITermTarget] = {
        val monthCode = MonthCode.get(period.code)

        val contract = ContractCode.getNew()
        val position = PositionCode.getNew()

        var resultList: Array[ITermTarget] = init.toArray

        var initTarget = init.find(x => x.article == articleDefs.code)

        if (initTarget.isEmpty) {
            val variant = VariantCode.get(1)

            val resultItem = new TermTarget(monthCode, contract, position, variant, articleDefs.code, articleDefs.role)

            resultList = (resultList :+ resultItem)
        }
        resultList
    }
    private def getCalculFunc(conceptsModel: Array[IConceptSpec], concept: ConceptCode): Option[ResultFunc] = {
        var conceptSpec = conceptsModel.find(a => (a.code == concept))

        if (conceptSpec.isEmpty) {
           return Some(notFoundCalculFunc)
        }
        conceptSpec.get.resultDelegate
    }
    private def notFoundCalculFunc(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultError = NoResultFuncError(period, target)
        Array(Left(resultError))
    }
    private def TargetComparator(topoOrders: Array[ArticleCode]) : (ITermTarget, ITermTarget) => Boolean = {
        (x: ITermTarget, y: ITermTarget) => {
            val xIndex = topoOrders.indexOf(x.article)

            val yIndex = topoOrders.indexOf(y.article)

            var result: Boolean = false
            if (xIndex == -1 && yIndex == -1)
            {
                result = (0 < 0)
            }

            if (xIndex == -1 && yIndex != -1)
            {
                result = (-1 < 0)
            }

            if (xIndex != -1 && yIndex == -1)
            {
                result = (1 < 0)
            }
            result = (xIndex.compare(yIndex) < 0)
            result
        }
    }
}