package example

object Hello extends Greeting with App {
  println(greeting)
}

trait Greeting {
  type BillingItems = (Money, Amount, StateCode) => Money

  def calculateItem(money: Money)(amount: Amount): Money = ???

  def applyTaxes(taxTable: TaxTable)(taxCode: TaxCode)(money:Money): Money = ???

  def applyDiscount(money: Money)(discountTable: DiscountTable): Money = ???

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
  def flatMapFunction[A,B,X](fa: X => A, f: A => X => B): X => B =
    (x: X) => f(fa(x)) (x)

  val discountTable : DiscountTable = ???
  val taxTable: TaxTable = ???

  def applyTaxWithTable: TaxCode => Money => Money = applyTaxes(taxTable)
  val applyDiscountWithTable: Money => Money = applyDiscount(_)(discountTable)

  def applyReductionsWithTaxes: TaxCode => Money => Money = (taxCode:TaxCode) =>
    applyDiscountWithTable andThen applyTaxWithTable(taxCode)

  val state = applyReductionsWithTaxes("TX")(3)
  val items: (Money, Amount) => Money = calculateItem(_)(_)
}
