#Basic algo:
# Sort the input array
# a temp variable for runtime comparison
# start the temp variable with 0 and keep the possible change of every iteration
# If the latest cent is <= to previous cent + 1 then that (previous cent+ 1) can be made
# latest cent is 7 and 1+1+2+3+5 = 12 and (12+1 =13) which is greater than latest cent 7. So change 13 can be made 
# as well as upto 12+7 = 19 cents, so 1 up to 19 change can be made. 
# If the latest cent > previous cent + 1 then that (previous cent+ 1) change can not be made
# latest cent is 22 and 1+1+2+3+5+7 = 19 and (19+1 = 20) which is less than latest cent 22. So change 20 can't be made.



def nonConstructibleChange(coins):
    # Write your code here.

    coins.sort()
    currentChangedPossible = 0
    for coin in coins:
        if coin > currentChangedPossible +1:
           return currentChangedPossible +1

        currentChangedPossible += coin   
  
    return currentChangedPossible +1

if __name__ == "__main__":

    coins = [5, 7, 1, 1, 2, 3, 22] 
    print(nonConstructibleChange(coins)) 