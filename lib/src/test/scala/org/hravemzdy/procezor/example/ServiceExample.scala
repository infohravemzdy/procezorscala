package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.service.ServiceProcezor
import org.hravemzdy.procezor.service.types.ArticleDefine
import org.hravemzdy.procezor.service.types.VersionCode

class ServiceExample() extends ServiceProcezor(ServiceExample.TEST_VERSION, ServiceExample.TEST_FINAL_DEFS) {
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
    private val TEST_FINAL_CONCEPT = ExampleConceptConst.CONCEPT_INCOME_NETTO

    private val TEST_FINAL_DEFS: IArticleDefine = ArticleDefine.fromInt(TEST_FINAL_ARTICLE.id, TEST_FINAL_CONCEPT.id)

    def getNew(): ServiceExample = {
        val service = new ServiceExample()
        service.buildFactories()
        service
    }

}
