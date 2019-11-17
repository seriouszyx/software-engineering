import jieba.analyse
from PIL import Image, ImageSequence
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties
from wordcloud import WordCloud, ImageColorGenerator

font = FontProperties(fname='Songti.ttc')
bar_width = 0.5
lyric = ''

f = open('../data/data_keywords.txt', 'r', encoding='utf-8')

for i in f:
    lyric += f.read()

result = jieba.analyse.textrank(lyric, topK=50, withWeight=True)

keywords = dict()
for i in result:
    keywords[i[0]] = i[1]
print(keywords)

image = Image.open('./background.png')
graph = np.array(image)
wc = WordCloud(font_path='Songti.ttc', background_color='White', max_words=50, mask=graph)
wc.generate_from_frequencies(keywords)
image_color = ImageColorGenerator(graph)
plt.imshow(wc)
plt.imshow(wc.recolor(color_func=image_color))
plt.axis("off")
plt.show()
wc.to_file('../out/wordcloud.png')

X = []
Y = []

for key in keywords:
    X.append(key)
    Y.append(keywords[key])

num = len(X)

fig = plt.figure(figsize=(28, 10))
plt.bar(range(num), Y, tick_label=X, width=bar_width)
plt.xticks(rotation=50, fontproperties=font, fontsize=20)
plt.yticks(fontsize=20)
plt.title("words-frequency chart", fontproperties=font, fontsize=30)
plt.savefig("../out/barChart.jpg", dpi=360)
plt.show()
