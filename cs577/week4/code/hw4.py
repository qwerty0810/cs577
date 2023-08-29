def binarySearch(finishList, low, high, s):
    
    if low > high or finishList[low] > s:
        return -1
    
    
    if low == high:
        return low
    
    mid = (low + high) // 2
    if finishList[mid] <= s < finishList[mid+1]:
        return mid
    elif finishList[mid] > s:
        return binarySearch(finishList, low, mid-1, s)
    else:
        return binarySearch(finishList, mid+1, high, s)


class WIS:
    def __init__(self, jobList):
        self.sortJob = sorted(jobList, key=lambda x: x[1])
        self.s = [item[0] for item in self.sortJob]
        self.f = [item[1] for item in self.sortJob]
        self.v = [item[2] for item in self.sortJob]
        self._n = len(self.s)
        self.m = [0] * self._n  # total weight of schedule
    
    def schedule(self):
        self.m[0] = self.v[0]

        for j in range(1, self._n):
            i = binarySearch(self.f, 0, j-1, self.s[j])

            if i == -1:
                self.m[j] = max(self.m[j-1], self.v[j])
            else:
                self.m[j] = max(self.m[j-1], self.m[i] + self.v[j])

        return self.m[-1]


def main():
    numInstance = int(input())  # the number of instance, first input
    res = []
    for _ in range(numInstance):
        numJob = int(input())  # the number of jobs in i-th instance
        jobs = [list(map(int, input().split())) for _ in range(numJob)]
        WeightIS = WIS(jobs)
        res.append(WeightIS.schedule())
    for val in res:
        print(val)


if __name__ == "__main__":
    main()
