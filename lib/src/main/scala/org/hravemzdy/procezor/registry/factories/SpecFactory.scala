package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.interfaces.ISpecDefine
import org.hravemzdy.procezor.registry.factories.FactoryTypes.CODE
import org.hravemzdy.procezor.registry.providers.ISpecProvider
import org.hravemzdy.procezor.service.types.VersionCode

import java.lang.reflect.Modifier
import org.reflections.Reflections

import scala.reflect.ClassTag

object FactoryTypes {
    type CODE = Int
}

trait ISpecFactory[P <: ISpecProvider[S, C], S <: ISpecDefine[C], C <: ISpecCode] {
    def getSpec(code: C, period: IPeriod, version: VersionCode): S
    def getSpecList(period: IPeriod, version: VersionCode)(implicit tag: ClassTag[S]): Array[S]
}

abstract class SpecFactory[P <: ISpecProvider[S, C], S <: ISpecDefine[C], C <: ISpecCode]() extends ISpecFactory[P, S, C] {

    protected val providers: Map[CODE, P]
    protected val notFoundProvider: P
    protected val notFoundSpec: S

    override def getSpec(code: C, period: IPeriod, version: VersionCode): S = {
        val maybeProvider = getProvider(code, notFoundProvider)
        maybeProvider match {
            case Some(provider) => provider.getSpec(period, version)
            case None => notFoundSpec
        }
    }
    override def getSpecList(period: IPeriod, version: VersionCode)(implicit tag: ClassTag[S]): Array[S] = {
        providers.map(x => (x._2.getSpec(period, version))).toArray
    }

    private def getProvider(code: C, defProvider: P): Option[P] = {
        val maybeProvider = providers.get(code.value)
        return maybeProvider match {
            case Some(provider) => Some(provider)
            case None => Some(defProvider)
        }
    }
}

object SpecFactory {
    def  buildProvidersFromAssembly[P <: ISpecProvider[S, C], S <: ISpecDefine[C], C <: ISpecCode](namespace: String)(implicit tag: ClassTag[P]): Map[CODE, P] = {
        val providerType: Class[P] = tag.runtimeClass.asInstanceOf[Class[P]]

        val reflections = new Reflections(namespace)

        val definedTags = reflections.getSubTypesOf(providerType)
          .toArray.map(x => ClassTag(x.asInstanceOf[Class[_]]))

        val definedProv = definedTags.filter(x => isValidType[P,S,C](x) && hasValidConstructor[P,S,C](x))
          .map(x => x.runtimeClass.getDeclaredConstructor().newInstance().asInstanceOf[P])

        definedProv.map(p => (p.code.value, p)).toMap
}

    def  isValidType[P <: ISpecProvider[S, C], S <: ISpecDefine[C], C <: ISpecCode](classTag: ClassTag[_])(implicit ct: ClassTag[P]): Boolean = {
        val assignable = ct.runtimeClass.isAssignableFrom(classTag.runtimeClass)
        val nonInterface = classTag.runtimeClass.isInterface == false && Modifier.isAbstract(classTag.runtimeClass.getModifiers())==false
        (assignable && nonInterface)
    }

    def hasValidConstructor[P <: ISpecProvider[S, C], S <: ISpecDefine[C], C <: ISpecCode](classTag: ClassTag[_]): Boolean = {
        val parameterlessConstructor = classTag.runtimeClass.getConstructors()
          .find(constructor =>  constructor.getParameterCount() == 0)
        parameterlessConstructor != null
    }
}
