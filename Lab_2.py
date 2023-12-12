import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Завантажте набір даних Netflix
df = pd.read_csv("/Users/Diana/Desktop/netflix_titles.csv", encoding="utf-8")

# 1. Побудуйте стовпчикову діаграму розподілу кількості проектів за телевізійним рейтингом
# Отримайте список телевізійних рейтингів
ratings = np.array(df["rating"].unique())

# Отримайте кількість проектів для кожного рейтингу
rating_counts = np.zeros(ratings.size)
for rating in ratings:
    rating_counts[ratings == rating] = len(df[df["rating"] == rating])

# Перетворіть списки чисел на списки рядків
ratings_str = [str(rating) for rating in ratings]
rating_counts_str = [str(rating_count) for rating_count in rating_counts]

# Побудуйте стовпчикову діаграму
plt.bar(ratings_str, rating_counts_str)
plt.xlabel("Телебачення рейтинг")
plt.ylabel("Кількість проектів")
plt.show()
# 2. Чи правда, що останніми роками Netflix більше фокусується на серіалах (TV Show), ніж на фільмах? Проаналізуйте останні 5 років.

# Отримайте список років з 2015 по 2022 рік
years = np.array(df["release_year"].unique())
years = years[years >= 2015]

# Отримайте кількість серіалів і фільмів, випущених у кожному році
tv_show_counts = []
movie_counts = []
for year in years:
    tv_show_counts.append(len(df[df["release_year"] == year]
                                [df["type"] == "TV Show"]))
    movie_counts.append(len(df[df["release_year"] == year]
                              [df["type"] == "Movie"]))

# Побудуйте точкову діаграму
plt.scatter(years, tv_show_counts, label="TV Show")
plt.scatter(years, movie_counts, label="Movie")
plt.legend()
plt.show()

# 3. Визначте, яка країна випустила найбільше проектів на Netflix у 2019 році. А яка найменше?
# Отримайте кількість проектів для кожної країни
country_counts = df[df["release_year"] == 2019][["country", "type"]].groupby("country").size().sort_values(ascending=False)

# Збережіть 5 перших країн у змінну
top_5 = country_counts[:5]

# Побудуйте стовпчикову діаграму
plt.bar(top_5.index, top_5)
plt.title("Країни з найбільшою кількістю проектів на Netflix після 2019 році")
plt.show()

# 4. В якому місяці на Netflix зазвичай виходить найбільше серіалів?

# Отримайте список місяців виходу серіалів
months = []
for date_added in df["date_added"]:
    date_added_str = str(date_added)

    # Перевірте, скільки елементів містить рядок
    num_elements = len(date_added_str.split(", "))

    # Розділіть рядок на окремі елементи за допомогою символу ","
    if num_elements > 1:
        month = date_added_str.split(", ")[0]
    else:
        month = ""

    months.append(month)

# Отримайте кількість проектів для кожного місяця
month_counts = df[df["type"] == "TV Show"].groupby(months).size().sort_values(ascending=False)

# Збережіть 5 перших місяців у змінну
top_5 = month_counts[:5]

# Побудуйте кругову діаграму
plt.pie(top_5, labels=top_5.index, autopct="%1.1f%%")
plt.title("Місяці з найбільшою кількістю серіалів на Netflix")
plt.show()
# 5. Визначте 5 найбільш популярних жанрів на Netflix та побудуйте відповідну кругову діаграму їх розподілу.

genre_counts = df.groupby("listed_in").size().sort_values(ascending=False)[:5]
plt.pie(genre_counts, labels=genre_counts.index)
plt.show()
