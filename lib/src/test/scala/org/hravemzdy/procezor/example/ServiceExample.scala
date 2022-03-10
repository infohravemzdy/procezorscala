package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.service.ServiceProcezor
import org.hravemzdy.procezor.service.types.{ArticleCode, VersionCode}

class ServiceExample() extends ServiceProcezor(ServiceExample.TEST_VERSION, ServiceExample.TEST_CALCS_ARTICLE) {
    override def buildArticleFactory(): Boolean = {
        this.articleFactory = new ExampleArticleFactory()

        true
    }

    override def buildConceptFactory(): Boolean = {
        this.conceptFactory = new ExampleConceptFactory()

        true
    }
}

object ServiceExample {
    private val TEST_VERSION: VersionCode = VersionCode.get(100)
    private val TEST_FINAL_ARTICLE = ExampleArticleConst.ARTICLE_INCOME_NETTO

    private val TEST_CALCS_ARTICLE: Array[ArticleCode] = Array[ArticleCode](ArticleCode.get(TEST_FINAL_ARTICLE.id))

    def getNew(): ServiceExample = {
        val service = new ServiceExample()
        val buildSuccess = service.buildFactories()
        if (buildSuccess == false) {
            println(s"Version: ${service.version}, build factories failed")
        }
        service
    }

}
