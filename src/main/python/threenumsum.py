"""
Problem statement: Write a function that takes in a non empty array of distinct integers (non repeated)
and an integer representing a target sum. The function should find all the triplets in the array
that sum up the target sum and return a two dimensional array of all triplets.
The numbers in each triplets should be ordered in ascending order, and the triplets themselves
should be ordered in ascending order.

"""

"""
1. Sort hte array
2. to find the triplets in O(N^2) time that is using 2 loops but using 2 pointers
3. Current num + left pointer + right pointer = current sum
4. if current sum < target sum, then increase left pointer by 1 index
5. if current sum > target sum, then increase right pointer by 1 index
6. if current sum = target sum, then increase left pointer by 1 index and right pointer by 1 index

Time: O(N^2) for left and right pointer we iterate the loop and also for current num we need to loop also
"""
def threeNumberSum(array, targetSum):
    # Write your code here.
    array.sort()
    triplets = []
    for i in range(len(array) -2):
        left = i+1
        right = len(array)-1
        while left < right:
            currentSum = array[i] +array[left] + array[right]
            if currentSum == targetSum:
                triplets.append([array[i], array[left], array[right]])
                left+= 1
                right-=1
            elif currentSum < targetSum:
                left += 1
            elif currentSum > targetSum:
                right-=1
    return triplets


if __name__ == "__main__":
    array = [12, 3, 1, 2, -6, 5, -8, 6]
    targetSum = 0
    print(threeNumberSum(array, targetSum))