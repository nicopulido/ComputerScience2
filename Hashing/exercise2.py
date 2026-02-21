import string

def encode(str, keys):
    result = []
    counter = 0
    key = keys[counter]
    for i in str:
        result.append(chr(ord(i)+key))
        counter += 1 
        if (counter > 4):
            counter = 0
    return "".join(result)
    
sample = 'Solo hay 10 personas en el mundo, las que entienden y las que no binario'
keys = [9,3,7,2]
print(encode(sample, keys))