class Knapsack:
    def __init__(self, n, W, itemList):
        self.n = n
        self.W = W
        self.items = itemList

    def _knapsack(self, n, W, items):
        vOld = [0] * (W + 1)
        vNew = [0] * (W + 1)

        for i in range(n):
            for w in range(1, W + 1):
                weight, value = items[i]
                if w >= weight:
                    vNew[w] = max(vOld[w], vOld[w - weight] + value)
                else:
                    vNew[w] = vOld[w]

            vOld = vNew[:]

        return vNew[W]

    def compute(self):
        return self._knapsack(self.n, self.W, self.items)


def main():
    numInstances = int(input())
    results = []
    for _ in range(numInstances):
        n, W = map(int, input().split())

        items = []
        for _ in range(n):
            weight, value = map(int, input().split())
            items.append((weight, value))

        ks = Knapsack(n, W, items)
        results.append(ks.compute())

    for val in results:
        print(val)


if __name__ == "__main__":
    main()
