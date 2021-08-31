# valid ip address have four different sections/ periods
# the four sections contain values range between 0 to 255
# each of the four sections must be atmost 8 bits
# there would be no leading zero in any section

# 19216880
# figure out the first valid position for the first period
# and try all of the different possibilites after that.
#    the positions that are valid to place our first period
#    are after 1, after 9 and after 2 because the values of
#    the first period must be 3 digit andless than or equal 255.
# figure out the first valid position for the second period,
# and check if it's valid.
# figure out the first valid position for the 3rd period, 
# and check if its valid and also check the 4th period at the same time and check if it's valid.
# if the 4th period is not valid then backtrack and move frward 1 from the 3rd period
# if the 3rd position is not valid then backtrack and  move forward 1 from the 2nd period
# if the 2nd position is not valid then backtrack and move forward 1 from the 1st period


def validIPAddresses(string):
    
    ipAddressesFound = []

    # to ensure if the string length is less than 4 then not occur the index out of bound
    # for first part the valid position is 1,2,3
    for i in range(1, min(len(string), 4)):
        # ip address has 4 parts
        currentIPAddressParts = ["","","",""]

        # define the 1st part
        # it should be the index until i
        currentIPAddressParts[0] = string[:i]
        if not isValidPart(currentIPAddressParts[0]):
            continue

        # for second part it should be i+1, i+2, i+3
        for j in range(i+1, i+ min(len(string)-i, 4)):
            currentIPAddressParts[1] = string[i:j]
            if not isValidPart(currentIPAddressParts[1]):
                continue

            for k in range(j+1, j+min(len(string) - j, 4)):
                currentIPAddressParts[2] = string[j:k]
                currentIPAddressParts[3] = string[k:]

                if isValidPart(currentIPAddressParts[2]) and isValidPart(currentIPAddressParts[3]) :
                    ipAddressesFound.append('.'.join(currentIPAddressParts))

        
    return ipAddressesFound

def isValidPart(string):
    stringAsInt = int(string)
    if stringAsInt > 255:
        return False

    return len(string) == len(str(stringAsInt))    


if __name__ == "__main__":
        
    # string = "255255255256"
    string = "1921680"
    print(validIPAddresses(string))      



