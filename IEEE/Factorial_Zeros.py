# a simple parser for python. use get_number() and get_word() to read
def parser():
    while 1:
        data = list(input().split(' '))
        for number in data:
            if len(number) > 0:
                yield(number)   

input_parser = parser()

def get_word():
    global input_parser
    return next(input_parser)

def get_number():
    data = get_word()
    try:
        return int(data)
    except ValueError:
        return float(data)

def primeFac(num):
    facArray = [];
    while num != 1:
        for i in range(2, int(num/2)):
            if num % i == 0:
                facArray.append(i)
                num = num / i
        num = 1;
    facArray.append(num);
    return facArray
# numpy and scipy are available for use
import numpy
import scipy

    
tests = get_number()
while tests > 0:
    tests -= 1
    base = get_number()
    targetZero = get_number()
    canidateP = 0;
    for i in primeFac(base):
        canidateP += i;
    canidate = scipy.math.factorial(canidateP * targetZero)
    rep = numpy.base_repr(canidate, base)
    print(rep)
    zeroCount = 0;
    backIt = -1
    while rep[backIt] == '0':
        zeroCount+=1
        backIt-=1
    if zeroCount == targetZero:
        print(canidateP)
    else:
        print(-1)
