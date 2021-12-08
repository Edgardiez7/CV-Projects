def find_length(num1, num2):
    l1 = len(num1)
    l2 = len(num2)
    return max(l1, l2)+2


def arithmetic_arranger(problems, printscr=False):
    if(len(problems) > 5):
        return "Error: Too many problems."
    firstOperand = []
    secondOperand = []
    operation = []
    for problem in problems:
        elements = problem.split()

        # validate operator
        if(elements[1] != '+' and elements[1] != '-'):
            return "Error: Operator must be '+' or '-'."
        operation.append(elements[1])

        # validate numbers and lenght
        if(not elements[0].isnumeric() or not elements[2].isnumeric()):
            return "Error: Numbers must only contain digits."
        if(len(elements[0]) > 4 or len(elements[2]) > 4):
            return "Error: Numbers cannot be more than four digits."
        firstOperand.append(elements[0])
        secondOperand.append(elements[2])
        #print(firstOperand, operation, secondOperand)
    output1 = ""
    output2 = ""
    output3 = ""
    output4 = ""
    length = -1
    for i in range(len(problems)):
        length = find_length(firstOperand[i], secondOperand[i])
        # add first line output
        l1 = len(firstOperand[i])
        for j in range(length - l1):
            output1 += ' '
        output1 += firstOperand[i]
        if(i != len(problems) - 1):
            output1 += "    "
        # add second line output
        l2 = len(secondOperand[i])
        output2 += operation[i]
        for j in range(length - (l2 + 1)):
            output2 += ' '
        output2 += secondOperand[i]
        if(i != len(problems) - 1):
            output2 += "    "
        # add third line output
        for j in range(length):
            output3 += '-'
        if(i != len(problems) - 1):
            output3 += "    "
        # add fourth line output
        result = None
        if(operation[i] == "+"):
            result = int(firstOperand[i]) + int(secondOperand[i])
        else:  # its "-"
            result = int(firstOperand[i]) - int(secondOperand[i])
        l4 = len(str(result))
        for j in range(length - l4):
            output4 += ' '
        output4 += str(result)
        if(i != len(problems) - 1):
            output4 += "    "
    # total output
    if(printscr):
        output_total = output1 + "\n" + output2 + \
            "\n" + output3 + "\n" + output4
    else:
        output_total = output1 + "\n" + output2 + "\n" + output3
    return(output_total)


print(arithmetic_arranger(['3801 - 2', '123 + 49'], True))
