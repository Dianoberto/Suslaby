# Приклад 1: Використання Ray для генерації числа пі за допомогою віддаленої функції
import ray
from random import random

ray.init()
SAMPLES = 1000000

@ray.remote
def pi4_sample():
    in_count = 0
    for _ in range(SAMPLES):
        x, y = random(), random()
        if x*x + y*y <= 1:
            in_count += 1
    return in_count

# Виклик віддаленої функції
result_id = pi4_sample.remote()
result = ray.get(result_id)
print('Pi is approximately', result * 4 / SAMPLES)

# Приклад 2: Використання Ray для паралельного виконання завдань
@ray.remote
def f(x):
    return x * x

futures = [f.remote(i) for i in range(10)]
print(ray.get(futures))

import pandas as pd
# Створення DataFrame
df = pd.DataFrame({
   'A': ['foo', 'bar', 'foo', 'bar', 'foo', 'bar', 'foo', 'foo'],
   'B': ['one', 'one', 'two', 'three', 'two', 'two', 'one', 'three'],
   'C': ['small', 'large', 'large', 'small', 'small', 'large', 'small', 'small'],
   'D': [1, 2, 2, 3, 3, 4, 5, 6],
   'E': [2, 4, 5, 5, 6, 6, 8, 9]
})
print(df)
# Приклад роботи з Pandas:
#1
df['new_column'] = range(1, len(df) + 1)
print(df)
#2
median = df['D'].median()
print(median)
