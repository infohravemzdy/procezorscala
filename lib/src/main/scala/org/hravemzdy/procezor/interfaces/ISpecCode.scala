package org.hravemzdy.procezor.interfaces

trait ISpecCode extends ICodeValue[Int] with Ordered[ISpecCode] {
  override def compare(that: ISpecCode): Int = {
     this.value.compare(that.value)
  }
}