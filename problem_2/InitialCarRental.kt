// Basic program that calculates a statement of a customer's charges at a car rental store.
//
// A customer can have multiple "Rental"s and a "Rental" has one "Car"
// As an ASCII class diagram:
//          Customer 1 ----> * Rental
//          Rental   * ----> 1 Car
//
// The charges depend on how long the car is rented and the type of the car (economy, muscle or supercar)
//
// The program also calculates frequent renter points.
//
//
// Refactor this class how you would see fit.
//
// The actual code is not that important, as much as its structure. You can even use "magic" functions (e.g. foo.sort()) if you want



sealed class PriceCode(
    val price: Double,
    val initialDays: Int = 0,
    val initialPrice: Double = 0.0,
) {
    data object Muscle : PriceCode(
        price = 50.0,
        initialDays = 3,
        initialPrice = 200.0,
    )

    data object Economy : PriceCode(
        price = 30.0,
        initialDays = 2,
        initialPrice = 80.0,
    )

    data object Supercar : PriceCode(price = 200.0)
}

data class Car(
    val title: String,
    var priceCode: PriceCode,
)

data class Rental(
    val car: Car,
    val daysRented: Int,
) {
    init {
        require(daysRented > 0) {
            "Rented days must be greater than 0"
        }
    }
}

data class Customer(val name: String) {
    private var _rentals = mutableListOf<Rental>()
    val rentals: List<Rental>
        get() = _rentals.toList()

    fun addRental(rental: Rental) {
        _rentals.add(rental)
    }
}

class BillingManager {

    fun createBillingStatement(customer: Customer): String {
        var totalAmount = 0.0
        var frequentRenterPoints = 0

        val result = StringBuilder("Rental Record for ${customer.name}\n")

        for (rental in customer.rentals) {
            val thisAmount = calculateRentalCost(rental.car.priceCode, rental.daysRented)
            frequentRenterPoints += calculateFrequentRenterPoints(rental.car.priceCode, rental.daysRented)

            result.append("\t${rental.car.title}\t$thisAmount\n")
            totalAmount += thisAmount
        }

        result.apply {
            append("Final rental payment owed $totalAmount\n")
            append("You received an additional $frequentRenterPoints frequent customer points")
        }

        return result.toString()
    }

    private fun calculateRentalCost(priceCode: PriceCode, daysRented: Int) =
        maxOf(0, daysRented - priceCode.initialDays) * priceCode.price + priceCode.initialPrice

    private fun calculateFrequentRenterPoints(priceCode: PriceCode, daysRented: Int) =
        if (priceCode == PriceCode.Supercar && daysRented > 1) 2 else 1
}

fun main() {
    val rental1 = Rental(Car("Mustang", PriceCode.Muscle), 5)
    val rental2 = Rental(Car("Lambo", PriceCode.Supercar), 20)
    val customer = Customer("Liviu")

    customer.addRental(rental1)
    customer.addRental(rental2)

    println(BillingManager().createBillingStatement(customer))
}
