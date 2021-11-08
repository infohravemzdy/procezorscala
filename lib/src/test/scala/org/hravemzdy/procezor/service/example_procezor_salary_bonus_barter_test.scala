package org.hravemzdy.procezor.service

import org.hravemzdy.legalios.factories.BundleProps
import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.legalios.service.types.Period
import org.hravemzdy.procezor.example.ExampleArticleConst
import org.hravemzdy.procezor.example.ExampleConceptConst
import org.hravemzdy.procezor.example.ExampleTermTarget
import org.hravemzdy.procezor.example.ServiceExample
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.service.types.{ArticleCode, ConceptCode, ContractCode, MonthCode, PositionCode, VariantCode}
import org.junit.runner.RunWith
import org.scalatest.funspec.AnyFunSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ServiceProcezorExampleWithSalaryBonusBarterTest extends AnyFunSpec {
    def getTargetsFromDb(period: IPeriod): Array[ITermTarget] = {
        val CONTRACT_CODE = 0
        val POSITION_CODE = 0

        val montCode = MonthCode.getWithPeriod(period)
        val contract = ContractCode.get(CONTRACT_CODE)
        val position = PositionCode.get(POSITION_CODE)
        val variant1 = VariantCode.get(1)
        val targets  = Array[ITermTarget](
            new ExampleTermTarget(montCode, contract, position, variant1,
                ArticleCode.get(ExampleArticleConst.ARTICLE_TIMESHT_WORKING.id),
                ConceptCode.get(ExampleConceptConst.CONCEPT_TIMESHT_WORKING.id)),
            new ExampleTermTarget(montCode, contract, position, variant1,
                ArticleCode.get(ExampleArticleConst.ARTICLE_PAYMENT_SALARY.id),
                ConceptCode.get(ExampleConceptConst.CONCEPT_AMOUNT_BASIS.id)),
            new ExampleTermTarget(montCode, contract, position, variant1,
                ArticleCode.get(ExampleArticleConst.ARTICLE_PAYMENT_BONUS.id),
                ConceptCode.get(ExampleConceptConst.CONCEPT_AMOUNT_FIXED.id)),
            new ExampleTermTarget(montCode, contract, position, variant1,
                ArticleCode.get(ExampleArticleConst.ARTICLE_PAYMENT_BARTER.id),
                ConceptCode.get(ExampleConceptConst.CONCEPT_AMOUNT_FIXED.id)),
        )
        return targets
    }

    describe("Procezor Example - Salary-Bonus-Barter") {
        val testService = ServiceExample.getNew()

        val testVersion = testService.version
        it("service version should return value = 100") {
            assert(100 == testVersion.value)
        }
        val testPeriod = Period.getWithYearMonth(2021, 1)
        it("service period should return value = 202101") {
            assert(202101 == testPeriod.code)
        }
        val testLegal: IBundleProps = BundleProps.empty(testPeriod)

        val factoryArticleCode = ArticleCode.get(ExampleArticleConst.ARTICLE_TIMESHT_WORKING.id)

        val factoryArticle = testService.getArticleSpec(factoryArticleCode, testPeriod, testVersion)
        it(s"article should have value = ${ExampleArticleConst.ARTICLE_TIMESHT_WORKING.id}") {
            assert(factoryArticle != null, "Error getting article - article is not valid")
            assert(0 != factoryArticle.code.value, "Error getting article - article is not valid")
        }
        val factoryConceptCode = ConceptCode.get(ExampleConceptConst.CONCEPT_TIMESHT_WORKING.id)

        val factoryConcept = testService.getConceptSpec(factoryConceptCode, testPeriod, testVersion)
        it(s"concept should have value = ${ExampleConceptConst.CONCEPT_TIMESHT_WORKING.id}") {
            assert(factoryConcept != null, "Error getting concept - concept is not valid")
            assert(0 != factoryConcept.code.value, "Error getting concept - concept is not valid")
        }
        val initService = testService.initWithPeriod(testPeriod)
        it("initiating service should return true") {
            assert(initService == true, "Error initiating service - init was failed")
        }
        val restService = testService.getResults(testPeriod, testLegal, getTargetsFromDb(testPeriod))

        restService.zipWithIndex.foreach(result_index => {
            val (result, index) = result_index
            result match {
                case Right(value) => {
                    val articleSymbol = value.articleDescr()
                    val conceptSymbol = value.conceptDescr()
                    println(s"Index: $index, ART: $articleSymbol, CON: $conceptSymbol")
                }
                case Left(error) => {
                    val articleSymbol = error.articleDescr()
                    val conceptSymbol = error.conceptDescr()
                    val errorValue = error.description()
                    println(s"Index: $index, ART: $articleSymbol, CON: $conceptSymbol, Error: $errorValue")
                }
            }
        })

        val testArticles = Array[Int](80001, 80003, 80004, 80002, 80006, 80007, 80010, 80012, 80008, 80009, 80011, 80013)
        it(s"get result should return array of length ${testArticles.length}") {
            assert(testArticles.length == restService.length, "Error getting results - get result was failed")
        }
        val restArticles = restService.filter(x => x.isRight).map(x => (x.map(a => a.article.value).getOrElse(0)))
        it("get result should return array of articles") {
            assert(testArticles.corresponds(restArticles)((x,y) => x==y),
                "Error getting results same order array - get result was failed"
            )
        }
    }
}
