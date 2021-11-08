package org.hravemzdy.procezor.example

object ExampleArticleConst extends Enumeration {
    type ExampleArticleConst = Value

    val ARTICLE_TIMESHT_WORKING = Value(80001)
    val ARTICLE_PAYMENT_SALARY  = Value(80002)
    val ARTICLE_PAYMENT_BONUS   = Value(80003)
    val ARTICLE_PAYMENT_BARTER  = Value(80004)
    val ARTICLE_ALLOWCE_HOFFICE = Value(80005)
    val ARTICLE_HEALTH_INSBASE  = Value(80006)
    val ARTICLE_SOCIAL_INSBASE  = Value(80007)
    val ARTICLE_HEALTH_INSPAYM  = Value(80008)
    val ARTICLE_SOCIAL_INSPAYM  = Value(80009)
    val ARTICLE_TAXING_ADVBASE  = Value(80010)
    val ARTICLE_TAXING_ADVPAYM  = Value(80011)
    val ARTICLE_INCOME_GROSS    = Value(80012)
    val ARTICLE_INCOME_NETTO    = Value(80013)

    def getArticleSymbol(item: Int): String = {
        val optionConst =  ExampleArticleConst.values.find(x => x.id == item)
        optionConst match {
            case Some(exists) => s"${exists}"
            case None => s"ArticleCode for ${item}"
        }
    }
}

object ExampleConceptConst extends Enumeration {
    type ExampleConceptConst = Value

    val CONCEPT_TIMESHT_WORKING = Value(80001)
    val CONCEPT_AMOUNT_BASIS    = Value(80002)
    val CONCEPT_AMOUNT_FIXED    = Value(80003)
    val CONCEPT_HEALTH_INSBASE  = Value(80006)
    val CONCEPT_SOCIAL_INSBASE  = Value(80007)
    val CONCEPT_HEALTH_INSPAYM  = Value(80008)
    val CONCEPT_SOCIAL_INSPAYM  = Value(80009)
    val CONCEPT_TAXING_ADVBASE  = Value(80010)
    val CONCEPT_TAXING_ADVPAYM  = Value(80011)
    val CONCEPT_INCOME_GROSS    = Value(80012)
    val CONCEPT_INCOME_NETTO    = Value(80013)

    def getConceptSymbol(item: Int): String = {
        val optionConst =  ExampleConceptConst.values.find(x => x.id == item)
        optionConst match {
            case Some(exists) => s"${exists}"
            case None => s"ConceptCode for ${item}"
        }
    }
}

