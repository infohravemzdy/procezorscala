package org.hravemzdy.procezor.registry

import org.apache.commons.math3.optim.MaxIter
import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleDefine, ArticleTerm}

import scala.collection.mutable


case class ArticleEdge(val start: ArticleTerm, val stops:ArticleTerm)

object DependencyGraph {
    def DefineComparator(topoOrders: Array[ArticleTerm]): (IArticleDefine, IArticleDefine) => Boolean = {
        val codeOrders: Array[ArticleCode] = topoOrders.map { t => t.code }
        (x: IArticleDefine, y: IArticleDefine) => {
            val xIndex = codeOrders.indexOf(x.code)

            val yIndex = codeOrders.indexOf(y.code)

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
    private def getArticleTerm(article: ArticleCode, articlesModel: Array[IArticleSpec]): ArticleTerm = {
        articlesModel.find { m => (m.code == article) } match {
            case Some(spec) => spec.term()
            case None => ArticleTerm.zero
        }
    }
    private def getArticleDefs(article: ArticleCode, articlesModel: Array[IArticleSpec]): IArticleDefine = {
         articlesModel.find(m => m.code == article) match {
            case Some(spec) => spec.defs()
            case None => new ArticleDefine()
        }
    }
    def initGraphModel(articlesModel: Array[IArticleSpec], conceptsModel: Array[IConceptSpec]): (Array[ArticleTerm], Map[ArticleTerm, Array[IArticleDefine]]) = {
        val vertModel: Array[ArticleTerm] = createVertModel(articlesModel)
        val edgeModel: Array[ArticleEdge] = createEdgeModel(articlesModel, conceptsModel)
        val pendModel: Array[ArticleEdge] = createPendModel(articlesModel, conceptsModel)

        val order = createTopoModel(vertModel, edgeModel)
        val paths = createPathModel(articlesModel, vertModel, pendModel, order)

        (order, paths)
    }
    private def createVertModel(articlesModel: Array[IArticleSpec]): Array[ArticleTerm] = {
        articlesModel.map(a => a.term()).sorted
    }
    private def createEdgeModel(articlesModel: Array[IArticleSpec], conceptsModel: Array[IConceptSpec]): Array[ArticleEdge] = {
        var init: Array[ArticleEdge] = Array[ArticleEdge]()

        articlesModel.foldLeft(init)((agr, x) => mergeEdges(articlesModel, conceptsModel, agr, x))
            .sortWith((a, b) => { if (a.start == b.start) a.stops < b.stops else a.start < b.start })
    }
    private def createPendModel(articlesModel: Array[IArticleSpec], conceptsModel: Array[IConceptSpec]): Array[ArticleEdge] = {
        var init: Array[ArticleEdge] = Array[ArticleEdge]()

        articlesModel.foldLeft(init)((agr, x) => mergePends(articlesModel, conceptsModel, agr, x))
          .sortWith((a, b) => { if (a.start == b.start) a.stops < b.stops else a.start < b.start })
    }
    private def createPathModel(articlesModel: Array[IArticleSpec], vertModel: Array[ArticleTerm], edgeModel: Array[ArticleEdge], vertOrder: Array[ArticleTerm]): Map[ArticleTerm, Array[IArticleDefine]] = {
        val mapPairs = vertModel.map(x => (x, mergePaths(articlesModel, edgeModel, vertOrder, x)))
        return mapPairs.toMap
    }
    private def mergeEdges(articlesModel: Array[IArticleSpec], conceptsModel: Array[IConceptSpec], agr: Array[ArticleEdge],  article: IArticleSpec): Array[ArticleEdge] = {
        var result = agr.toSet

        var concept = conceptsModel.find(c => (c.code == article.role))

        if (concept.isDefined) {
            result = (article.sums.map(s => ArticleEdge(article.term(), getArticleTerm(s, articlesModel))) ++ result).toSet

            result = (concept.get.path.map( p => ArticleEdge(getArticleTerm(p, articlesModel), article.term())) ++ result).toSet
        }
        result.toArray
    }
    private def mergePends(articlesModel: Array[IArticleSpec], conceptsModel: Array[IConceptSpec], agr: Array[ArticleEdge],  article: IArticleSpec): Array[ArticleEdge] = {
        var result = agr.toSet

        var concept = conceptsModel.find(c => (c.code == article.role))

        if (concept.isDefined) {
            result = (concept.get.path.map( p => ArticleEdge(getArticleTerm(p, articlesModel), article.term())) ++ result).toSet
        }
        result.toArray
    }
    private def mergePaths(articlesModel: Array[IArticleSpec], edgeModel: Array[ArticleEdge], vertOrder: Array[ArticleTerm], article: ArticleTerm): Array[IArticleDefine] = {
        val articleInit: Array[IArticleDefine] = edgeModel.filter(e => e.stops == article).map(e => getArticleDefs(e.start.code, articlesModel))
        val articlePath = articleInit.foldLeft(articleInit)((agr, x) => mergeVert(articlesModel, edgeModel, agr, x))
        articlePath.distinct.sortWith(DefineComparator(vertOrder.toArray))
    }
    private def mergeVert(articlesModel: Array[IArticleSpec], edgeModel: Array[ArticleEdge], resultVert: Array[IArticleDefine], defs: IArticleDefine): Array[IArticleDefine] = {
        val resultInit: Array[IArticleDefine] = edgeModel.
            filter(e => (e.stops == defs.term())).
            map(e => getArticleDefs(e.start.code, articlesModel))
        val resultList = resultInit.foldLeft(resultInit)((agr, x) => mergeVert(articlesModel, edgeModel, agr, x))
        (resultVert ++ resultList :+ defs)
    }
    private def createTopoModel(vertModel: Array[ArticleTerm], edgeModel: Array[ArticleEdge]): Array[ArticleTerm] = {
        var articlesOrder = Array[ArticleTerm]()

        var degrees = vertModel.map(x => (x, edgeModel.count(e => (e.stops == x)))).toMap

        val queues = mutable.Queue[ArticleTerm]()
        queues.addAll(degrees.filter(x => (x._2 == 0)).keys.toArray.sorted)

        var index = 0
        while (queues.nonEmpty) {
            index += 1
            val article = queues.dequeue()
            articlesOrder = articlesOrder :+ article
            val paths = edgeModel.filter(x => (x.start == article)).map(x => x.stops)
            paths.foreach(p => {
                degrees = degrees.updatedWith(p)(x => x match {
                    case Some(value) => Some(value - 1)
                    case None => None
                })
                val current = degrees.get(p)
                if (current.isDefined && current.get == 0) {
                    queues.enqueue(p)
                }
            })
        }
        val modelLength = vertModel.length
        if (index != modelLength) {
            println(s"CreateTopoModel, build graph failed: ${index}<>${modelLength}")
            return Array[ArticleTerm]()
        }
        articlesOrder
    }
}