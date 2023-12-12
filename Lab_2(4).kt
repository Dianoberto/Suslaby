data class Customer(
    val name: String,
    val email: String,
    val isWholesale: Boolean
)

val customers = mutableListOf<Customer>()
val wholesaleCustomers = mutableListOf<Customer>()
val retailCustomers = mutableListOf<Customer>()

fun addCustomer(customer: Customer) {
    customers.add(customer)
    if (customer.isWholesale) {
        wholesaleCustomers.add(customer)
    } else {
        retailCustomers.add(customer)
    }
}
fun showCustomers() {
    if (customers.isEmpty()) {
        println("No customers have been added yet.")
    } else {
        println("List of Customers:")
        customers.forEachIndexed { index, customer ->
            println("Customer ${index + 1}: ${customer.name}, Email: ${customer.email}, Wholesale: ${customer.isWholesale}")
        }
    }
}

fun showWholesaleCustomers() {
    if (wholesaleCustomers.isEmpty()) {
        println("No wholesale customers have been added yet.")
    } else {
        println("List of Wholesale Customers:")
        wholesaleCustomers.forEachIndexed { index, customer ->
            println("Wholesale Customer ${index + 1}: ${customer.name}, Email: ${customer.email}")
        }
    }
}

fun showRetailCustomers() {
    if (retailCustomers.isEmpty()) {
        println("No retail customers have been added yet.")
    } else {
        println("List of Retail Customers:")
        retailCustomers.forEachIndexed { index, customer ->
            println("Retail Customer ${index + 1}: ${customer.name}, Email: ${customer.email}")
        }
    }
}

fun notifyWholesaleCustomers(message: String) {
    wholesaleCustomers.forEach { customer ->
        println("Message sent to ${customer.name} ${customer.email}: $message")
    }
}

data class SeedVariety(
    val name: String,
    val plantType: String,
    val yield: Double,
    val frostResistance: String,
    val adaptation: String,
    val ripeningPeriod: String,
    val price: Double
)
val seedVarieties = mutableListOf<SeedVariety>()
// You can organize seed varieties of lists by plant type or other criteria if needed.
val treesVarieties = mutableListOf<SeedVariety>()
val bushVarieties = mutableListOf<SeedVariety>()
val flowerVarieties = mutableListOf<SeedVariety>()
fun addSeedVariety(variety: SeedVariety) {
    seedVarieties.add(variety)
    // Add the variety to specific lists by plant type (e.g., tomato or flower)
    when (variety.plantType) {
        "Tree" -> treesVarieties.add(variety)
        "Bush" -> bushVarieties.add(variety)
        "Flower" -> flowerVarieties.add(variety)
    }
}
fun showSeedVarieties(plantType: String) {
    val varietiesToDisplay = when (plantType) {
        "Trees" -> treesVarieties
        "Bush" -> bushVarieties
        "Flower" -> flowerVarieties
        // Add more cases for other plant types
        else -> seedVarieties // Display all varieties if plantType is not specified
    }

    if (varietiesToDisplay.isEmpty()) {
        println("No seed varieties have been added yet.")
    } else {
        println("List of Seed Varieties for $plantType:")
        varietiesToDisplay.forEachIndexed { index, variety ->
            println("Variety ${index + 1}: ${variety.name}, ${variety.plantType}, ${variety.yield}, ${variety.frostResistance}, ${variety.adaptation}, ${variety.ripeningPeriod}, ${variety.price}")
            // Print other variety details as needed
        }
    }
}
fun searchSeedVarieties(criteria: String) {
    val results = seedVarieties.filter { variety ->
        variety.name.contains(criteria, ignoreCase = true) ||
                variety.plantType.contains(criteria, ignoreCase = true)||
                variety.frostResistance.contains(criteria, ignoreCase = true) ||
                variety.ripeningPeriod.contains(criteria, ignoreCase = true) ||
                variety.adaptation.contains(criteria, ignoreCase = true)
        // Add more search criteria here
    }

    if (results.isEmpty()) {
        println("No matching seed varieties found.")
    } else {
        println("Matching Seed Varieties:")
        results.forEachIndexed { index, variety ->
            println("Variety ${index + 1}: ${variety.name}")
            // Print other variety details as needed
        }
    }
}

fun main() {
    val presetCustomer1 = Customer("Alice", "alice@gmail.com", isWholesale = true)
    val presetCustomer2 = Customer("Bob", "bobby@gmail.com", isWholesale = false)
    val presetCustomer3 = Customer("Charlie", "charlie@gmail.com", isWholesale = true)
    val presetCustomer4 = Customer("Bond", "jamesBond@gmail.com",  isWholesale = false)
    val presetCustomer5 = Customer("Agate Christ", "theCatty@gmail.com",  isWholesale = true)
// Add the preset customers to the appropriate lists
    customers.add(presetCustomer1)
    customers.add(presetCustomer2)
    customers.add(presetCustomer3)
    customers.add(presetCustomer4)
    customers.add(presetCustomer5)

// Separate wholesale and retail customers
    wholesaleCustomers.addAll(customers.filter { it.isWholesale })
    retailCustomers.addAll(customers.filter { !it.isWholesale })

    val presetSeed1 = SeedVariety("Apple","Tree", 60.0, "Good resist", "Medium adaptation", "Medium ripe", 5.0)
    val presetSeed2 = SeedVariety("Baobab","Tree", 7.0, "Bad resist", "Bad adaptation", "Late ripe", 25.0)
    val presetSeed3 = SeedVariety("Elder","Bush", 55.0, "Good resist", "Good adaptation", "Medium ripe", 2.0)
    val presetSeed4 = SeedVariety("Watermelon","Bush", 25.0, "Bad resist", "Bad adaptation", "Early ripe", 10.0)
    val presetSeed5 = SeedVariety("Daisy","Flower", 77.0, "Medium resist", "Good adaptation", "Early ripe", 1.5)
    val presetSeed6 = SeedVariety("Sunflower","Flower", 80.0, "Medium resist", "Medium adaptation", "Late ripe", 3.25)

    seedVarieties.addAll(listOf(presetSeed1, presetSeed2, presetSeed3, presetSeed4, presetSeed5, presetSeed6))

    treesVarieties.addAll(seedVarieties.filter { it.plantType == "Tree" })
    bushVarieties.addAll(seedVarieties.filter { it.plantType == "Bush" })
    flowerVarieties.addAll(seedVarieties.filter { it.plantType == "Flower" })
    while (true) {
        println("-----Institute of Plant Breeding Menu:-----")
        println("1. Add Seed Variety")
        println("2. Add Customer")
        println("3. Search Seed Varieties")
        println("4. Notify Wholesale Customers")
        println("5.Show seeds list")
        println("6.Show customers list")
        println("7.Show wholesale customers list")
        println("8.Show retail customers list")
        println("9. Exit")
        print("Enter your choice: ")

        when (readLine()) {
            "1" -> {
                // Add a new seed variety
                println("Enter variety name: ")
                val name = readLine() ?: ""
                print("Enter plant type: ")
                val plantType = readLine() ?: ""
                print("Enter yield: ")
                val yield = readLine()?.toDoubleOrNull() ?: 0.0
                print("Enter frost resistance: ")
                val frostResistance = readLine() ?: ""
                print("Enter adaptation: ")
                val adaptation = readLine() ?: ""
                print("Enter ripening period: ")
                val ripeningPeriod = readLine() ?: ""
                print("Enter a price: ")
                val price = readLine()?.toDoubleOrNull() ?: 0.0

                val variety = SeedVariety(name, plantType, yield, frostResistance, adaptation, ripeningPeriod, price)
                addSeedVariety(variety)
            }
            "2" -> {
                // Add a new customer
                println("Enter new customer name: ")
                val name = readLine() ?: ""
                print("Enter customer email: ")
                val email = readLine() ?: ""
                print("Is this a wholesale customer (true/false): ")
                // Read and parse the user input for isWholesale
                val isWholesaleInput = readLine()
                val isWholesale = isWholesaleInput?.toBoolean() ?: false

                val customer = Customer(name, email, isWholesale)
                addCustomer(customer)
                // Collect input from the user and call addCustomer function
            }
            "3" -> {
                // Search seed varieties
                println("Enter search criteria: ")
                val searchCriteria = readLine() ?: ""

                searchSeedVarieties(searchCriteria) // Call the searchSeedVarieties function directly
            }
            "4" -> {
                // Notify wholesale customers
                println("Enter notification message: ")
                val notificationMessage = readLine() ?: ""

                notifyWholesaleCustomers(notificationMessage)
            }
            "5" -> {
                // Show Seed Varieties
                println("Enter plant type (leave empty to show all): ")
                val plantType = readLine() ?: ""

                showSeedVarieties(plantType)
            }
            "6" -> {
                // Show Customers
                showCustomers()
            }
            "7" -> {
                // Show Wholesale customers
                showWholesaleCustomers()
            }
            "8" -> {
                // Show Retail Customers
                showRetailCustomers()
            }
            "9" -> return
            else -> println("Invalid choice. Please enter a valid option.")
        }
            print("1. Return to Menu\n2. Exit\nEnter your choice: ")
                when (readLine()) {
                "1" -> continue
                "2" -> return
                else -> return
            }
        }
    }
