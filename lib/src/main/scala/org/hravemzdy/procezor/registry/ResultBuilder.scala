package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.legalios.service.types.Period
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResult, BuilderResultList, ResultFunc}
import org.hravemzdy.procezor.interfaces.TermTargetTypes.ITermTargetList
import org.hravemzdy.procezor.interfaces.{IArticleDefine, IArticleSpec, IConceptSpec, IContractTerm, IPositionTerm, ITermTarget}
import org.hravemzdy.procezor.registry.TermCalculTypes.ITermCalculList
import org.hravemzdy.procezor.registry.factories.IArticleSpecFactory
import org.hravemzdy.procezor.registry.factories.IConceptSpecFactory
import org.hravemzdy.procezor.service.errors.{NoResultFuncError, TermResultError}
import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleDefine, ArticleTerm, ConceptCode, ContractCode, MonthCode, PositionCode, TermTarget, VariantCode, VersionCode}

import scala.::

class ResultBuilder extends IResultBuilder {
    override var version: VersionCode = VersionCode.getNew
    override var periodInit: IPeriod = Period.getNew()
    override var articleOrder: Array[ArticleTerm] = Array[ArticleTerm]()
    override var articlePaths: Map[ArticleTerm, Array[IArticleDefine]] = Map[ArticleTerm, Array[IArticleDefine]]()
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

    override def getResults(ruleset: IBundleProps,
                            contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                            targets: ITermTargetList, calcArticles: Array[ArticleCode]): BuilderResultList = {
        val calculTargets = buildCalculsList(periodInit, ruleset,
            contractTerms, positionTerms, targets, calcArticles)

        val calculResults = buildResultsList(periodInit, ruleset, calculTargets)

        calculResults
    }

    private def buildCalculsList(
        period: IPeriod, ruleset: IBundleProps,
        contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
        targets: ITermTargetList,
        calcArticles: Array[ArticleCode]): ITermCalculList = {
        val specDefines: Array[Option[IArticleSpec]] = calcArticles.map { a => articleModel.find { m => m.code == a } }

        val calcDefines = specDefines.filter { s => (s.isDefined) }.map { t => t.get }
          .map { x => ArticleDefine.fromInt(x.code.value, x.seqs.value, x.role.value).asInstanceOf[IArticleDefine] }

        val targetsSpec: ITermTargetList = addFinDefToTargets(period, ruleset,
            contractTerms, positionTerms, targets.toArray, calcDefines)

        val targetsStep: ITermTargetList = addExternToTargets(period, ruleset,
            contractTerms, positionTerms, targetsSpec)

        val calculsList: ITermCalculList = addTargetToCalculs(targetsStep)

        return calculsList
    }
    private def buildResultsList(period: IPeriod, ruleset: IBundleProps, calculs: ITermCalculList): BuilderResultList = {
        var resultsInit: BuilderResultList = Array[BuilderResult]()

        calculs.foldLeft(resultsInit)((agr, x) => mergeResults(agr, x.getResults(period, ruleset, agr).toIndexedSeq))
    }
    private def mergeResults(results: BuilderResultList, resultValues: IndexedSeq[BuilderResult]): BuilderResultList = {
        (results ++ resultValues)
    }
    private def addFinDefToTargets(period: IPeriod, ruleset: IBundleProps,
                                   contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                                   targets: ITermTargetList, calcDefines: Array[IArticleDefine]): ITermTargetList = {
        return mergeListPendings(period, ruleset, contractTerms, positionTerms, targets, calcDefines)
    }
    private def addExternToTargets(period: IPeriod, ruleset: IBundleProps,
                                   contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                                   targets: ITermTargetList): ITermTargetList = {
        var targetsInit: ITermTargetList = targets.toArray

        var targetList = targets.foldLeft(targetsInit)((agr, item) => mergePendings(
            period, ruleset, contractTerms, positionTerms, agr, item))

        var targetSort = targetList.sortWith(TargetComparator(articleOrder.toArray))

        targetSort
    }

    private def addDefinesToTargets(period: IPeriod, ruleset: IBundleProps,
                                    contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                                    targets: ITermTargetList, defines: Array[IArticleDefine]) : ITermTargetList = {
        return defines.flatMap { x =>
            getTargetList(period, ruleset, conceptModel, contractTerms, positionTerms,
                targets.filter {t => t.article == x.code}, x.code, x.role).map { a => a }
        }
    }
    private def addTargetToCalculs(targets: ITermTargetList): ITermCalculList = {
        val targetsRets = targets.map(x => {
            val articleSpec = articleModel.find { a => (a.code == x.article) }
            new TermCalcul(x, articleSpec, getCalculFunc(conceptModel, x.concept))
        })
        return targetsRets.toArray
    }
    private def mergePendings(period: IPeriod, ruleset: IBundleProps,
                              contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                              targets: ITermTargetList, target: ITermTarget): ITermTargetList = {
        var resultList: ITermTargetList = targets.toArray

        val pendingsSpec = articlePaths.find { x => x._1.code == target.article }

        if (pendingsSpec.isDefined) {
            val pendingsPath = pendingsSpec.get._2
            resultList = pendingsPath.foldLeft(resultList)((agr, defs) => mergeItemPendings(
                period, ruleset, contractTerms, positionTerms, agr, defs))
        }
        return resultList
    }
    private def mergeItemPendings(period: IPeriod, ruleset: IBundleProps,
                                  contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                                  targets: ITermTargetList, articleDefs: IArticleDefine): ITermTargetList = {
        var resultList: ITermTargetList = targets.toArray

        var initTargets = targets.filter(x => x.article == articleDefs.code)

        val targetList = getTargetList(period, ruleset, conceptModel,
            contractTerms, positionTerms, initTargets, articleDefs.code, articleDefs.role)

        resultList = (resultList ++ targetList)

        resultList
    }
    private def mergeListPendings(period: IPeriod, ruleset: IBundleProps,
                                  contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                                  targets: ITermTargetList, calcDefines: Array[IArticleDefine]): ITermTargetList = {
        var resultList: ITermTargetList = targets.toArray

        val defineList = calcDefines.filter {x => targets.find { t => t.article == x.code}.isEmpty }

        val targetList = addDefinesToTargets(period, ruleset, contractTerms, positionTerms, targets, defineList)

        resultList = (resultList ++ targetList)

        resultList
    }
    private def getCalculFunc(conceptsModel: Array[IConceptSpec], concept: ConceptCode): Option[ResultFunc] = {
        var conceptSpec = conceptsModel.find(a => (a.code == concept))

        if (conceptSpec.isEmpty) {
           return Some(notFoundCalculFunc)
        }
        conceptSpec.get.resultDelegate
    }
    private def getTargetList(period: IPeriod, ruleset: IBundleProps, conceptsModel: Array[IConceptSpec],
                              contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                              targets: ITermTargetList, article: ArticleCode, concept: ConceptCode) : ITermTargetList = {
        val monthCode = MonthCode.get(period.code)
        val variant = VariantCode.get(1)

        val conceptSpec = conceptsModel.find {a => a.code.value == concept.value }
        if (conceptSpec.isEmpty) {
            val contract = ContractCode.getNew
            val position = PositionCode.getNew
            return Array[ITermTarget](new TermTarget(monthCode, contract, position, variant, article, concept))
        }
        return conceptSpec.get.defaultTargetList(article, period, ruleset, monthCode,
            contractTerms, positionTerms, targets, variant)
    }
    private def notFoundCalculFunc(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultError = NoResultFuncError(period, target)
        Array(Left(resultError))
    }
    private def TargetComparator(topoOrders: Array[ArticleTerm]) : (ITermTarget, ITermTarget) => Boolean = {
        val codeOrders: Array[ArticleCode] = topoOrders.map { t => t.code}
        (x: ITermTarget, y: ITermTarget) => {
            val xIndex = codeOrders.indexOf(x.article)

            val yIndex = codeOrders.indexOf(y.article)

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