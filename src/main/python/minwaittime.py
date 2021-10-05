# values of the array will represent the duration of a query.
# We can execute the query in any order but only one query will be executed at a time
# so we will minimize the total waiting time for all the queries.
# the input array can be mutate

def minimumWaitingTime(queries):
    # sort the array
    queries.sort()
    totalWaitingTime =0

    for idx, duration in enumerate(queries):
        queriesLeft = len(queries) - (idx +1)
        # rather sum all the elements in the sorted array
        # its a good logic
        # for the current index query, calculate the remain queries
        # and all those remaining queries need to wait this duration of time
        totalWaitingTime += duration * queriesLeft

    return totalWaitingTime

if __name__ == "__main__":
    queries = [3, 2, 1, 2, 6]
    print(minimumWaitingTime(queries))