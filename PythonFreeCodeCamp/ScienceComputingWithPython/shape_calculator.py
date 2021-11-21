import math

class Rectangle:
    def __init__(self, w, h):
        self.width = w
        self.height = h

    def set_width(self, w):
        self.width = w

    def set_height(self, h):
        self.height = h

    def get_area(self):
        return self.width * self.height

    def get_perimeter(self):
        return 2 * self.width + 2 * self.height

    def get_diagonal(self):
        return (self.width ** 2 + self.height ** 2) ** .5

    def get_picture(self):
        if(self.width > 50 or self.height > 50):
            return "Too big for picture."
        else:
            output = ""
            for i in range(self.height):
                for j in range(self.width):
                    output += "*"
                output += "\n"
        return output

    def get_amount_inside(self, rect):
        return math.floor(self.get_area() / rect.get_area())

    def __repr__(self):
        return "Rectangle(width={}, height={})".format(self.width, self.height)


class Square(Rectangle):
    def __init__(self, s):
        self.height = s
        self.width = s

    def set_side(self, s):
        self.height = s
        self.width = s

    def __repr__(self):
        return "Square(side={})".format(self.height)

    def set_height(self, s):
        self.height = s
        self.width = s

    def set_width(self, s):
        self.height = s
        self.width = s


rect = Rectangle(10, 5)
print(rect.get_area())
rect.set_height(3)
print(rect.get_perimeter())
print(rect)
print(rect.get_picture())
sq = Square(9)
print(sq.get_area())
sq.set_side(4)
print(sq.get_diagonal())
print(sq)
print(sq.get_picture())

rect.set_height(8)
rect.set_width(16)
print(rect.get_amount_inside(sq))
