package org.hravemzdy.procezor.interfaces

trait ISpecSeqs extends ICodeValue[Int] with Ordered[ISpecSeqs] {
  override def compare(that: ISpecSeqs): Int = {
    this.value.compare(that.value)
  }
}
