
#Sample input 
# Competitions = [["HTML", "C#"],["C#", "PYTHON"],["PYTHON","HTML"]] List of Lists
# Results = [0,0,1] List
#Sample Output 
# "PYTHON"

# Basic algo: 
# look through all the input competitions
# and keep track of how many points every single team has.
# Once we know all the points for all of the teams 
# then we can simply go through and figure out whih team has most of the points.
# So we need to create a some kind of data steucture that can tell us which team has what number of points.
# 1. We can either initialize this data structure when we start our algorithm or 
# 2. We can create it as we go through.

# Sol: 1 (Create data structure as we gp through)
# traverse the competitions and results array, and store the winner with winning points in the new data structure.
# then traverse the data structure to find out the best winner.
# So, 2 for loop needs to be traversed.

#Sol: 2 (create data structure at the begining with an empty best)
# create the data structure with and empty best 
# now traverse the competitions and results array and update the data structure with winner


HOME_TEAM_WON = 1

def tournamentWinner(competitions, results):
    currentBestTeam = "" # which one I need to find
    scores = {currentBestTeam: 0} # initially current best team has score 0 /Dictionary in python

    # The enumerate() function assigns an index to each item in an iterable object 
    # that can be used to reference the item later. ... It makes it easier to 
    # keep track of the content of an iterable object
    for idx, competition in enumerate(competitions):
        result = results[idx]
        homeTeam, awayTeam = competition #assign list value to variable

        winningTeam = homeTeam if result == HOME_TEAM_WON else awayTeam #yes_value if test else no_value

        updateScores(winningTeam, 3, scores)

        if scores[winningTeam] > scores[currentBestTeam]: # compare the hash map value by key
            currentBestTeam = winningTeam

    return currentBestTeam       



def updateScores(team, points, teamScore):
    if team not in teamScore:
        teamScore[team] = 0

    teamScore[team] += points    


if __name__ == "__main__" :

    """
     lst = [ ] 
     n = int(input("Enter number of elements : ")) 
    
     for i in range(0, n): 
        ele = [input(), input()] 
        lst.append(ele) 
        
     print(lst)  
    """

    competitions = [["HTML", "C#"],["C#", "PYTHON"],["PYTHON","HTML"]]
    results = [0,0,1] 
    print(tournamentWinner(competitions, results))

