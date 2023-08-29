class MyGraph:
    def __init__(self, num_nodes):
        self.adj_list = {}
        self.todo = []
        self.traversal = []
        self.num_nodes = num_nodes

    def add_node(self, input_row):
        input_list = input_row.split()
        self.adj_list[input_list[0]] = input_list[1:]

    def depth_first_search(self):
        keys = list(self.adj_list.keys())
        nodes_not_stepped = [node for node in keys if node not in self.traversal]

        while nodes_not_stepped:
            self.todo.append(nodes_not_stepped[0])

            while self.todo:
                current_node = self.todo.pop()
                self.traversal.append(current_node)

                for adj_node in reversed(self.adj_list[current_node]):
                    if adj_node not in self.traversal:
                        self.todo.append(adj_node)

                todo = [node for node in reversed(self.todo) if node not in self.traversal]
                self.todo = list(reversed(todo))

            nodes_not_stepped = [node for node in keys if node not in self.traversal]


def main():
    num_instances = int(input())
    ins_list = []
    for _ in range(num_instances):
        num_nodes = int(input())
        g = MyGraph(num_nodes)
        node_idx = 0
        while True:
            g.add_node(input())
            node_idx += 1
            if node_idx >= num_nodes:
                break
        ins_list.append(g)

    for instance in ins_list:
        instance.depth_first_search()
        print(' '.join(instance.traversal))


if __name__ == '__main__':
    main()
