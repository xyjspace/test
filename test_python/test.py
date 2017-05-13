def new():
    n = 1
    while True:
        n = n + 2
        yield n


def filter_jishu(n):
    return lambda x: x % n > 0


def primes():
    yield 2
    it = new()  # 初始序列
    while True:
        n = next(it)  # 返回序列的第一个数
        yield n
        it = filter(filter_jishu(n), it)  # 构造新序列


a = primes()


def by_name(t):
    return t[0].lower()


def by_score(t):
    return -t[1]


L = [('Bob', 75), ('Adam', 92), ('Bart', 66), ('Lisa', 88)]
L2 = sorted(L, key=by_name)
print(L2)
