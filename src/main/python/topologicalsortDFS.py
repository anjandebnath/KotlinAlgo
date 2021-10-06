# topological sort means you have list of tasks where some tasks are dependent on others
# Like task 2 can be done only after task 1 completion
# Again task 3 can be done only after task 1 and 2 completion.
# so the tasks are directed and we will deal with directed graph

# at first define a class for graph data structure

def topologicalSort(jobs, deps):
    jobGraph = createJobGraph(jobs, deps)
    return getOrderedJobs(jobGraph)

def createJobGraph(jobs, deps):
    graph = JobGraph(jobs)
    for job, dep in deps:
        graph.addDep(job, dep)
    return graph   

def getOrderedJobs(graph) :
    orderedJobs = []
    '''
    Java:
    List<JobNode> nodesWithNoPrereqs = new ArrayList<JobNode>();
    for(JobNode node : graph.nodes){
        if(nodes.numOfPrereqs == 0){
            nodesWithNoPrereqs.add(node)
        }
    }
    Python w/o lambda:
    items = [1, 2, 3, 4, 5]
    squared = []
    for i in items:
      squared.append(i**2)

    items = [1, 2, 3, 4, 5]
    squared = list(map(lambda x: x**2, items)) 

    nodesWithNoPrereqs = []
    for node in graph.nodes:
        if node.numOfPrereqs == 0:
             nodesWithNoPrereqs.appened(node)
    '''
    # nodesWithNoPrereqs = list(filter(lambda node: node.numOfPrereqs == 0, graph.nodes))  
    nodesWithNoPrereqs = []
    for node in graph.nodes:
        if node.numOfPrereqs == 0:
            nodesWithNoPrereqs.append(node)

    while len(nodesWithNoPrereqs):
        node = nodesWithNoPrereqs.pop() 
        orderedJobs.append(node.job)
        removeDeps(node, nodesWithNoPrereqs)

    graphHasEdges = any(node.numOfPrereqs for node in graph.nodes) 
    return [] if graphHasEdges else orderedJobs

def removeDeps(node, nodesWithNoPrereqs):
    while len(node.deps):
        dep = node.deps.pop()    
        dep.numOfPrereqs -= 1
        if dep.numOfPrereqs == 0:
            nodesWithNoPrereqs.append(dep)   

class JobGraph:

    def __init__(self, jobs):
        # on the graph, the nodes will be acyclic 
        self.nodes = [] # add the jobs as nodes
        self.graph = {} # hashmap to notice the job 
        for job in jobs:
            self.addNode(job)

    def addDep(self, job, dep):
        jobNode = self.getNode(job) 
        depNode = self.getNode(dep)  
        jobNode.deps.append(depNode)    
        depNode.numOfPrereqs +=1  

    def addNode(self, job):
        self.graph[job]  = JobNode(job) 
        self.nodes.append(self.graph[job])  

    def getNode(self, job):
        if job not in self.graph:
            self.addNode(job)   
        return self.graph[job]     


class JobNode:
    def __init__(self, job):
        self.job = job
        self.deps = [] # list the dependencies 
        self.numOfPrereqs = 0 # calculate the prerequiite of each node
        

if __name__ == "__main__":

    jobs = [1,2,3,4]
    deps = [[1,2], [1,3], [3,2], [4,2], [4,3]]
    print(topologicalSort(jobs, deps))
