
# dfs: go deeper and then wider
# traversing or exploring a graph in depth first search way.
# We are going to explore a specific branch, all the way down, as deep as we can, before exploring the next branch.

# So the basic idea is to start from the root or any arbitrary node and
# mark the node and
# move to the adjacent unmarked node and
# continue this loop until there is no unmarked adjacent node.
# https://www.geeksforgeeks.org/linked-list-set-1-introduction/
# Then backtrack and check for other unmarked nodes and traverse them.


# A node is a collection of two sub-elements or parts.
# A data part that stores the element and a next part that stores the link to the next node.
class Node:
    def __init__(self, name): # self in python is equivalent to this in java
        self.name = name # node attribute
        self.children = [] # every node has an array of children nodes or empty nodes, point to the next nodes

    def addChild(self, name): # add a child to the node
        self.children.append(Node(name))

    def depthFirstSearch(self, array): # this array will return the names of the nodes at the end
        array.append(self.name) # append the node's name. because we are calling the dfs function on the node, so we can refer to it as self,
        for child in self.children:
            child.depthFirstSearch(array) # call the dfs function on the child
        return array


if __name__ == "__main__":

  #creating a simple acyclic graph
  root = Node("A")
  b = Node("B")
  c = Node("C")
  d = Node("D")
  root.children.append(b)
  root.children.append(c)
  root.children.append(d)
  e = Node("E")
  f = Node("F")
  b.children.append(e)
  b.children.append(f)
  g = Node("G")
  h = Node("H")
  d.children.append(g)
  d.children.append(h)
  i = Node("I")
  j = Node("J")
  k = Node("K")
  f.children.append(i)
  f.children.append(j)
  g.children.append(k)

  DFS = []
  print(root.depthFirstSearch(DFS))