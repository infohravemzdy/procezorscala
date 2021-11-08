package org.hravemzdy.procezor.registry

import org.apache.commons.math3.optim.MaxIter
import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ArticleDefine

import scala.collection.mutable


case class ArticleEdge(val start: ArticleCode, val stops:ArticleCode)

object DependencyGraph {
    def DefineComparator(topoOrders: Array[ArticleCode]): (IArticleDefine, IArticleDefine) => Boolean = {
        (x: IArticleDefine, y: IArticleDefine) => {
            val xIndex = topoOrders.indexOf(x.code)

            val yIndex = topoOrders.indexOf(y.code)

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
    private def getArticleDefs(article: ArticleCode, articlesModel: Array[IArticleSpec]): IArticleDefine = {
         articlesModel.find(m => m.code == article) match {
            case Some(spec) => spec.defs()
            case None => new ArticleDefine()
        }
    }
    def initGraphModel(articlesModel: Array[IArticleSpec], conceptsModel: Array[IConceptSpec]): (Array[ArticleCode], Map[ArticleCode, Array[IArticleDefine]]) = {
        val vertModel: Array[ArticleCode] = createVertModel(articlesModel)
        val edgeModel: Array[ArticleEdge] = createEdgeModel(articlesModel, conceptsModel)
        val pendModel: Array[ArticleEdge] = createPendModel(articlesModel, conceptsModel)

        val order = createTopoModel(vertModel, edgeModel)
        val paths = createPathModel(articlesModel, vertModel, pendModel, order)

        (order, paths)
    }
    private def createVertModel(articlesModel: Array[IArticleSpec]): Array[ArticleCode] = {
        articlesModel.map(a => a.code).sorted
    }
    private def createEdgeModel(articlesModel: Array[IArticleSpec], conceptsModel: Array[IConceptSpec]): Array[ArticleEdge] = {
        var init: Array[ArticleEdge] = Array[ArticleEdge]()

        articlesModel.foldLeft(init)((agr, x) => mergeEdges(conceptsModel, agr, x))
            .sortWith((a, b) => { if (a.start == b.start) a.stops < b.stops else a.start < b.start })
    }
    private def createPendModel(articlesModel: Array[IArticleSpec], conceptsModel: Array[IConceptSpec]): Array[ArticleEdge] = {
        var init: Array[ArticleEdge] = Array[ArticleEdge]()

        articlesModel.foldLeft(init)((agr, x) => mergePends(conceptsModel, agr, x))
          .sortWith((a, b) => { if (a.start == b.start) a.stops < b.stops else a.start < b.start })
    }
    private def createPathModel(articlesModel: Array[IArticleSpec], vertModel: Array[ArticleCode], edgeModel: Array[ArticleEdge], vertOrder: Array[ArticleCode]): Map[ArticleCode, Array[IArticleDefine]] = {
        return vertModel.map(x => (x, mergePaths(articlesModel, edgeModel, vertOrder, x))).toMap
    }
    private def mergeEdges(conceptsModel: Array[IConceptSpec], agr: Array[ArticleEdge],  article: IArticleSpec): Array[ArticleEdge] = {
        var result = agr.toSet

        var concept = conceptsModel.find(c => (c.code == article.role))

        if (concept.isDefined) {
            result = (article.sums.map(s => ArticleEdge(article.code, s)) ++ result).toSet

            result = (concept.get.path.map( p => ArticleEdge(p, article.code)) ++ result).toSet
        }
        result.toArray
    }
    private def mergePends(conceptsModel: Array[IConceptSpec], agr: Array[ArticleEdge],  article: IArticleSpec): Array[ArticleEdge] = {
        var result = agr.toSet

        var concept = conceptsModel.find(c => (c.code == article.role))

        if (concept.isDefined) {
            result = (concept.get.path.map( p => ArticleEdge(p, article.code)) ++ result).toSet
        }
        result.toArray
    }
    private def mergePaths(articlesModel: Array[IArticleSpec], edgeModel: Array[ArticleEdge], vertOrder: Array[ArticleCode], article: ArticleCode): Array[IArticleDefine] = {
        val articleInit: Array[IArticleDefine] = edgeModel.filter(e => e.stops == article).map(e => getArticleDefs(e.start, articlesModel))
        val articlePath = articleInit.foldLeft(articleInit)((agr, x) => mergeVert(articlesModel, edgeModel, agr, x))
        articlePath.distinct.sortWith(DefineComparator(vertOrder.toArray))
    }
    private def mergeVert(articlesModel: Array[IArticleSpec], edgeModel: Array[ArticleEdge], resultVert: Array[IArticleDefine], defs: IArticleDefine): Array[IArticleDefine] = {
        val resultInit: Array[IArticleDefine] = edgeModel.
            filter(e => (e.stops == defs.code)).
            map(e => getArticleDefs(e.start, articlesModel))
        val resultList = resultInit.foldLeft(resultInit)((agr, x) => mergeVert(articlesModel, edgeModel, agr, x))
        (resultVert ++ resultList :+ defs)
    }
    private def createTopoModel(vertModel: Array[ArticleCode], edgeModel: Array[ArticleEdge]): Array[ArticleCode] = {
        var articlesOrder = Array[ArticleCode]()

        var degrees = vertModel.map(x => (x, edgeModel.count(e => (e.stops == x)))).toMap

        val queues = mutable.Queue[ArticleCode]()
        queues.addAll(degrees.filter(x => (x._2 == 0)).map(x => x._1).toArray.sorted)

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
        if (index != vertModel.length) {
            return Array[ArticleCode]()
        }
        articlesOrder
    }
}