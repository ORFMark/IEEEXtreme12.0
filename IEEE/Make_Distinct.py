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

# numpy and scipy are available for use
import numpy
import scipy

ops = 0;
def operations(index):
    global myList
    val = myList[index]
    ind = index
    back = 0;
    forw = 0;
    while val == myList[ind] and ind < len(myList):
        val += 1;
        if val > myList[ind]:
            ind +=1;
        forw+=1;
    ind = index
    val = myList[index]
    while val == myList[ind] and ind < len(myList):
        val -= 1;
        if val < myList[ind]:
            ind -=1;
        back +=1
    if forw < back:
        myList[index] += forw
        myList = sorted(myList)
        return forw;
    else:
        myList[index] -= back
        myList = sorted(myList)
        return back;
length = get_number()
myList = [];
dublicateList = []
ops = 0;
while(len(myList) + len(dublicateList) < length):
    val = get_number();
    if val in myList:
        dublicateList.append(val);
    else:
        myList.append(val);
while len(dublicateList) != 0:
    dublicateList = sorted(dublicateList)
    myList.append(dublicateList[0])
    myList = sorted(myList)
    for i in range(0, len(myList)-1):
        if myList[i] == myList[i+1]:
           ops += operations(i)
    dublicateList.pop(0)
print(ops)