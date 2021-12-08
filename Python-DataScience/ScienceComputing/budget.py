class Category:
    def __init__(self, description):
        self.description = description
        self.ledger = []
        self.total_funds = 0.0

    def __repr__(self):  # toString() in java
        outputhead = self.description.center(30, "*") + "\n"
        outputledger = ""
        for operation in self.ledger:
            desc = "{:<23}".format(operation["description"])
            am = "{:>7.2f}".format(operation["amount"])
            outputledger += "{}{}\n".format(desc[:23], am[:7])
        outputtotal = "Total: {:.2f}".format(self.total_funds)
        return outputhead + outputledger + outputtotal

    def deposit(self, amount, description=""):        
        self.ledger.append({"amount": amount, "description": description})
        self.total_funds += amount

    def withdraw(self, amount, description=""):
        if(not self.check_funds(amount)):
            return False
        else:
            self.ledger.append({"amount": -1 * amount, "description": description})
            self.total_funds -= amount
            return True

    def get_balance(self):
        return self.total_funds

    def transfer(self, amount, category_object):
        if self.withdraw(amount, "Transfer to {}".format(category_object.description)):
            category_object.deposit(amount, "Transfer from {}".format(self.description))
            return True
        else:
            return False

    def check_funds(self, amount):
        if(amount > self.total_funds):
            return False
        else:
            return True


def create_spend_chart(categories):
    spent_amounts = []
    # Get total spent in each category
    for category in categories:
        spent = 0
        for item in category.ledger:
            if item["amount"] < 0:
                spent += abs(item["amount"])
        spent_amounts.append(round(spent, 2))

    # Calculate percentage rounded down to the nearest 10
    total = round(sum(spent_amounts), 2)
    spent_percentage = list(map(lambda amount: int((((amount / total) * 10) // 1) * 10), spent_amounts))

    # Create the bar chart substrings
    header = "Percentage spent by category\n"

    chart = ""
    for value in reversed(range(0, 101, 10)):
        chart += str(value).rjust(3) + '|'
        for percent in spent_percentage:
            if percent >= value:
                chart += " o "
            else:
                chart += "   "
        chart += " \n"

    footer = "    " + "-" * ((3 * len(categories)) + 1) + "\n"
    descriptions = list(map(lambda category: category.description, categories))
    max_length = max(map(lambda description: len(description), descriptions))
    descriptions = list(map(lambda description: description.ljust(max_length), descriptions))
    for x in zip(*descriptions):
        footer += "    " + "".join(map(lambda s: s.center(3), x)) + " \n"

    return (header + chart + footer).rstrip("\n")
