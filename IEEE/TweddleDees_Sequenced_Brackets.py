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
#import numpy
#import scipy

seq = get_word()
done = False;
seq = list(seq);
openSq = 0;
openParen = 0;
closeSq = 0;
closeParen =0;
openCountSq = 0
openCountPara = 0
badInput = False
if (len(seq) % 2 == 1):
    print("impossible")
    done = True;
if not done:
    for i in seq:
        if i == '[':
            openSq+=1
            openCountSq += 1
        if i == ']': 
            closeSq+=1
            if openCountSq == 0:
                badInput = True
            openCountSq-=1
        if i == '(': 
            openParen+=1
            openCountPara+=1
        if i == ')':
            closeParen+=1
            if openCountPara == 0:
                badInput = True
            openCountPara -= 1
if (((openSq != closeSq) or (openParen != closeParen)) or badInput) and not done:
    print("impossible")
    done = True;
elif ((openSq + openParen) % 2 == 1) and not done:
    print("impossible")
    done = True;
elif not done:
    openB = (openSq + openParen)/2
    open1 = 0;
    sq1 = 0;
    para1 = 0;
    parse = [];
    openParaL = []
    openSquareL = []
    count = -1
    for i in range(0, len(seq)):
        parse.append(0)
    for i in seq:
        count+=1
        if i == '(':
            openParaL.append(count)
        elif i == '[':
            openSquareL.append(count)
        elif i == ')':
            if (open1 < openB):
                parse[openParaL.pop(0)] = 1
                parse[count] = 1
                open1+=1
            else:
                parse[openParaL.pop(0)] = 2
                parse[count] = 2
        elif i == ']':
            if open1 < openB:
                parse[openSquareL.pop(0)] = 1
                parse[count] = 1
                open1+=1
            else:
                parse[openSquareL.pop(0)] = 2
                parse[count] = 2
    parseString = " ";
    for i in parse:
        parseString += str(i) + " "
    parseString = parseString.strip()
    print(parseString)
