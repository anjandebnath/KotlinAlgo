# linked list is a DS that consists of nodes. Typically each node
# has some kind of value. On top of this value, each node have pointers to
# other nodes. So if were dealing with singly linked list, every node would point
# to the following node to the next node.
# in a doubly linked list every node points to two other nodes,
# the next node and the previous node.
# this nodes will be represented as prev and next properties.

# the linked list has a head and a tail

# methods that a linked list have to support
# set the head and set the tail at any point of time
# searching, removal of nodes
# removal consists of 2 types
# simple node removal and other is
# remove all the nodes from the linked list that have a given value

# insertion of 3 types
# inserting a node before or after another given node
# insertion at a given position

class Node:
    def __init__(self, value):
        self.value = value
        self.prev = None
        self.next = None

class DoublyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def setHead(self, node):
        if self.head is None: # empty linked list
            self.head = node
            self.tail = node
            return
        self.insertBefore(self.head, node)


    def setTail(self, node): # empty linked list
        if self.tail is None:
            self.setHead(node)
            return
        self.insertAfter(self.tail, node)


    def insertBefore(self, node, nodeToInsert):
        # 1--> 2 --> 3 --> 5 --> 6 (5,4)
        # if we are dealing with the node that's the only node in the linked list
        # before itself
        if nodeToInsert == self.head and nodeToInsert == self.tail:
            return
        self.remove(nodeToInsert) # if the node is in our linked list we will remove it blindly, if not then no operation

        # update node pointer for inserting node
        # 3 --> 4--> 5
        nodeToInsert.prev = node.prev
        nodeToInsert.next = node

        # update node pointer for the current node
        if node.prev is None:
            self.head = nodeToInsert
        else:
            node.prev.next = nodeToInsert
        node.prev = nodeToInsert



    def insertAfter(self, node, nodeToInsert):
        if nodeToInsert == self.head and nodeToInsert == self.tail:
            return
        self.remove(nodeToInsert)
        nodeToInsert.prev = node
        nodeToInsert.next = node.next

        # update node pointer for the current node
        if node.next is None:
            self.tail = nodeToInsert
        else:
            node.next.prev = nodeToInsert
        node.next = nodeToInsert


    def insertAtPosition(self, position, nodeToInsert):
        if position == 1:
            self.setHead(nodeToInsert)
            return
        node = self.head
        currentPosition =1
        while node is not None and currentPosition != position:
            node = node.next
            currentPosition += 1
        if node is not None:
            self.insertBefore(node, nodeToInsert)
        else:
            self.setTail(nodeToInsert)



    def removeNodesWithValue(self, value):
        node = self.head
        while node is not None:
            # to keep track of the nodes
            # it is important to keep in temp variable
            # otherwise after removing the node there will be no track
            nodeToRemove = node
            node = node.next
            if nodeToRemove.value == value:
                self.remove(nodeToRemove)

    def remove(self, node):
        # check are we dealing with the head or tail of the linked list
        if (node == self.head): # if head then update the head with the following node
            self.head = self.head.next
        if (node == self.tail):
            self.tail = self.tail.prev
        self.removeNodesBinding(node)


    def containsNodeWithValue(self, value):
        # traverse the list and check if there is a node whose value matches
        # the given value
        node = self.head

        # traverse as long as the node is not null, that is not the tail
        # and node.value is not equal to our value
        while node is not None and node.value != value:
            node = node.next
        return node is not None

    def removeNodesBinding(self, node):
        # update the pointer of surrrounding nodes
        # and remove the pointers of the given node.
        # 1--> 2 --> 3 --> 4 where need to remove node 3
        # So 4 is now point to 2
        if node.prev is not None:
            node.prev.next = node.next
        # So 2 is now point to 4
        if node.next is not None:
            node.next.prev = node.prev

        node.prev = None
        node.next = None

if __name__== "__main__":


        one = Node(1)
        two = Node(2)
        three = Node(3)
        three2 = Node(3)
        three3 = Node(3)
        four = Node(4)
        five = Node(5)
        six = Node(6)

        linkedList = DoublyLinkedList()
        linkedList.head = one
        linkedList.tail = five


 