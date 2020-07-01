# -*- coding: UTF-8 -*-
class LoopNode:
    def __init__(self, data):
        self.nextNode = self
        self.data = data

    def set_next(self, node):
        tmp_node = self.nextNode
        self.nextNode = node
        node.nextNode = tmp_node
        return node

    def get_data(self):
        return self.data
