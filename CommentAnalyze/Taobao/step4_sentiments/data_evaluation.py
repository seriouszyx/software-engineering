import os

from snownlp import SnowNLP
import matplotlib.pyplot as plt
from snownlp import sentiment
sentiment.classifier.load('train_model/sentiment.marshal')

comment = []
pos_count = 0
neg_count = 0

for line_data in open("../data/data_keywords.txt", 'r', encoding='utf-8'):

    comment = line_data

    s = SnowNLP(comment)
    rates = s.sentiments

    if (rates >= 0.5):
        pos_count += 1

    elif (rates < 0.5):
        neg_count += 1

    else:
        pass

labels = 'Positive Side', 'Negative Side'
fracs = [pos_count, neg_count]
explode = [0.1, 0]  # 0.1 凸出这部分，
plt.axes(aspect=1)  # set this , Figure is round, otherwise it is an ellipse
# autopct ，show percet

plt.pie(x=fracs, labels=labels, explode=explode, autopct='%3.1f %%',
        shadow=True, labeldistance=1.1, startangle=90, pctdistance=0.6)

plt.savefig("../out/emotions_pie_chart.jpg", dpi=360)
plt.show()
