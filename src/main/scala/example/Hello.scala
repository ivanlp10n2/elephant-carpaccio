package example

object Hello extends Greeting with App {
  println(greeting)
}

trait Greeting {
  type BillingItems = (Money, Amount, StateCode) => Money

  def calculateItem: (Money, Amount) => Money = ???

  def applyTaxes: TaxTable => TaxCode => Money => Money = ???

  def applyDiscount: Money => DiscountTable => Money = ???

  type TaxTable = Map[TaxCode, ChargePercent]
  type DiscountTable = Map[Money, DiscountPercent]

  type Money = Int
  type Amount = Int
  type StateCode = String
  type DiscountPercent = Float
  type ChargePercent = Float
  type TaxCode = String
  type TotalPrice = Int

}
object a extends Greeting{
  val discountTable : DiscountTable = ???
  def applyDiscountWithTable: Money => Money = applyDiscount(_)(discountTable)

  val taxTable: TaxTable = ???
  def applyTaxWithTable: TaxCode => Money => Money = applyTaxes(taxTable)

  def pipeline: TaxCode => Money => Money =
    applyTaxWithTable andThen applyDiscountWithTable
}
